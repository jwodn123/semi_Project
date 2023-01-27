package question.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.model.service.QuestionService;
import question.model.vo.Question;

/**
 * Servlet implementation class QestionUpdateViewServlet
 */
@WebServlet("/qupview")
public class QestionUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QestionUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자용 공지사항 수정페이지로 이동 처리용 컨트롤러
		
				int qusNo = Integer.parseInt(request.getParameter("qnum"));
				int currentPage = Integer.parseInt(request.getParameter("page"));
				
				Question question = new QuestionService().selectQuestion(qusNo);
				
				RequestDispatcher view = null;
				if(question != null) {
					view = request.getRequestDispatcher("views/question/questionUpdateView.jsp");
					request.setAttribute("question", question);
					request.setAttribute("page", currentPage);
					view.forward(request, response);
				} else {
					view = request.getRequestDispatcher("views/common/error.jsp");
					request.setAttribute("message", qusNo + "번 글에 대한 수정페이지 요청 실패");
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
