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
 * Servlet implementation class SquareListServlet
 */
@WebServlet("/slist")
public class SquareListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SquareListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이비별 목록 전체보기 처리용 컨트롤러
		
		//페이지 기본값 지정
		int currentPage = 1;
		//전송 온 페이지 값 추출
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		//한 페이지당 출력 목록 갯수 지정
		int limit = 10;
		
		SquareService sservice = new SquareService();
		
		//전체 목록 갯수 조회
		int listCount = sservice.getListCount();
		
		//현재 페이지에 출력할 게시글 목록 조회
		ArrayList<Square> list = sservice.selectList(currentPage, limit);
		
		//뷰에서 출력된 총 페이지 수 계산 : 게시글이 1개이면 1페이지임
		int maxPage = (int)((double)listCount / limit + 0.9);
		//현재 페이지가 속한 그룹의 시작 페이지 수 지정
		//ex) currenPage가 35이면 페이지그룹이 10일때 시작 페이지는 31이 된다.
		int startPage = (((int)((double)currentPage / limit + 0.9)) - 1) * limit + 1;
		int endPage = startPage + limit - 1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}

		// RequestDispatcher : /a.jsp 로 들어온 요청을 /a.jsp 내에서 RequestDispatcher를 사용하여
		// b.jsp로 요청을 보낼 수 있습니다.
		// 또는 a.jsp에서 b.jsp로 처리를 요청하고 b.jsp에서 처리한 결과 내용을 a.jsp의 결과에 포함시킬 수 있습니다.
		RequestDispatcher view = null;
		if (list.size() > 0) {
			view = request.getRequestDispatcher("views/square/squareListView.jsp");			
			request.setAttribute("list", list);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);
			
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "조회된 목록이 없습니다.");
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
