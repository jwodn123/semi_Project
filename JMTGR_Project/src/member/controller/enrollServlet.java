package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class enrollServlet
 */
@WebServlet("/minsert.cp")
public class enrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public enrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원아비 처리용 컨트롤러
				//1. 전송온 값에 한글이 있다면 인코딩 처리함
				request.setCharacterEncoding("utf-8");
				
				//2. 전송온 값 꺼내서, 변수 또는 객체에 기록 저장 처리함
				Member member = new Member();
				member.setUserId(request.getParameter("userid"));
				member.setUserPwd(request.getParameter("userpwd"));
				member.setUserName(request.getParameter("username"));								
				member.setUserNo(request.getParameter("userno"));
				member.setPhone(request.getParameter("phone"));
				member.setGender(request.getParameter("gender"));
				member.setAddress1(request.getParameter("address1"));
				member.setAddress2(request.getParameter("address2"));
				member.setAddress3(request.getParameter("address3"));
				member.setEmail(request.getParameter("email"));
				
				//3. 서비스 객체 생성하고, 서비스 메소드를 이용해서 객체 전달하고
				//처리 결과받기
				int result = new MemberService().insertMember(member);
				
				//4. 받은 결과에 따라 성공/실패 뷰 선택해서 내보내기
				if(result > 0) {
					response.sendRedirect("/jmtgr/views/member/loginPage.jsp");
				}else {
					//response.sendRedirect("/test1/views/common/error.jsp");
					
					RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
					request.setAttribute("message", "회원 가입 실패!");
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
