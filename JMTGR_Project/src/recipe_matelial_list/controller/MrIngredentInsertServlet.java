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
 * Servlet implementation class MrIngredentInsertServlet
 */
@WebServlet("/mingreinsert")
public class MrIngredentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MrIngredentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//나만의 레시피 재료리스트 추가 컨트롤러 
		//추가 후 view 컨트롤러
		
		request.setCharacterEncoding("utf-8");
		String listaddform = request.getParameter("listaddform");
		
		MaterialList mlist = new MaterialList();
		
		mlist.setrBoardNo(Integer.parseInt(request.getParameter("r_Board_No")));
		mlist.setMaNo(Integer.parseInt(request.getParameter("ma_No")));
		mlist.setGram(Integer.parseInt(request.getParameter("gram")));
		
		int result = new MatrialListService().mrInsertMatrialList(mlist);
		if(result > 0) {
			int mrno = Integer.parseInt(request.getParameter("mrno"));
			ArrayList<MaterialList> mvlist = new MatrialListService().MringreView(mrno);
			RequestDispatcher view = request.getRequestDispatcher("views/material_list/mrboardMaterialList.jsp");
			request.setAttribute("mvlist", mvlist);
			request.setAttribute("listaddform", listaddform);
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
