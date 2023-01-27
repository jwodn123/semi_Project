package korea_recipe_board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import korea_recipe_board.model.service.KRService;
import korea_recipe_board.model.vo.KRBoard;
import korea_recipe_board.model.vo.KRContent;

/**
 * Servlet implementation class UpdateKRecipeServlet
 */
@WebServlet("/krupview")
public class UpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int krno = Integer.parseInt(request.getParameter("krno"));
		KRService ks = new KRService();
		KRBoard krb = ks.selectOne(krno);
		ArrayList<KRContent> krclist = ks.selectKrc(krno);
		RequestDispatcher view = null;
		if(krb != null) {
			view = request.getRequestDispatcher("views/krboard/KrUpdateView.jsp");
			request.setAttribute("krb", krb);
			request.setAttribute("krclist", krclist);
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "수정페이지 이동 실패");
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
