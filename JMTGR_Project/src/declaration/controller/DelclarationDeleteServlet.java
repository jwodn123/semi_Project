package declaration.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import declaration.model.service.DeclarationService;



/**
 * Servlet implementation class DelclarationDeleteServlet
 */
@WebServlet("/ddel")
public class DelclarationDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelclarationDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자가 요청한 공지글 삭제 처리용 컨트롤러
				int deNo = Integer.parseInt(request.getParameter("deNo"));
				
				int result = new DeclarationService().deleteDeclaration(deNo);	
				
				if(result > 0) {
					// 공지글 삭제시
					String renameFileName = request.getParameter("rfile");
					if( renameFileName != null) {
						String savePath = request.getSession().
								  getServletContext().getRealPath("/resources/dupfiles");
						new File(savePath + "\\" + renameFileName).delete();
					}
					response.sendRedirect("dlist");
				} else {
					RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
					request.setAttribute("message", deNo + "번글 삭제 실패" );
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
