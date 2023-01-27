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
 * Servlet implementation class SquareCommentInsertServlet
 */
@WebServlet("/cominsert")
public class SquareCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SquareCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 댓글 등록 처리용 컨트롤러
		request.setCharacterEncoding("utf-8");
		int page = Integer.parseInt(request.getParameter("page"));
		SquareComment sco = new SquareComment();
		sco.setComm_content(request.getParameter("comcontent"));
		sco.setMs_board_no(Integer.parseInt(request.getParameter("squareno")));
		sco.setUser_id(request.getParameter("userid"));
		
		int result = new SquareService().insertComm(sco);
		
		RequestDispatcher view = null;
		if(result > 0) {
			response.sendRedirect("/jmtgr/sdetail?squareno=" + sco.getMs_board_no()+"&page="+page);		
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
