package my_recipe_board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my_recipe_board.model.service.MyRecipeService;
import my_recipe_board.model.vo.MyRecipe;

/**
 * Servlet implementation class MyRecipeSearchT
 */
@WebServlet("/mrsearch.en")
public class MyRecipeSearchT extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyRecipeSearchT() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mrtitle = request.getParameter("keyword");
		ArrayList<MyRecipe> mrlist = new MyRecipeService().searchMRT(mrtitle);

		RequestDispatcher view = null;
		if(mrlist != null) {
			view = request.getRequestDispatcher("views/mrboard/MrSearchView.jsp");
			request.setAttribute("mrlist", mrlist);
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("masseage", "조회결과 없음");
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
