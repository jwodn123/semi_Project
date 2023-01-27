package packages.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packages.model.service.PackagesService;
import packages.model.vo.Packages;

/**
 * Servlet implementation class mrPackageInsertServlet
 */
@WebServlet("/mpinsert")
public class mrPackageInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mrPackageInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//나만의 레시피 구매용 재료 리스트 담는 컨트롤러
		
		Packages packages = new Packages();

		packages.setUserId(request.getParameter("USER_ID"));
		packages.setPeople(Integer.parseInt(request.getParameter("PEOPLE")));
		packages.setKrBoardNo(Integer.parseInt(request.getParameter("MR_BOARD_NO")));
		
		
		
		int result = new PackagesService().MrpackagesInsert(packages);
		
		if(result > 0) {
			
			response.sendRedirect("/jmtgr/mrlist");
			
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
