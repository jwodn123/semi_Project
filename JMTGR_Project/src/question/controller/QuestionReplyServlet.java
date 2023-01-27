package question.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.model.service.QuestionService;
import question.model.vo.Reply;


/**
 * Servlet implementation class QuestionAnswerServlet
 */
@WebServlet("/qreply.en")
public class QuestionReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // 게시글 댓글 등록 처리용 컨트롤러
	      
		Reply rpy = new Reply();
		rpy.setQusReplyContent(request.getParameter("content"));
		rpy.setQusNo(Integer.parseInt(request.getParameter("replyqno")));
		int result = new QuestionService().insertReply(rpy);
		RequestDispatcher view = null;
		if (result > 0) {
			response.sendRedirect("/jmtgr/qdetail?qnum=" + rpy.getQusNo());
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "댓글 등록 실패");
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
