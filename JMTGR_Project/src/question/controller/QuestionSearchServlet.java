package question.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.model.service.QuestionService;
import question.model.vo.Question;


/**
 * Servlet implementation class QuestionSearchServlet
 */
@WebServlet("/qsearch")
public class QuestionSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				request.setCharacterEncoding("utf-8");
	
				response.setContentType("text/html; charset=UTF-8");
				

				String action = request.getParameter("action");
				String keyword = null;
				
				 
				keyword = request.getParameter("keyword");
				
			
				QuestionService qservice = new QuestionService();
				ArrayList<Question> list = null;
				
				switch(action) {
				case "id":           list = qservice.selectSearchUserid(keyword);
				                     break ;
				}
				
				RequestDispatcher view = null;
				if(list.size() > 0) {
					view = request.getRequestDispatcher("views/question/questionListView.jsp");
					request.setAttribute("list", list);
					view.forward(request, response);
				} else {
					view = request.getRequestDispatcher("views/common/error.jsp");
					request.setAttribute("message", action + "검색에 대한" + keyword + "결과가 존재하지 않습니다");
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
