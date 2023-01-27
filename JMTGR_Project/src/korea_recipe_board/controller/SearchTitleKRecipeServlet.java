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
 * Servlet implementation class SearchKRecipeServlet
 */
@WebServlet("/krsearchT.en")
public class SearchTitleKRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public SearchTitleKRecipeServlet() {
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
		int limit = 6;
		KRService kservice = new KRService();

		int listCount = 0;
		
		String keyword = request.getParameter("keyword");
		String search = request.getParameter("searchKeyword");
		ArrayList<KRBoard> krblist = null;
		RequestDispatcher view = null;
		if(search.equals("title")){			
			listCount = kservice.countKrT(keyword);
			krblist = kservice.searchKrT(keyword, currentPage, limit);
		}else {
			listCount = kservice.countKrC(keyword);
			krblist = kservice.searchKrC(keyword, currentPage, limit);
		}
		
		int maxPage = (int) (((double) listCount / limit) + 0.9);
		// 현재페이지가 속한 그룹의 시작 페이지 수 지정
		// 예 : currentPage 가 35 이면 페이지 그룹이 10일때 시작페이지는 31이 된다.
		int startPage = (((int) ((double) currentPage / 10 + 0.9)) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;
		if (maxPage < endPage) {
			endPage = maxPage;
		}
		
		if (krblist != null) {
			view = request.getRequestDispatcher("views/krboard/KrSearchView.jsp");
			request.setAttribute("searchKeyword", search);
			request.setAttribute("keyword", keyword);
			request.setAttribute("kblist", krblist);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("views/common/serror.jsp");
			request.setAttribute("masseage", "조회결과 없음");
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
