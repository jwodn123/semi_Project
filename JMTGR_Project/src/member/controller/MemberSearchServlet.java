package member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberSearchServlet
 */
@WebServlet("/msearch.en")
public class MemberSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//회원 관리서비스에서 회원정보 검색 처리용 컨트롤러
		
		response.setContentType("text/html; charset=utf-8"); //오타날 경우 파일 선택창이 나옴
		
		//2. 전송온 값 거내서 변수 또는 객체에 기록 저장
		String action = request.getParameter("action");
		String keyword = null, beginDate = null, endDate = null;
		
		if(action.equals("enrolldate")) {
			beginDate = request.getParameter("begin");
			endDate = request.getParameter("end");
		}else {
			keyword = request.getParameter("keyword");					
		}
		
		//3. 서비스 객체 생성하고, 메소드 실행하고 결과 받기
		MemberService mservice = new MemberService();
		ArrayList<Member> list = null;
		
		switch(action) {
		case "id":	list = mservice.selectSearchUserid(keyword);
					break;
		}
		
		//4. 받은 결과에 대한 뷰 내보내기
		RequestDispatcher view = null;
		if(list.size() > 0) {
			view = request.getRequestDispatcher("views/member/memberListView.jsp");
			request.setAttribute("list", list);
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", action + "검색에 대한" + keyword + "결과가 존재하지 앖습니다.");
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
