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
 * Servlet implementation class UpdateKRecipeServlet
 */
@WebServlet("/krupdate.en")
public class UpdateKRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateKRecipeServlet() {
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

		int maxSize = 1024 * 1024 * 50;

		String savePath = request.getSession().getServletContext().getRealPath("/resources/thumbnail");

		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		KRBoard krb = new KRBoard();
		krb.setKrBoardTitle(mrequest.getParameter("btitle"));
		krb.setCookName(mrequest.getParameter("cname"));
		krb.setAdminId(mrequest.getParameter("writer"));
		krb.setKrBoardNo(Integer.parseInt(mrequest.getParameter("krno")));

		// 첨부파일 삭제 여부 추출
		String deleteFlag = mrequest.getParameter("deleteFlag");

		String originalFileName = mrequest.getParameter("ofile");
		String renameFileName = mrequest.getParameter("rfile");
		String newRenameFileName = null;
		// 서버에 새로 업로드된 파일명 추출하기
		String newOriginalFileName = mrequest.getFilesystemName("thumbnail"); // 파일 명만 추출
		krb.setThumbNailFile(newOriginalFileName);

		// 원래 파일과 새로 업로드된 파일의 이름이 같고 파일 용량도 동일하면 원래 이름 그대로 객체에 기록한다.
		// 첨부파일이 없었는데 추가된 경우와 있었는데 변경된 경우 둘 다 파일명 바꾸기한다.

		// 첨부된 파일의 파일명 바꾸기 하려면....
		// 저장 폴더에 같은 이름의 파일이 있을경우 대비하기 위함.
		// "년월일시분초.확장자" 형식으로 변경해 본다.
		if (newOriginalFileName != null) {
			// 첨부파일이 있을 때만 이름 바꾸기 실행 한다.
			// 바꿀 파일명에 대한 포멧 문자열 만들기 : 년월일시분초 형식
			sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			// 바꿀 파일명 만들기
			newRenameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
			// 업로드된 파일의 확장자를 추출해서, 새 파일명에 붙여준다.
			newRenameFileName += "." + newOriginalFileName.substring(newOriginalFileName.lastIndexOf(".") + 1); // .에서부터
																												// 뒤로 +1
																												// 해서
			// 나온 글자 다 붙여준다.

			// 원본 파일명 rename 처리를 위해서 File 객체 만들기.
			File originFile = new File(savePath + "\\" + newOriginalFileName);
			File renameFile = new File(savePath + "\\" + newRenameFileName);

			// 이름 바꾸기 실행한다.
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
			krb.setRenameFile(newRenameFileName);

			if (originalFileName != null) {
				// 원래 첨부파일이 있었다면,
				// 원래 첨부파일을 폴더에서 삭제한다.
				new File(savePath + "\\" + renameFileName).delete();
			}
			// 업로드된 새 파일이 있다면

		} else if (originalFileName != null && deleteFlag != null && deleteFlag.equals("yes")) {
			// 원래 첨부파일이 있었고, 파일 삭제가 선택된 경우
			krb.setThumbNailFile(null);
			krb.setRenameFile(null);
			new File(savePath + "\\" + renameFileName).delete();
		} else if (originalFileName != null && (newOriginalFileName == null
				|| originalFileName.equals("newOriginalFileName") && new File(savePath + "\\" + renameFileName)
						.length() == new File(savePath + "\\" + newRenameFileName).length())) {
			// 원래 첨부파일이 있었고, 변경되지 않았을 때
			krb.setThumbNailFile(originalFileName);
			krb.setRenameFile(renameFileName);
		}

		result = ks.updateKR(krb); // 글 등록

