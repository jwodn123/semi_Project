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

/**
 * Servlet implementation class KRecipeOrderbyServlet
 */
@WebServlet("/orderbylist")
public class KRecipeOrderbyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KRecipeOrderbyListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int currentPage = 1;
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		int limit = 15;
		KRService kservice = new KRService();

		int listCount = kservice.getListCount();
		int maxPage = (int) (((double) listCount / limit) + 0.9);
		// 현재페이지가 속한 그룹의 시작 페이지 수 지정
		// 예 : currentPage 가 35 이면 페이지 그룹이 10일때 시작페이지는 31이 된다.
		int startPage = (((int) ((double) currentPage / 10 + 0.9)) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;
		if (maxPage < endPage) {
			endPage = maxPage;
		}
		ArrayList<KRBoard> kblist = new ArrayList<KRBoard>();
		RequestDispatcher view = null;
		System.out.println(request.getParameter("orderby"));
		int orderby = 0;
		if (request.getParameter("orderby").equals("title")) {
			orderby = 1;
			kblist = kservice.Orderby(currentPage, limit, orderby);

		} else if(request.getParameter("orderby").equals("tdesc")){
			orderby = 2;			
			kblist = kservice.Orderby(currentPage, limit, orderby);
		} else if(request.getParameter("orderby").equals("readcount")) {
			orderby = 3;
			kblist = kservice.Orderby(currentPage, limit, orderby);
		}

		if (kblist.size() > 0) {
			view = request.getRequestDispatcher("views/krboard/KrListBoardView.jsp");
			request.setAttribute("kblist", kblist);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "한식 글 정렬 조회 실패");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
