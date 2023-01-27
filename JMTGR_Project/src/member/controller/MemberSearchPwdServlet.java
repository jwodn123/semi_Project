package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;

import member.model.service.MemberService;

/**
 * Servlet implementation class MemberSearchPwdServlet
 */
@WebServlet("/searchpwd.en")
public class MemberSearchPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberSearchPwdServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 회원 비밀번호 찾기 컨트롤러
		RequestDispatcher view = null;
		String userid = request.getParameter("userid");
		String username = request.getParameter("username");
		String userno = request.getParameter("userno");
		MemberService mservice = new MemberService();
		int result = mservice.MemberSearchPwd(userid, username, userno);
		String getTempPassword = null;
		if(result == 1) {
			int index = 0;
			int length = 3;
			char[] charArr = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
					'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
					'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' };

			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < length; i++) {
				index = (int) (charArr.length * Math.random());
				sb.append(charArr[index]);
			}

			getTempPassword = sb.toString();
			view = request.getRequestDispatcher("views/member/memberSerachPwdView.jsp");
			request.setAttribute("userid", userid);
			request.setAttribute("password", getTempPassword);
			view.forward(request, response);
			
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "회원 조회 실패");
			view.forward(request, response);
		
			
		}
		
		// 임시비밀번호 발급
		
		/*
		 * int result1 = mservice.updatePwd(userid, getTempPassword); if(result1 > 0) {
		 * response.sendRedirect("    ");
		 * 
		 * } else { view = request.getRequestDispatcher("views/common/error.jsp");
		 * request.setAttribute("message", "비밀번호 발급 실패"); view.forward(request,
		 * response); }
		 */
			
		

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
