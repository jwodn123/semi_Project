package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class CryptoLoginServlet
 */
@WebServlet("/login.cp")
public class CryptoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CryptoLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String userid = request.getParameter("userid");
				String userpwd = request.getParameter("userpwd");

				MemberService mservice = new MemberService();
				Member loginMember = mservice.loginCheck(userid, userpwd);
				if (loginMember != null) {
					HttpSession session = request.getSession();					
					session.setMaxInactiveInterval(10 * 60); 
					session.setAttribute("loginMember", loginMember); 
					response.sendRedirect("/jmtgr/index.jsp");
				} else {
					
					RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
					request.setAttribute("message", "로그인 실패 또는 로그인 제한상태입니다!");
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
