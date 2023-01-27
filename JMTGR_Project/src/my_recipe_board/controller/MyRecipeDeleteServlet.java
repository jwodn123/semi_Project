package my_recipe_board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my_recipe_board.model.service.MyRecipeService;

/**
 * Servlet implementation class MyRecipeDeleteServlet
 */
@WebServlet("/mrdel")
public class MyRecipeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyRecipeDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 나만의 레시피 글 삭제
		int mrno = Integer.parseInt(request.getParameter("mrno"));
		MyRecipeService ms = new MyRecipeService();
		int lh = ms.deleteLH(mrno);
		int co = ms.deleteAllComment(mrno);
		int result1 = ms.deleteMrMaterial(mrno);		
		int result = new MyRecipeService().delectMyRecipe(mrno);
	
		
		if( result > 0) {
			response.sendRedirect("/jmtgr/mrlist.ss");
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "글 삭제 실패");
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
