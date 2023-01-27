package recipe_matelial_list.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import my_recipe_board.model.vo.MyRecipe;
import recipe_matelial_list.model.service.MatrialListService;
import recipe_matelial_list.model.vo.MaterialList;

/**
 * Servlet implementation class MRBoardNoSelectServlet
 */
@WebServlet("/mrbno")
public class MRBoardNoSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MRBoardNoSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		 MyRecipe boardno = new MyRecipe();
		 boardno.setMrBoardNo(Integer.parseInt(request.getParameter("mrno")));
		
		if(boardno != null ) {
			HttpSession boardNo = request.getSession(false);
			ArrayList<MaterialList> mvlist = new MatrialListService().MringreView(boardno.getMrBoardNo());
			boardNo.setAttribute("boardno", boardno);
			request.setAttribute("mvlist", mvlist);
			RequestDispatcher view = request.getRequestDispatcher("views/material_list/mrboardMaterialList.jsp");
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
