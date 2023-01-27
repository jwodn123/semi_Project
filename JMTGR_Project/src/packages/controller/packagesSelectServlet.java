package packages.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packages.model.service.PackagesService;
import packages.model.vo.PackagesResult;


/**
 * Servlet implementation class PakageSelectServlet
 */
@WebServlet("/pselect")
public class packagesSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public packagesSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 장바구니에 리스트 추가 컨트롤러
		String userid = request.getParameter("userid");
		
		ArrayList<PackagesResult> list = new PackagesService().selectList(userid);
		ArrayList<PackagesResult> list2 = new PackagesService().selectMrList(userid);
		
		if(list != null) {
			RequestDispatcher view = request.getRequestDispatcher("views/packages/packages.jsp");
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			view.forward(request, response);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "레시피 리스트 불러오는데 실패 했습니다.");
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
