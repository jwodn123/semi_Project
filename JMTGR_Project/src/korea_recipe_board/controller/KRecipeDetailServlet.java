package korea_recipe_board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import korea_recipe_board.model.service.KRService;
import korea_recipe_board.model.vo.Comment;
import korea_recipe_board.model.vo.KRBoard;
import korea_recipe_board.model.vo.KRContent;
import recipe_matelial_list.model.service.MatrialListService;
import recipe_matelial_list.model.vo.MaterialList;

/**
 * Servlet implementation class KRDetailServlet
 */
@WebServlet("/krdetail.ss")
public class KRecipeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public KRecipeDetailServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int krno = Integer.parseInt(request.getParameter("krno"));
		KRService ks = new KRService();

		KRBoard kb = ks.selectOne(krno);
		ArrayList<KRContent> krclist = ks.selectKrc(krno);
		ArrayList<Comment> colist = ks.selectComment(krno);
		ArrayList<MaterialList> mvlist = new MatrialListService().ingreView(krno);
		int result = ks.updateReadCount(krno);
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));

		}
		String keyword = request.getParameter("keyword");
		String searchKeyword = request.getParameter("searchKeyword");
		RequestDispatcher view = null;
		if (kb != null && mvlist != null) {
			if (request.getParameter("cono") != null) {
				int cono = Integer.parseInt(request.getParameter("cono"));
				view = request.getRequestDispatcher("views/krboard/KrDetailView.jsp");
				request.setAttribute("kb", kb);
				request.setAttribute("krclist", krclist);
				request.setAttribute("comment", colist);
				request.setAttribute("cono", cono);
				request.setAttribute("mvlist", mvlist);
				request.setAttribute("page", page);
				request.setAttribute("keyword", keyword);
				request.setAttribute("searchKeyword", searchKeyword);
				view.forward(request, response);
			} else {
				view = request.getRequestDispatcher("views/krboard/KrDetailView.jsp");
				request.setAttribute("kb", kb);
				request.setAttribute("krclist", krclist);
				request.setAttribute("comment", colist);
				request.setAttribute("mvlist", mvlist);
				request.setAttribute("page", page);
				request.setAttribute("keyword", keyword);
				request.setAttribute("searchKeyword", searchKeyword);
				view.forward(request, response);
			}
		} else {
			view = request.getRequestDispatcher("views/common/errer.jsp");
			request.setAttribute("message", "게시글 상세 보기 실패");
			view.forward(request, response);
		}
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
