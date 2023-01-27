package korea_recipe_board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import korea_recipe_board.model.service.KRService;
import korea_recipe_board.model.vo.KRBoard;
import korea_recipe_board.model.vo.KRContent;

/**
 * Servlet implementation class InsertKRecipeServlet
 */
@WebServlet("/insertkr.en")
public class InsertKRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertKRecipeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int result = 0;
		KRService ks = new KRService();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		RequestDispatcher view = null;
		if (!ServletFileUpload.isMultipartContent(request)) { // 멀티파트방식으로 전송되면 true !를 붙여서 false로 만들어준다.
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "form의 enctype='multipart/form-data' 속성 누락");
			view.forward(request, response);
		}

		int maxSize = 1024 * 1024 * 50; // 50mb

		String savePath = request.getSession().getServletContext().getRealPath("/resources/thumbnail");
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",new DefaultFileRenamePolicy());

		KRBoard kb = new KRBoard();
		kb.setCookName(mrequest.getParameter("cname"));
		kb.setKrBoardTitle(mrequest.getParameter("btitle"));
		kb.setAdminId(mrequest.getParameter("writer"));

		String originalFileName = mrequest.getFilesystemName("thumbnail");
		kb.setThumbNailFile(originalFileName);
		if (originalFileName != null) {

			String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
			renameFileName += "." + originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

			File originFile = new File(savePath + "\\" + originalFileName);
			File renameFile = new File(savePath + "\\" + renameFileName);

			if (!originFile.renameTo(renameFile)) {
				// renameTo 메소드가 실패할 경우엔 직접 바꾸기한다.
				// 원본파일 내용 읽어서, 복사본에 기록하고 완료되면, 원본 파일 삭제한다.
				FileInputStream fin = new FileInputStream(originFile);
				FileOutputStream fout = new FileOutputStream(renameFile);
				int data = -1;
				byte[] buffer = new byte[1024];
				while ((data = fin.read(buffer, 0, buffer.length)) != -1) {
					fout.write(buffer, 0, buffer.length);
				}
				fin.close();
				fout.close();
				originFile.delete();
			}

			originFile.delete();
			kb.setRenameFile(renameFileName);
		}
		result = ks.insertKR(kb); // 글 등록
		int krno = ks.selectKrno(kb.getAdminId());

		if (result > 0) { // 글 등록 성공여부 확인
			// 글저장
			int result1 = 0;
			ArrayList<KRContent> krclist = new ArrayList<KRContent>();
				int count = Integer.parseInt(mrequest.getParameter("count"));
				for (int i = 1; i <= count; i++) {
					KRContent krc = new KRContent();
					
					krc.setKrCount(i);
					krc.setKrBoardNo(krno);
					krc.setKrBoardContent(mrequest.getParameter("content" + i));
					String fileName = mrequest.getFilesystemName("upfile" + i);
					krc.setKrOriginFile(fileName);
					if (fileName != null) {
						String renameFile = sdf.format(new java.sql.Date(System.currentTimeMillis())) + i;
						renameFile += "." + fileName.substring(fileName.lastIndexOf(".") + 1);

						File oFile = new File(savePath + "\\" + fileName);
						File rFile = new File(savePath + "\\" + renameFile);

						if (!oFile.renameTo(rFile)) {
							// renameTo 메소드가 실패할 경우엔 직접 바꾸기한다.
							// 원본파일 내용 읽어서, 복사본에 기록하고 완료되면, 원본 파일 삭제한다.
							FileInputStream fin = new FileInputStream(oFile);
							FileOutputStream fout = new FileOutputStream(rFile);
							int data = -1;
							byte[] buffer = new byte[1024];
							while ((data = fin.read(buffer, 0, buffer.length)) != -1) {
								fout.write(buffer, 0, buffer.length);
							}
							fin.close();
							fout.close();
							oFile.delete();
						}

						oFile.delete();

						krc.setKrRenameFile(renameFile);

					}
					krclist.add(krc);
				}
			result1 = ks.insertKrCon(krclist);
			if (result1 > 0) {
				response.sendRedirect("/jmtgr/krdetail.ss?krno="+krno);
			} else {
				view = request.getRequestDispatcher("views/common/error.jsp");
				request.setAttribute("message", "글 내용 등록 실패");
				view.forward(request, response);
			}
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "글 등록 실패");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
