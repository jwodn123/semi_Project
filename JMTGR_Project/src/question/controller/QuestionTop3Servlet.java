package question.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import question.model.service.QuestionService;
import question.model.vo.Question;



/**
 * Servlet implementation class QuestionTopServlet
 */
@WebServlet("/qtop3")
public class QuestionTop3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionTop3Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // ajax 요청으로 조회수 많은 게시글 3개 조회 반환하는 컨트롤러
	      ArrayList<Question> list = new QuestionService().selectTop3();
	      
	      JSONObject sendJSON = new JSONObject();
	      JSONArray jarr = new JSONArray();
	      
	      for(Question question : list) {
	         JSONObject job = new JSONObject();
	         
	         job.put("qnum", question.getQusNo());
	         job.put("qtitle", URLEncoder.encode(question.getQusTitle(), "utf-8"));
	         job.put("rcount", question.getQusReadCount());
	         
	         jarr.add(job);
	      } //for each 
	      
	      sendJSON.put("list", jarr);
	      
	      response.setContentType("application/json; charset=utf-8");
	      PrintWriter out = response.getWriter();
	      out.write(sendJSON.toJSONString());
	      out.flush();
	      out.close();
	      
	   }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
