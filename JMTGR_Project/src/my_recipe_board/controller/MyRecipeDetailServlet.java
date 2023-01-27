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
import my_recipe_board.model.vo.Comment;
import my_recipe_board.model.vo.MrContent;
import my_recipe_board.model.vo.MyRecipe;
import recipe_matelial_list.model.service.MatrialListService;
import recipe_matelial_list.model.vo.MaterialList;


/**
 * Servlet implementation class MyRecipeDetailServlet
 */
@WebServlet("/mrdetail.ss")
public class MyRecipeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyRecipeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 나만의 레시피 글 상세보기
		int mrno = Integer.parseInt(request.getParameter("mrno"));
		MyRecipeService ms = new MyRecipeService();
		MyRecipe myrecipe =   ms.selectMyRecipe(mrno);
		ArrayList<MrContent>  mrclist = ms.selectMrc(mrno);
		ArrayList<Comment> colist =  ms.selectComment(mrno);
		ArrayList<MaterialList> mvlist = new MatrialListService().MringreView(mrno);
		int[] likes = new MyRecipeService().selectLh(mrno);
		int result = ms.updateReadCount(mrno);
		RequestDispatcher view = null;
		
		if (myrecipe != null || mvlist != null) {
			if(request.getParameter("cono") != null) {
			int cono = Integer.parseInt(request.getParameter("cono"));	
			view = request.getRequestDispatcher("views/mrboard/MyRecipeDetailView.jsp");
			request.setAttribute("myrecipe", myrecipe);
			request.setAttribute("mrclist", mrclist);
			request.setAttribute("comment", colist);
			request.setAttribute("lh", likes);
			request.setAttribute("mvlist", mvlist);
			request.setAttribute("cono", cono);
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("views/mrboard/MyRecipeDetailView.jsp");
			request.setAttribute("myrecipe", myrecipe);
			request.setAttribute("mrclist", mrclist);
			request.setAttribute("comment", colist);
			request.setAttribute("mvlist", mvlist);
			request.setAttribute("lh", likes);
			view.forward(request, response);
		}	
			

		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", mrno + "번 글 상세보기 조회 실패");
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
