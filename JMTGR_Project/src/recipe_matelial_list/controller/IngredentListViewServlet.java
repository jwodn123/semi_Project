package recipe_matelial_list.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipe_matelial_list.model.service.MatrialListService;
import recipe_matelial_list.model.vo.MaterialList;

/**
 * Servlet implementation class IngredentListViewServlet
 */
@WebServlet("/ilistview")
public class IngredentListViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IngredentListViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한식 재료리스트 view 컨트롤러
		request.setCharacterEncoding("utf-8");
		String listaddform = request.getParameter("listaddform");
		int krno = Integer.parseInt(request.getParameter("krno"));
		ArrayList<MaterialList> mvlist = new MatrialListService().ingreView(krno);
		
		if(mvlist != null) {
		RequestDispatcher view = request.getRequestDispatcher("views/material_list/koreaMaterialList.jsp");
		request.setAttribute("mvlist", mvlist);
		request.setAttribute("listaddform", listaddform);
		view.forward(request, response);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "재료리스트를 불러오는데 실패했습니다.");
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
