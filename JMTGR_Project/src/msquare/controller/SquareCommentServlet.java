package msquare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import msquare.model.service.SquareService;
import msquare.model.vo.SquareComment;

/**
 * Servlet implementation class SquareCommentServlet
 */
@WebServlet("/scom")
public class SquareCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SquareCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 댓글 등록 처리용 컨트롤러
		
		request.setCharacterEncoding("utf-8");
		
		int squareno = Integer.parseInt(request.getParameter("snum"));	
		String user_id = request.getParameter("writer");
		
		SquareComment sco = new SquareComment();
		sco.setComm_content(request.getParameter("content"));
		sco.setMs_board_no(squareno);
		sco.setUser_id(user_id);
		
		int result = new SquareService().insertComm(sco);
		
		RequestDispatcher view = null;
	    if(result > 0) {
	    	view = request.getRequestDispatcher(" ");
	    	request.setAttribute("comment", sco);
	    	view.forward(request, response);
	    }else {
	    	view = request.getRequestDispatcher("views/common/error.jsp");
	    	request.setAttribute("message", "댓글 등록 실패!");
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
