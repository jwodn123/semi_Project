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
 * Servlet implementation class MyRecipeOrderByServlet
 */
@WebServlet("/orderbyS")
public class MyRecipeOrderByServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyRecipeOrderByServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 1;
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		int limit = 6;
		MyRecipeService mrservice = new MyRecipeService();

		int listCount = mrservice.getListCount();
		int maxPage = (int) (((double) listCount / limit) + 0.9);
		// 현재페이지가 속한 그룹의 시작 페이지 수 지정
		// 예 : currentPage 가 35 이면 페이지 그룹이 10일때 시작페이지는 31이 된다.
		int startPage = (((int) ((double) currentPage / limit + 0.9)) - 1) * limit + 1;
		int endPage = startPage + limit - 1;
		if (maxPage < endPage) {
			endPage = maxPage;
		}
		ArrayList<MyRecipe> mrlist = new ArrayList<MyRecipe>();
		RequestDispatcher view = null;	
		int orderby = 0;
		
		if(request.getParameter("orderby").equals("title")) {
			orderby = 1;
			mrlist = mrservice.Orderby(currentPage, limit, orderby);

		} else if(request.getParameter("orderby").equals("titledesc")){
			orderby = 2;			
			mrlist = mrservice.Orderby(currentPage, limit, orderby);	
		}
		
		if (mrlist.size() > 0) {
			view = request.getRequestDispatcher("views/mrboard/MyRecipeListView.jsp");
			request.setAttribute("mrlist", mrlist);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "한식 글 정렬 실패 실패");
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
