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
 * Servlet implementation class AdminIngredentDeleteServlet
 */
@WebServlet("/adidelete")
public class AdminIngredentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIngredentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자용 재료 삭제 컨트롤러
		int mano = Integer.parseInt(request.getParameter("mano"));
		
		int result = new MaterialService().deleteList(mano);
		
		if(result > 0) {
			ArrayList<Material> list = new MaterialService().selectList();
			
			RequestDispatcher view = request.getRequestDispatcher("views/material_list/Material.jsp");
			request.setAttribute("list", list);
			view.forward(request, response);
			
			
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "재료 삭제 실패");
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
