package my_recipe_board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my_recipe_board.model.service.*;
import my_recipe_board.model.vo.Comment;

/**
 * Servlet implementation class insertComment
 */
@WebServlet("/inCom.en")
public class insertComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public insertComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Comment co = new Comment();
		co.setCommContent(request.getParameter("comcontent"));
		co.setMrBoardNo(Integer.parseInt(request.getParameter("mrno")));
		co.setUserId(request.getParameter("userid"));
		
		int result = new MyRecipeService().insertCom(co);
		RequestDispatcher view = null;
		
		if (result > 0) {
			response.sendRedirect("/jmtgr/mrdetail.ss?mrno=" + co.getMrBoardNo());
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "댓글 등록 실패");
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
