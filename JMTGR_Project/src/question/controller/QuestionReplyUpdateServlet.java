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
 * Servlet implementation class QuestionAnswerUpdateServlet
 */
@WebServlet("/qreplyup.en")
public class QuestionReplyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionReplyUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int currentPage = Integer.parseInt(request.getParameter("page"));
		
	Reply rpy = new Reply();
	rpy.setQusReplyContent(request.getParameter("replycontent"));
	int result = new QuestionService().updateReply(rpy);
	if(result > 0) {
		response.sendRedirect("/jmtgr/qdetail?qusno="+rpy.getQusNo());
	} else {
		RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
		request.setAttribute("message", "댓글 수정 실패");
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
