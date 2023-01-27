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
 * Servlet implementation class QuestionUpdateServlet
 */
@WebServlet("/qupdate")
public class QuestionUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// 관리자용 공지 사항 수정 처리용 컨트롤러
        
		// 관리자용 공지사항 수정 처리용 컨트롤러

	      // 1. multipart 방식으로 인코딩되어서 전송되었는지 확인함.

	      RequestDispatcher view = null;
	      if (!ServletFileUpload.isMultipartContent(request)) { // 멀티파트방식으로 전송되면 true !를 붙여서 false로 만들어준다.
	         view = request.getRequestDispatcher("views/common/error.jsp");
	         request.setAttribute("message", "form의 enctype='multipart/form-data' 속성 누락");
	         view.forward(request, response);
	      }

	      // 2. 업로드할 파일의 용량 제한 : 10Mbyte 로 제한.
	      int maxSize = 1024 * 1024 * 10; // byte에서 출발 byte->kb->mb

	      // 3. 업로드되는 파일의 저장 폴더 지정하기.
	      String savePath = request.getSession().getServletContext().getRealPath("/resources/qupfiles"); // getRealPath 까지  root경로를 가져온다. 따라서 " " 안에 루트안에서부터 시작하기위해 /로시작한다.
	                                                                              

	      // 4. request 를 MultipartRequest 로 변환해야 한다.
	      // cos.jar 가 제공하는 클래스를 사용함.
	      // 전송 온 파일을 자동 지정 폴더에 저장됨.
	      MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy()); // 매개변수 : request, 파일저장경로, 파일사이즈, 파일인코딩, cos자르안에 있는 클래스

	      // 5. 데이터베이스에 기록할 값 꺼내기. : mrequest로 추출한다.
	      Question question = new Question();
	      question.setQusTitle(mrequest.getParameter("title"));
	      question.setUserId(mrequest.getParameter("userid"));
	      question.setQusContent(mrequest.getParameter("content"));
	      question.setQusNo(Integer.parseInt(mrequest.getParameter("qnum")));
	      
	      // 첨부파일 삭제 여부 추출
	      String deleteFlag = mrequest.getParameter("deleteFlag");
	      
	      String qusOriginalFileName = mrequest.getParameter("ofile");
	      String qusRenameFileName = mrequest.getParameter("rfile");
	      String newQusRenameFileName = null;
	      // 서버에 새로 업로드된 파일명 추출하기
	      String newQusOriginalFileName = mrequest.getFilesystemName("upfile"); // 파일 명만 추출
	      question.setQusOriginalFileName(newQusOriginalFileName);
	      
	      // 원래 파일과 새로 업로드된 파일의 이름이 같고 파일 용량도 동일하면 원래 이름 그대로 객체에 기록한다.
	      // 첨부파일이 없었는데 추가된 경우와 있었는데 변경된 경우 둘 다 파일명 바꾸기한다.

	      // 첨부된 파일의 파일명 바꾸기 하려면....
	      // 저장 폴더에 같은 이름의 파일이 있을경우 대비하기 위함.
	      // "년월일시분초.확장자" 형식으로 변경해 본다.
	      if (newQusOriginalFileName != null) {
	         // 첨부파일이 있을 때만 이름 바꾸기 실행 한다.
	         // 바꿀 파일명에 대한 포멧 문자열 만들기 : 년월일시분초 형식
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	         // 바꿀 파일명 만들기
	         newQusOriginalFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
	         // 업로드된 파일의 확장자를 추출해서, 새 파일명에 붙여준다.
	         newQusOriginalFileName += "." + newQusOriginalFileName.substring(newQusOriginalFileName.lastIndexOf(".") + 1); // .에서부터 뒤로 +1 해서
	                                                                              // 나온 글자 다 붙여준다.

	         // 원본 파일명 rename 처리를 위해서 File 객체 만들기.
	         File qusOriginFile = new File(savePath + "\\" + newQusOriginalFileName);
	         File qusRenameFile = new File(savePath + "\\" + newQusOriginalFileName);

	         // 이름 바꾸기 실행한다.
	         if (!qusOriginFile.renameTo(qusRenameFile)) {
	            // renameTo 메소드가 실패할 경우엔 직접 바꾸기한다.
	            // 원본파일 내용 읽어서, 복사본에 기록하고 완료되면, 원본 파일 삭제한다.
	            FileInputStream fin = new FileInputStream(qusOriginFile);
	            FileOutputStream fout = new FileOutputStream(qusRenameFile);
	            int data = -1;
	            byte[] buffer = new byte[1024];
	            while ((data = fin.read(buffer, 0, buffer.length)) != -1) {
	               fout.write(buffer, 0, buffer.length);
	            }
	            fin.close();
	            fout.close();
	            qusOriginFile.delete();
	         }
	         question.setQusRenameFileName(newQusOriginalFileName);
	         
	         if(qusOriginalFileName != null) {
	            // 원래 첨부파일이 있었다면,
	            // 원래 첨부파일을 폴더에서 삭제한다.
	            new File(savePath + "\\" + qusRenameFileName).delete();
	         }
	         // 업로드된 새 파일이 있다면
	         
	         else if(qusOriginalFileName != null 
	               && qusOriginalFileName.equals("newOriginalFileName") 
	               && new File(savePath + "\\" + qusRenameFileName).length() == new File(savePath + "\\" + newQusOriginalFileName).length()) {
	            // 원래 첨부파일이 있었고, 변경되지 않았을 때            
	            question.setQusOriginalFileName(qusOriginalFileName);
	            question.setQusRenameFileName(qusRenameFileName);
	         }else if(qusOriginalFileName != null && deleteFlag != null && deleteFlag.equals("yes")) {
	            // 원래 첨부파일이 있었고, 파일 삭제가 선택된 경우
	            question.setQusOriginalFileName(null);
	            question.setQusRenameFileName(null);
	            new File(savePath + "\\" + qusRenameFileName).delete();
	         }
	      }

	      // 6. 서비스 객체생성하고 메소드로 notice 객체 전달하고 처리된 결과 받기
	      int result = new QuestionService().updateQuestion(question);

	      // 7. 받은 결과로 성공/실패 페이지 내보내기.
	      if (result > 0) {
	         response.sendRedirect("qlist");
	      } else {
	         view = request.getRequestDispatcher("views/common/error.jsp");
	         request.setAttribute("message", question.getQusNo() + "번 공지사항 수정 실패");
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
