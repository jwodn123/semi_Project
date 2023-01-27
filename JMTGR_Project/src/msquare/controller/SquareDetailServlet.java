package msquare.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import msquare.model.service.SquareService;
import msquare.model.vo.Square;
import msquare.model.vo.SquareComment;

/**
 * Servlet implementation class SquareDetailServlet
 */
@WebServlet("/sdetail")
public class SquareDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SquareDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 상세보기 처리용 컨트롤러
		request.setCharacterEncoding("utf-8");
		int squareno = Integer.parseInt(request.getParameter("squareno"));
		String keyword = request.getParameter("keyword");
		String searchKeyword = request.getParameter("searchKeyword");
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		SquareService sservice = new SquareService();
		ArrayList<SquareComment> comlist = new SquareService().selectComm(squareno);
		
		//조회수 1 증가 처리용
		sservice.addReadCount(squareno);
		
		//글 번호에 대한 게시글 조회
		Square square = new SquareService().selectSquare(squareno);

		RequestDispatcher view = null;
		if (square != null) {
			if(request.getParameter("comno") != null) {
				int comno = Integer.parseInt(request.getParameter("comno"));
				view = request.getRequestDispatcher("views/square/squareDetailView.jsp");
				request.setAttribute("square", square);
				request.setAttribute("comment", comlist);
				request.setAttribute("comno", comno);
				request.setAttribute("keyword", keyword);
				request.setAttribute("searchKeyword", searchKeyword);
				request.setAttribute("page", currentPage);
				view.forward(request, response);
			}else {
				view = request.getRequestDispatcher("views/square/squareDetailView.jsp");
				request.setAttribute("square", square);
				request.setAttribute("comment", comlist);
				request.setAttribute("keyword", keyword);
				request.setAttribute("searchKeyword", searchKeyword);
				request.setAttribute("page", currentPage);
				view.forward(request, response);
			}
				
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp"); 
			request.setAttribute("message", squareno + "번 글 상세보기 조회 실패!");
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
