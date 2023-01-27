package my_recipe_board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

import my_recipe_board.model.service.MyRecipeService;
import my_recipe_board.model.vo.MrContent;
import my_recipe_board.model.vo.MyRecipe;

/**
 * Servlet implementation class MyRecipeInsertServlet
 */
@WebServlet("/mrinsert.en")
public class MyRecipeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyRecipeInsertServlet() {
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
		MyRecipeService ms = new MyRecipeService();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		RequestDispatcher view = null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "form 의 entype='multipart/form-data' 속성누락됨");
			view.forward(request, response);
		}

		int maxSize = 1024 * 1024 * 50;

		String savePath = request.getSession().getServletContext().getRealPath("/resources/mrthumbnail");

		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		MyRecipe myRecipe = new MyRecipe();
		myRecipe.setMrBoardTitle(mrequest.getParameter("title"));
		myRecipe.setUserId(mrequest.getParameter("userid"));

		String originalFileName = mrequest.getFilesystemName("thumbnail");
		myRecipe.setMrboardoriginFile(originalFileName);

		if (originalFileName != null) {
			String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
			renameFileName += "." + originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

			File originFile = new File(savePath + "\\" + originalFileName);
			File renameFile = new File(savePath + "\\" + renameFileName);

			if (!originFile.renameTo(renameFile)) {
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

			myRecipe.setMrboardrenameFile(renameFileName);
		}
		// 글 등록
		result = ms.insertMyRecipe(myRecipe);
		
		int mrno = ms.selectMrno(myRecipe.getUserId());
		
		// 글 등록 여뷰 홧인
		if (result > 0) {
			int result1 = 0;
			ArrayList<MrContent> mrclist = new ArrayList<MrContent>();

				int count = Integer.parseInt(mrequest.getParameter("count"));

				for (int i = 1; i <= count; i++) {
					MrContent mrc = new MrContent();
					mrc.setMrCount(i);
					mrc.setMrBoardNo(mrno);
					mrc.setMrBoardContent(mrequest.getParameter("content" + i));
					String fileName = mrequest.getFilesystemName("upfile" + i);
					mrc.setMrOriginFile(fileName);
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

						mrc.setMrRenamefile(renameFile);
					}
					
					mrclist.add(mrc);
				}
			
			result1 = ms.insertMrCon(mrclist);
			if (result1 > 0) {
				response.sendRedirect("/jmtgr/mrlist.ss");
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
