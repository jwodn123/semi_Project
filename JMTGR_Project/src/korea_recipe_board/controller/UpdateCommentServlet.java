package korea_recipe_board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import korea_recipe_board.model.service.KRService;
import korea_recipe_board.model.vo.Comment;

/**
 * Servlet implementation class UpdateCommentServlet
 */
@WebServlet("/comupdate.en")
public class UpdateCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Comment co = new Comment();
		co.setCommNo(Integer.parseInt(request.getParameter("cono")));
		co.setKrBoardNo(Integer.parseInt(request.getParameter("krno")));
		co.setCommContent( request.getParameter("comcontent"));
		int result = new KRService().updateCom(co);
		int page = Integer.parseInt(request.getParameter("page"));

		if(result > 0) {
			response.sendRedirect("/jmtgr/krdetail.ss?krno="+co.getKrBoardNo()+"&page=" + page);
		} else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "댓글 수정 실패");
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
