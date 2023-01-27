package question.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import question.model.service.QuestionService;
import question.model.vo.Question;



/**
 * Servlet implementation class QuestionOriginUpdateServlet
 */
@WebServlet("/qoriginup")
public class QuestionOriginUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionOriginUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시 원글 수정 처리용 컨트롤러
		// 첨부파일 전송

		// 1. multipart 방식으로 인코딩되어서 전송되었는지 확인함
		RequestDispatcher view = null;
		if (!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "form 의 enctype='multipart/form-date' 속성 누락");
			view.forward(request, response);
		}

		// 2. 업로드할 파일의 용량 제한 : 10Mbyte 로 제한한다면
		int maxSize = 1024 * 1024 * 10;

		// 3. 업로드 되는 파일의 저장 폴더 지정하기
		String savePath = request.getSession().getServletContext().getRealPath("/resources/qupfiles");

		// 4. request 를 MultpartRequest 로 변환해야 함
		// cos.jar 가 제공하는 클래스르 사용함
		// 전송온 파일은 자동 지정 폴더에 저장됨
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		// 5. 데이터베이스에 기록할 값 꺼내기
		// mrequest 로 수정해야함
		Question question = new Question();

		question.setQusTitle(mrequest.getParameter("title"));
		question.setQusContent(mrequest.getParameter("content"));

		int qusNo = Integer.parseInt(mrequest.getParameter("qnum"));
		question.setQusNo(qusNo);
		int currentPage = Integer.parseInt(mrequest.getParameter("page"));

		// 이전 등록 파일 삭제 여부 값 추출
		String deleteFlag = mrequest.getParameter("delflag");

		// 이전 등록 파일명 추출

		String originFilePath = mrequest.getParameter("ofile");
		String renameFilePath = mrequest.getParameter("rfile");

		// 서버에 업로드된 파일명 추출하기
		String originalFileName = mrequest.getFilesystemName("upfile");

		// 원래 파일과 새로 없로드된 파일의 이름이 같고, 파일 용량도 동일하면 원래 이름 그래도 객체에 기록함
		// 업로드된 파일의 File 객체 만들기
		File newOriginFile = new File(savePath + "/" + originalFileName);
		// 이전 저장된 파일의 File 객체 만들기
		File originFile = new File(savePath + "/" + renameFilePath);

		// 첨부파일이 없었는데 추가된 경우와
		// 첨부파일이 있는데 변경된 경우

		if (originalFileName != null) {
			question.setQusOriginalFileName(originalFileName);

			// 새로운
			// 바꿀 파일명에 대한 포맷 문자열 만들기 : 년월일시분초 형식으로
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

			// 바꿀 파일명 만들기
			String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));

			// 업로드된 파일의 확장자를 추출해서, 새 파일명에 붙여줌
			renameFileName += "." + originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

			// 원본 파일명 rename 처리를 위해서 File 객체 만들기
			File renameFile = new File(savePath + "\\" + renameFileName);

			// 이름바꾸기 실행
			if (!newOriginFile.renameTo(renameFile)) {
				// renameTo() 메소드가 실패(false)한 경우에 직접 바꾸기함
				// 원본 파일 내용 읽어서, 복사본에 기록하고 완료되면, 원본 파일 삭제
				FileInputStream fin = new FileInputStream(newOriginFile);
				FileOutputStream fout = new FileOutputStream(renameFile);

				int data = -1;
				byte[] buffer = new byte[1024];

				while ((data = fin.read(buffer, 0, buffer.length)) != -1) {
					fout.write(buffer, 0, buffer.length);
				}
				fin.close();
				fout.close();
				newOriginFile.delete(); // 새로 업로드된 원본 파일 삭제
			} // 직접 이름 바꾸기
			question.setQusRenameFileName(renameFileName);

			// 이전 첨부 파일이 있었다면
			if (originFilePath != null) {
				originFile.delete();
			}
		} else if (originFilePath != null && deleteFlag != null && deleteFlag.equals("yes")) {
			// 원래 첨부파일이 있었는데 파일 삭제가 선택된 경우
			question.setQusOriginalFileName(null);
			question.setQusRenameFileName(null);

			// 폴더에 저장된 파일도 삭제함
			originFile.delete();

		} else if (originFilePath != null
				&& (originFilePath.equals(originalFileName) || newOriginFile.length() == originFile.length())) {
			// 원래 첨부파일이 있었는데 변경되지 않는 경우
			question.setQusOriginalFileName(originFilePath);
			question.setQusRenameFileName(renameFilePath);
		}
		// 6. 서비스 객체 생성하고 메소드로 notice 객체 전달하고 처리된 결과 받기
		// 처리된 결과 받기
		int result = new QuestionService().updateOrigin(question);

		// 7. 밥은 결과로 성공 / 실패 페이지 내보내기
		if (result > 0) {
			response.sendRedirect("qlist?page=" + currentPage);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", qusNo + "새 게시원글 수정 처리 실패");
			view.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
