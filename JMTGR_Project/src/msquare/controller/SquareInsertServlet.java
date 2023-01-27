package msquare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import msquare.model.service.SquareService;
import msquare.model.vo.Square;

/**
 * Servlet implementation class SquareInsertServlet
 */
@WebServlet("/sinsert")
public class SquareInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SquareInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 만남의 광장 글 등록 처리용 컨트롤러
		request.setCharacterEncoding("UTF-8");
		
		Square square = new Square();
		square.setMs_board_title(request.getParameter("title"));
		square.setMs_board_content(request.getParameter("content"));
		square.setUser_id(request.getParameter("writer"));
		square.setAddress(request.getParameter("address"));
		
		int result = new SquareService().insertSquare(square);

		if (result > 0) {
			response.sendRedirect("slist?page=1");
		} else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "글 등록 처리 실패!");
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
