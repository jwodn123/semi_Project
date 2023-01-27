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
 * Servlet implementation class AdminIngredentInsertServlet
 */
@WebServlet("/adinsertingre")
public class AdminIngredentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIngredentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//재료추가 컨트롤러 
				//추가 후 view 컨트롤러
				
				request.setCharacterEncoding("utf-8");
				Material mlist = new Material();
				mlist.setMaNo(Integer.parseInt(request.getParameter("mano")));
				mlist.setMaName(request.getParameter("maname"));
			
				
				
				int result = new MaterialService().insertMatrial(mlist);
				if(result > 0) {
					ArrayList<Material> list = new MaterialService().selectList();
					
					RequestDispatcher view = request.getRequestDispatcher("views/material_list/material.jsp");
					request.setAttribute("list", list);
					view.forward(request, response);
					
				}else {
					RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
					request.setAttribute("message", "이미 등록했거나 재료 등록이  실패했습니다.");
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
