package declaration.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import declaration.model.service.DeclarationService;
import declaration.model.vo.Declaration;


/**
 * Servlet implementation class DeclarationUpdateViewServlet
 */
@WebServlet("/dupview")
public class DeclarationUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeclarationUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int deNo = Integer.parseInt(request.getParameter("dnum"));
		int currentPage = Integer.parseInt(request.getParameter("page"));
		
		Declaration declaration = new DeclarationService().selectDeclaration(deNo);
		
		RequestDispatcher view = null;
		if(declaration != null) {
			view = request.getRequestDispatcher("views/declaration/declarationUpdateView.jsp");
			request.setAttribute("declaration", declaration);
			request.setAttribute("page", currentPage);
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", deNo + "번 글에 대한 수정페이지 요청 실패");
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
