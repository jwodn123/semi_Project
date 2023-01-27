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
 * Servlet implementation class MrIngredentDeleteServlet
 */
@WebServlet("/mringredelete")
public class MrIngredentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MrIngredentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 나만의 레시피 재료 리스트 삭제 컨트롤러
		int mano = Integer.parseInt(request.getParameter("mano"));
		
		int result = new MatrialListService().MrdeleteList(mano);
		if(result > 0) {
			int mrno = Integer.parseInt(request.getParameter("mrno"));
			ArrayList<MaterialList> mvlist = new MatrialListService().MringreView(mrno);
			RequestDispatcher view = request.getRequestDispatcher("views/material_list/mrboardMaterialList.jsp");
			request.setAttribute("mvlist", mvlist);
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
