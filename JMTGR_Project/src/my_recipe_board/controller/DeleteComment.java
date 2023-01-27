package my_recipe_board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my_recipe_board.model.service.*;

/**
 * Servlet implementation class DeleteComment
 */
@WebServlet("/delcom")
public class DeleteComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cono = Integer.parseInt(request.getParameter("cono"));
		int mrno = Integer.parseInt(request.getParameter("mrno"));
		int result = new MyRecipeService().deleteComment(cono);
		RequestDispatcher view = null;
		
		if(result > 0) {
			response.sendRedirect("/jmtgr/mrdetail.ss?mrno="+mrno);
			
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "댓글 삭제 실패");
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
