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
import question.model.vo.Reply;

/**
 * Servlet implementation class QuestionDetailViewSerlvet
 */
@WebServlet("/qdetail")
public class QuestionDetailViewSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuestionDetailViewSerlvet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 게시글 상세보기 처리용 컨트롤러

		int qusNo = Integer.parseInt(request.getParameter("qnum"));
		int currentPage = 1;
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		QuestionService qservice = new QuestionService();

		// 조회수 1 증가 처리 : update
		qservice.addReadCount(qusNo);

		// 글 번호에 대한 게시글 조회 : select
		Question question = qservice.selectQuestion(qusNo);
		Reply reply = null;
		reply = qservice.selectReplyOne(qusNo);
		RequestDispatcher view = null;
		if (reply != null) {
			if (question != null) {
				view = request.getRequestDispatcher("views/question/questionDetailView.jsp");
				request.setAttribute("question", question);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("reply", reply);
				view.forward(request, response);
			} else {
				view = request.getRequestDispatcher("views/common/error.jsp");
				request.setAttribute("message", qusNo + "번 글 상세보기 조회 실패!");
				view.forward(request, response);
			}
		}else {
			if (question != null) {
				view = request.getRequestDispatcher("views/question/questionDetailView.jsp");
				request.setAttribute("question", question);
				request.setAttribute("currentPage", currentPage);
				view.forward(request, response);
			}
			else {
				view = request.getRequestDispatcher("views/common/error.jsp");
				request.setAttribute("message", qusNo + "번 글 상세보기 조회 실패!");
				view.forward(request, response);
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
