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
 * Servlet implementation class PackageListMrDeleteServlet
 */
@WebServlet("/plistmrdelete")
public class PackageListMrDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PackageListMrDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid = request.getParameter("userid");
		int mrboardno = Integer.parseInt(request.getParameter("mrboardno"));
		
		int result = new PackagesService().Mrdeletelist(userid, mrboardno);	
		if(result >0) {
			ArrayList<PackagesResult> list = new PackagesService().selectList(userid);
			ArrayList<PackagesResult> list2 = new PackagesService().selectMrList(userid);
			RequestDispatcher view = request.getRequestDispatcher("views/packages/packages.jsp");
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			view.forward(request, response);
			
			
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "구매용 재료리스트 삭제 실패했습니다.");
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
