package member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;

import member.model.service.MemberService;

/**
 * Servlet implementation class LoginAccessServlet
 */
@WebServlet("/loginOk")
public class LoginAccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAccessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 접속제한/해제 처리 컨트롤러
		String userid = request.getParameter("userid");
		String ok = request.getParameter("ok");
		String loginok = null;
		if(ok.equals("true")) {
			loginok = "Y";
		}else {
			loginok = "N";
		}
		
		MemberService mservice = new MemberService();
		int result = new MemberService().updateLoginOk(userid, loginok);
		ArrayList<Member> list = null;
		if(result > 0) {
			list = mservice.selectList();
		}
		
		RequestDispatcher view = null;
		if(result > 0 && list.size() > 0) {
			view = request.getRequestDispatcher("views/member/memberListView.jsp");
			request.setAttribute("list", list);
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", userid + "회원의 로그인 제한 처리 실패");
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
