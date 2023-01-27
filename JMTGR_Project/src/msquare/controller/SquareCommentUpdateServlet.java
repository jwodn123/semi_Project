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
 * Servlet implementation class SquareCommentUpdateServlet
 */
@WebServlet("/comupdate")
public class SquareCommentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SquareCommentUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 댓글 수정 처리용 컨트롤러
		request.setCharacterEncoding("utf-8");
		
		SquareComment sco = new SquareComment();
		sco.setComm_no(Integer.parseInt(request.getParameter("comno")));
		sco.setComm_content(request.getParameter("comcontent"));
		sco.setMs_board_no(Integer.parseInt(request.getParameter("squareno")));
	    
	    int result = new SquareService().updateComm(sco);
	    RequestDispatcher view = null;
	    if(result > 0) {
	    	response.sendRedirect("/jmtgr/sdetail?squareno=" + sco.getMs_board_no());
	    }else {
	    	view = request.getRequestDispatcher("views/common/error.jsp");
	    	request.setAttribute("message", "댓글 수정 실패!");
	    	view.forward(request, response);
	    }
	    System.out.println(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
