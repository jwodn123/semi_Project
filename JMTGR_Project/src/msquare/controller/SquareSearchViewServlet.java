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

/**
 * Servlet implementation class SquareSearchViewServlet
 */
@WebServlet("/squaresearch")
public class SquareSearchViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SquareSearchViewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int currentPage = 1;
		//전송 온 페이지 값 추출
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		//한 페이지당 출력 목록 갯수 지정
		int limit = 10;
		
		SquareService sservice = new SquareService();
		
		//전체 목록 갯수 조회
		int listCount = 0;
		
		String keyword = request.getParameter("keyword");
		String searchKeyword = request.getParameter("searchKeyword");
		ArrayList<Square> sqlist = new ArrayList<Square>();
		
		if (searchKeyword.equals("title")) {
			sqlist = sservice.searchTitle(keyword, currentPage, limit);
			listCount =  sservice.getSearchCountT(keyword);
		} else {
			sqlist = sservice.searchId(keyword, currentPage, limit);
			listCount =  sservice.getSearchCountI(keyword);
		}
		
		int maxPage = (int)((double)listCount / limit + 0.9);
		//현재 페이지가 속한 그룹의 시작 페이지 수 지정
		//ex) currenPage가 35이면 페이지그룹이 10일때 시작 페이지는 31이 된다.
		int startPage = (((int)((double)currentPage / limit + 0.9)) - 1) * limit + 1;
		int endPage = startPage + limit - 1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}

		RequestDispatcher view = null;
		
		if (sqlist.size() > 0) {
			view = request.getRequestDispatcher("views/square/squareListView.jsp");			
			request.setAttribute("list", sqlist);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);
			request.setAttribute("keyword", keyword);
			request.setAttribute("searchKeyword", searchKeyword);
			
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "조회된 목록이 없습니다.");
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
