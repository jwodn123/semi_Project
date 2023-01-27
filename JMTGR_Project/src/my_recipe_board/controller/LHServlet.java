package my_recipe_board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my_recipe_board.model.service.MyRecipeService;
import my_recipe_board.model.vo.LH;

/**
 * Servlet implementation class LikeHateServlet
 */
@WebServlet("/lh")
public class LHServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LHServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		LH lh = new LH();
		lh.setUserId(request.getParameter("userid"));
		lh.setMrBoardNo(Integer.parseInt(request.getParameter("mrno")));
		String key = null;

		if (request.getParameter("lh").equals("like")) {
			key = "Y";
			lh.setLikes("key");

			int result = new MyRecipeService().insertLh(lh, key);
			RequestDispatcher view = null;

			if (result > 0) {
				response.sendRedirect("/jmtgr/mrdetail.ss?mrno=" + lh.getMrBoardNo());
			} else {
				view = request.getRequestDispatcher("views/common/error.jsp");
				request.setAttribute("message", "좋아요는 한번만 가능합니다. ");
				view.forward(request, response);
			}	return;
		} else if (request.getParameter("lh").equals("hate")) {
			key = "N";
			lh.setHates("key");
		}
		int result = new MyRecipeService().insertLh(lh, key);
		RequestDispatcher view = null;

		if (result > 0) {
			response.sendRedirect("/jmtgr/mrdetail.ss?mrno=" + lh.getMrBoardNo());
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "싫어요는 한번만 가능합니다.");
			view.forward(request, response);
		}	return;
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
