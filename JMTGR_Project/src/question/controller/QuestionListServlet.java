package question.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.model.service.QuestionService;
import question.model.vo.Question;
import question.model.vo.Reply;

/**
 * Servlet implementation class QuestionListServlet
 */
@WebServlet("/qlist")
public class QuestionListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이비별롤 출력되는 게시글 전체 조회 처리용 컨트롤러
		int currentPage = 1;
		// 전송 온 페이지값 추출
		if(request.getParameter("page") != null ) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		int limit = 10;
		
		QuestionService qservice = new QuestionService();
		
		// 전체 목록 갯구 조회
		int listCount = qservice.getListCount();
		
		// 현재 페이지에 출력할 게시글 목록 조회
		ArrayList<Question> list = qservice.selectList(currentPage, limit);
		
		// 뷰에 출력될 총 페이지 수 계산 : 게시글이 1개이면 1 페이지 
		int maxPage = (int)((double)listCount / limit + 0.9);
		// 현재 페이지 속한 그룹의 시작 페이지 수 지정
		// 예 : currentPage 가 35이면 페이지그룹이 10일째 시작페이지는 31임
		
		int startPage = (((int)((double)currentPage / limit + 0.9 )) - 1 ) * limit + 1;
		int endPage = startPage + limit - 1;
		
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		int[] nolist = new int[10];
		
		for(int i = 0; i < list.size(); i++) {
			nolist[i] = list.get(i).getQusNo();					
		}
		ArrayList<Reply> replylist = qservice.selectReply(nolist);
		// 뷰 지정해서 내보내기
		RequestDispatcher view = null;
		if(list.size() > 0) {
			view = request.getRequestDispatcher("views/question/questionListView.jsp");
			
			request.setAttribute("list", list);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);
			request.setAttribute("replylist", replylist);
			
			view.forward(request, response);
			
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", currentPage + "페이지에 대한 목록 조회 실패");
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
