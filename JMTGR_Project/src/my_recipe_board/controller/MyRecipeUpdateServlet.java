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
 * Servlet implementation class MyRecipeUpdateServlet
 */
@WebServlet("/mrupdate.en")
public class MyRecipeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyRecipeUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 나만의 레시피 글 수정
		int result = 0;
		MyRecipeService ms = new MyRecipeService();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		RequestDispatcher view = null;
		if (!ServletFileUpload.isMultipartContent(request)) { // 멀티파트방식으로 전송되면 true !를 붙여서 false로 만들어준다.
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "form의 enctype='multipart/form-data' 속성 누락");
			view.forward(request, response);
		}

		int maxSize = 1024 * 1024 * 50;

		String savePath = request.getSession().getServletContext().getRealPath("/resources/mrthumbnail");

		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		MyRecipe mrb = new MyRecipe();
		mrb.setMrBoardTitle(mrequest.getParameter("title"));
		mrb.setUserId(mrequest.getParameter("userid"));
		mrb.setMrBoardNo(Integer.parseInt(mrequest.getParameter("mrno")));

		// 첨부파일 삭제 여부 추출
		String deleteFlag = mrequest.getParameter("deleteFlag");

		String originalFileName = mrequest.getParameter("ofile");
		String renameFileName = mrequest.getParameter("rfile");
		String newRenameFileName = null;
		// 서버에 새로 업로드된 파일명 추출하기
		String newOriginalFileName = mrequest.getFilesystemName("thumbnail"); // 파일 명만 추출
		mrb.setMrboardoriginFile(newOriginalFileName);

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
			mrb.setMrboardrenameFile(newRenameFileName);

			if (originalFileName != null) {
				// 원래 첨부파일이 있었다면,
				// 원래 첨부파일을 폴더에서 삭제한다.
				new File(savePath + "\\" + renameFileName).delete();
			}
			// 업로드된 새 파일이 있다면

		} else if (originalFileName != null && deleteFlag != null && deleteFlag.equals("yes")) {
			// 원래 첨부파일이 있었고, 파일 삭제가 선택된 경우
			mrb.setMrboardoriginFile(null);
			mrb.setMrboardrenameFile(null);
			new File(savePath + "\\" + renameFileName).delete();
		} else if (originalFileName != null && (newOriginalFileName == null
				|| originalFileName.equals("newOriginalFileName") && new File(savePath + "\\" + renameFileName)
						.length() == new File(savePath + "\\" + newRenameFileName).length())) {
			// 원래 첨부파일이 있었고, 변경되지 않았을 때
			mrb.setMrboardoriginFile(originalFileName);
			mrb.setMrboardrenameFile(renameFileName);
		}

		result = ms.updateMR(mrb);// 글 등록

		if (result < 1) {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "글 등록 실패");
			view.forward(request, response);
		} else {

			// 글저장
			int result1 = 0;
			int result2 = 0;
			ArrayList<MrContent> mrclist = new ArrayList<MrContent>();

			int count = Integer.parseInt(mrequest.getParameter("countA"));
			int originRow = Integer.parseInt(mrequest.getParameter("originRow"));
			if(count >= originRow) {				
			for (int i = 1; i <= count; i++) {
				MrContent mrc = new MrContent();
				mrc.setMrBoardNo(mrc.getMrBoardNo());
				mrc.setMrBoardContent(mrequest.getParameter("content" + i));
				mrc.setMrCount(i);
				String mrcOriginFile = mrequest.getParameter("ofile" + i);
				String mrcRenameFile = mrequest.getParameter("rfile" + i);
				String newMrcRenameFile = null;
				deleteFlag = mrequest.getParameter("deleteFlag"+i);


				String FileName = mrequest.getFilesystemName("upfile" + i);
				mrc.setMrOriginFile(FileName);
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

					mrc.setMrRenamefile(newRenameFile);

					if (mrcOriginFile != null) {
						// 원래 첨부파일이 있었다면,
						// 원래 첨부파일을 폴더에서 삭제한다.
						new File(savePath + "\\" + mrcRenameFile).delete();
					}
					// 업로드된 새 파일이 있다면

				} else if (mrcOriginFile != null && deleteFlag != null && deleteFlag.equals("yes")) {
					// 원래 첨부파일이 있었고, 파일 삭제가 선택된 경우
					mrc.setMrOriginFile(null);
					mrc.setMrRenamefile(null);
					new File(savePath + "\\" + renameFileName).delete();
				} else if (mrcOriginFile != null && (FileName == null
						|| mrcOriginFile.equals("FileName") && new File(savePath + "\\" + mrcRenameFile)
								.length() == new File(savePath + "\\" + newRenameFileName).length())) {
					// 원래 첨부파일이 있었고, 변경되지 않았을 때
					mrc.setMrOriginFile(mrcOriginFile);
					mrc.setMrRenamefile(mrcRenameFile);
				}

				mrclist.add(mrc);
			}
			result1 = ms.updateMrc(mrclist);
			
			
			} else {
				for(int i = count+1; i <= originRow;i++) {
					int deleteResult = ms.deletemrConOne(mrb.getMrBoardNo(), i);
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
					ArrayList<MrContent> mrclist2 = new ArrayList<MrContent>();
					int countB = (Integer.parseInt(mrequest.getParameter("countB")));
					
					for (int i = count+1; i <= countB; i++) {
						MrContent mrc = new MrContent();
						mrc.setMrBoardNo(mrc.getMrBoardNo());
						mrc.setMrBoardContent(mrequest.getParameter("content" + i));
						mrc.setMrCount(i);
						String fileName = mrequest.getParameter("upfile" + i);
						mrc.setMrOriginFile(fileName);

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

							mrc.setMrRenamefile(insertRenameFile);
						}
						mrclist2.add(mrc);

					}
					result2 = ms.insertUpdateMrCon(mrclist2);
					if (result2 > 0) {
						response.sendRedirect("/jmtgr/mrlist.ss");
					} else {
						view = request.getRequestDispatcher("views/common/error.jsp");
						request.setAttribute("message", "글 수정추가 실패");
						view.forward(request, response);
					}
				} else {
					response.sendRedirect("/jmtgr/mrlist.ss");
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
