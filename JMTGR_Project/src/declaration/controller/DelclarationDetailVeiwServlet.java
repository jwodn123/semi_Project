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
 * Servlet implementation class DelclarationDetailVeiwServlet
 */
@WebServlet("/ddetail")
public class DelclarationDetailVeiwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelclarationDetailVeiwServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // 게시글 상세보기 처리용 컨트롤러
	      
	      int deNo = Integer.parseInt(request.getParameter("dnum"));
	      int currentPage = Integer.parseInt(request.getParameter("page"));
	      
	      DeclarationService dservice = new DeclarationService();
	      
	      // 조회수 1 증가 처리 : update
	      
	      // 글 번호에 대한 게시글 조회 : select 
	      Declaration declaration = dservice.selectDeclaration(deNo);
	      
	      RequestDispatcher view = null;
	      if(declaration != null) {
	         view = request.getRequestDispatcher("views/declaration/declarationDetailView.jsp");
	         request.setAttribute("declaration", declaration);
	         request.setAttribute("currentPage", currentPage);
	         view.forward(request, response);
	      }else {
	         view = request.getRequestDispatcher("views/common/error.jsp");
	         request.setAttribute("message", deNo + "번 글 상세보기 조회 실패!");
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
