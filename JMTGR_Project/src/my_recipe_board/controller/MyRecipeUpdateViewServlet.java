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
import my_recipe_board.model.vo.MrContent;
import my_recipe_board.model.vo.MyRecipe;

/**
 * Servlet implementation class MyRecipeUpdateViewServlet
 */
@WebServlet("/mrupview.en")
public class MyRecipeUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyRecipeUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mrno = Integer.parseInt(request.getParameter("mrno"));
		MyRecipeService ms = new MyRecipeService();
		MyRecipe mr = ms.selectMyRecipe(mrno);
		ArrayList<MrContent> mrclist = ms.selectMrc(mrno);
		
		RequestDispatcher view = null;
		if(mr != null) {
			view = request.getRequestDispatcher("views/mrboard/MyRecipeUpdateView.jsp");
			request.setAttribute("mr", mr);
			request.setAttribute("mrclist", mrclist);
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "수정페이지 이동 실패");
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
