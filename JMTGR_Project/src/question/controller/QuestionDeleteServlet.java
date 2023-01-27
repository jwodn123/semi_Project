package question.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.model.service.QuestionService;

/**
 * Servlet implementation class QuestionDeleteServlet
 */
@WebServlet("/qdel")
public class QuestionDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 삭제 처리용 컨트롤러
        int qusNo = Integer.parseInt(request.getParameter("qnum"));
		
		int result = new QuestionService().deleteQuestion(qusNo);
		
		if(result > 0) {
			// 공지글 삭제시
			String renameFileName = request.getParameter("rfile");
			if( renameFileName != null) {
				String savePath = request.getSession().
						  getServletContext().getRealPath("/resources/qupfiles");
				new File(savePath + "\\" + renameFileName).delete();
			}
			response.sendRedirect("qlist");
		} else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", qusNo + "번글 삭제 실패" );
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
