package recipe_matelial_list.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recipe_matelial_list.model.service.MaterialService;
import recipe_matelial_list.model.vo.Material;

/**
 * Servlet implementation class IngredentSearchServlet
 */
@WebServlet("/ingresearch")
public class IngredentSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IngredentSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//재료 검색 컨트롤러
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getParameter("action");
		String listaddform = request.getParameter("listaddform");
		String keyword = null;
		
		keyword = request.getParameter("keyword");

		MaterialService mservice = new MaterialService();
		ArrayList<Material> list = null;
		
		list = mservice.selectSearchMaName(keyword); 
		
		RequestDispatcher view = null;
		if(list.size() > 0 ) {
			view = request.getRequestDispatcher("views/material_list/koreaMaterialList.jsp");
			request.setAttribute("list", list);
			request.setAttribute("listaddform", listaddform);
			
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", action + "검색에 대한" + keyword + "존재하지 않습니다");
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