		if (result < 1) {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "글 등록 실패");
			view.forward(request, response);
		} else {

			// 글저장
			int result1 = 0;
			int result2 = 0;
			ArrayList<KRContent> krclist = new ArrayList<KRContent>();

			int count = Integer.parseInt(mrequest.getParameter("countA"));
			int originRow = Integer.parseInt(mrequest.getParameter("originRow"));
			if(count >= originRow) {				
			for (int i = 1; i <= count; i++) {
				KRContent krc = new KRContent();
				krc.setKrBoardNo(krb.getKrBoardNo());
				krc.setKrBoardContent(mrequest.getParameter("content" + i));
				krc.setKrCount(i);
				String krcOriginFile = mrequest.getParameter("ofile" + i);
				String krcRenameFile = mrequest.getParameter("rfile" + i);
				String newKrcRenameFile = null;
				deleteFlag = mrequest.getParameter("deleteFlag"+i);


				String FileName = mrequest.getFilesystemName("upfile" + i);
				krc.setKrOriginFile(FileName);
				if (FileName != null) {
					String newRenameFile = sdf.format(new java.sql.Date(System.currentTimeMillis())) + i;
					newRenameFile += "." + FileName.substring(FileName.lastIndexOf(".") + 1);

					File oFile = new File(savePath + "\\" + FileName);
					File rFile = new File(savePath + "\\" + newRenameFile);

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

					krc.setKrRenameFile(newRenameFile);

					if (krcOriginFile != null) {
						// 원래 첨부파일이 있었다면,
						// 원래 첨부파일을 폴더에서 삭제한다.
						new File(savePath + "\\" + krcRenameFile).delete();
					}
					// 업로드된 새 파일이 있다면

				} else if (krcOriginFile != null && deleteFlag != null && deleteFlag.equals("yes")) {
					// 원래 첨부파일이 있었고, 파일 삭제가 선택된 경우
					krc.setKrOriginFile(null);
					krc.setKrRenameFile(null);
					new File(savePath + "\\" + renameFileName).delete();
				} else if (krcOriginFile != null && (FileName == null
						|| krcOriginFile.equals("FileName") && new File(savePath + "\\" + krcRenameFile)
								.length() == new File(savePath + "\\" + newRenameFileName).length())) {
					// 원래 첨부파일이 있었고, 변경되지 않았을 때
					krc.setKrOriginFile(krcOriginFile);
					krc.setKrRenameFile(krcRenameFile);
				}

				krclist.add(krc);
			}
			result1 = ks.updateKRC(krclist);
			} else {
				for(int i = count+1; i <= originRow;i++) {
					int deleteResult = ks.deleteKrConOne(krb.getKrBoardNo(), i);
					if(deleteResult < 1) {
						view = request.getRequestDispatcher("views/common/error.jsp");
						request.setAttribute("message", "글 내용 삭제 수정 실패");
						view.forward(request, response);
					}
				}
			}
			if (result < 1) {
				view = request.getRequestDispatcher("views/common/error.jsp");
				request.setAttribute("message", "글 내용 등록 실패");
				view.forward(request, response);
			} else {

				if (mrequest.getParameter("countB") != null) {
					ArrayList<KRContent> krclist2 = new ArrayList<KRContent>();
					int countB = (Integer.parseInt(mrequest.getParameter("countB")));
					
					for (int i = count+1; i <= countB; i++) {
						KRContent krc = new KRContent();
						krc.setKrBoardNo(krb.getKrBoardNo());
						krc.setKrBoardContent(mrequest.getParameter("content" + i));
						krc.setKrCount(i);
						String fileName = mrequest.getParameter("upfile" + i);
						krc.setKrOriginFile(fileName);

						if (fileName != null) {
							String insertRenameFile = sdf.format(new java.sql.Date(System.currentTimeMillis()));
							insertRenameFile += "." + fileName.substring(fileName.lastIndexOf(".") + 1);

							File ioFile = new File(savePath + "\\" + fileName);
							File irFile = new File(savePath + "\\" + insertRenameFile);

							if (!ioFile.renameTo(irFile)) {
								FileInputStream fin = new FileInputStream(ioFile);
								FileOutputStream fout = new FileOutputStream(irFile);
								int data = -1;
								byte[] buffer = new byte[1024];
								while ((data = fin.read(buffer, 0, buffer.length)) != -1) {
									fout.write(buffer, 0, buffer.length);
								}
								fin.close();
								fout.close();
								ioFile.delete();
							}
							ioFile.delete();

							krc.setKrRenameFile(insertRenameFile);
						}
						krclist2.add(krc);

					}
					result2 = ks.insertUpdateKrCon(krclist2);
					System.out.println(krclist);
					
					if (result2 > 0) {
						response.sendRedirect("/jmtgr/krlist");
					} else {
						view = request.getRequestDispatcher("views/common/error.jsp");
						request.setAttribute("message", "글 수정추가 실패");
						view.forward(request, response);
					}
				} else {
					response.sendRedirect("/jmtgr/krlist");
				}
			}
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
