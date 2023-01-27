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
 * Servlet implementation class packagesInsertServlet
 */
@WebServlet("/pinsert")
public class packagesInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public packagesInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		Packages packages = new Packages();

		packages.setUserId(request.getParameter("USER_ID"));
		packages.setPeople(Integer.parseInt(request.getParameter("PEOPLE")));
		packages.setKrBoardNo(Integer.parseInt(request.getParameter("KR_BOARD_NO")));
		
		
		
		int result = new PackagesService().packagesInsert(packages);
		
		if(result > 0) {
			
			response.sendRedirect("/jmtgr/krlist");
			
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
