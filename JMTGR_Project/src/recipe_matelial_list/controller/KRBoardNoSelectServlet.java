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

import korea_recipe_board.model.vo.KRBoard;
import recipe_matelial_list.model.service.MatrialListService;
import recipe_matelial_list.model.vo.MaterialList;

/**
 * Servlet implementation class KRBoardNoSelectServlet
 */
@WebServlet("/krbno")
public class KRBoardNoSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KRBoardNoSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		 KRBoard boardno = new KRBoard();
		 boardno.setKrBoardNo(Integer.parseInt(request.getParameter("krno")));
		if(boardno != null ) {
			HttpSession boardNo = request.getSession(false);
			
			ArrayList<MaterialList> mvlist = new MatrialListService().ingreView(boardno.getKrBoardNo());
			RequestDispatcher view = request.getRequestDispatcher("views/material_list/koreaMaterialList.jsp");
			request.setAttribute("mvlist", mvlist);
			boardNo.setAttribute("boardno", boardno);
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
