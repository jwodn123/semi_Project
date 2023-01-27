package my_recipe_board.model.service;

import static common.JDBCTemp.close;
import static common.JDBCTemp.commit;
import static common.JDBCTemp.getConnection;
import static common.JDBCTemp.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import my_recipe_board.model.dao.MyRecipeDao;
import my_recipe_board.model.vo.Comment;
import my_recipe_board.model.vo.LH;
import my_recipe_board.model.vo.MrContent;
import my_recipe_board.model.vo.MyRecipe;


public class MyRecipeService {

	private MyRecipeDao mdao = new MyRecipeDao();

	// 리스트 보기
	public ArrayList<MyRecipe> selectList(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<MyRecipe> list = mdao.selectList(conn, currentPage, limit);
		close(conn);

		return list;
	}

	// 상세보기
	public MyRecipe selectMyRecipe(int mrno) {
		Connection conn = getConnection();
		MyRecipe myrecipe = mdao.selectMyRecipe(conn, mrno);
		close(conn);
		return myrecipe;
	}

	// 글 쓰기
	public int insertMyRecipe(MyRecipe myrecipe) {
		Connection conn = getConnection();
		int result = mdao.insertMyRecipe(conn, myrecipe);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	// 수정이래..
	public int updateMR(MyRecipe myRecipe) {
		Connection conn = getConnection();
		int result = mdao.updateMR(conn, myRecipe);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	// 글 삭제
	public int delectMyRecipe(int mrNo) {
		Connection conn = getConnection();
		int result = mdao.delectMyRecipe(conn, mrNo);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
			close(conn);
		}
		return result;
	}

	//  검색 (Title)
	public ArrayList<MyRecipe> searchMRT(String mrtitle) {
		Connection conn = getConnection();
		ArrayList<MyRecipe> mrlist = mdao.searchMRT(conn, mrtitle);
		close(conn);
		return mrlist;
	}

	// 댓글 삽입
	public int insertCom(Comment cm) {
		Connection conn = getConnection();
		int result = mdao.insertCom(conn, cm);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	// 댓글 상세보기
	public ArrayList<Comment> selectComment(int mrno) {
		Connection conn = getConnection();
		ArrayList<Comment> colist = mdao.selectComment(conn, mrno);
		close(conn);
		return colist;
	}

	// 댓글 삭제
	public int deleteComment(int cono) {
		Connection conn = getConnection();
		int result = mdao.deleteComment(conn, cono);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	// 조회수 증가
	public int getListCount() {
		Connection conn = getConnection();
		int result = mdao.getListCount(conn);
		close(conn);
		return result;

	}

	// 정렬
	public ArrayList<MyRecipe> Orderby(int currentPage, int limit, int orderby) {

		Connection conn = getConnection();
		ArrayList<MyRecipe> mrlist = mdao.Orderby(conn, currentPage, limit, orderby);
		close(conn);
		return mrlist;
	}



	// 댓글 수정
	public int selectCommentOne(Comment co) {
		Connection conn = getConnection();
		int result = mdao.selectCommentOne(conn, co);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;

	}

	// 좋아요 수 증가
	public int insertLh(LH lh, String key) {
		Connection conn = getConnection();
		int result = mdao.insertLh(conn, lh, key);
		if (result > 0 ) {
			commit(conn);
		} else 
			rollback(conn); 
		close(conn);
		
		return result;
	}

	public int[] selectLh(int mrno) {
		Connection conn = getConnection();
		int[] result = mdao.selectLh(conn, mrno);
		close(conn);
		return result;
	}

	public int deleteAllComment(int mrno) {
		Connection conn = getConnection();
		int result = mdao.deleteAllComment(conn, mrno);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	// 게시글 상세보기 (대표사진)
	public ArrayList<MrContent> selectMrc(int mrno) {
		Connection conn = getConnection();
		ArrayList<MrContent> mrclist = mdao.selectMrc(conn, mrno);
		close(conn);
		return mrclist;
	}

	public int selectMrno(String userId) {
		Connection conn = getConnection();
		int mrno = mdao.selectMrno(conn, userId);
		close(conn);
		return mrno;
	}

	public int insertMrCon(ArrayList<MrContent> mrclist) {
		Connection conn = getConnection();
		int result = mdao.insertMrCon(conn, mrclist);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int updateMrc(ArrayList<MrContent> mrclist) {
		Connection conn = getConnection();
		int result = mdao.updateMrc(conn, mrclist);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteLH(int mrno) {
	Connection conn = getConnection();
	int result = mdao.deleteLH(conn, mrno);
	if (result > 0) {
		commit(conn);
	} else {
		rollback(conn);
	}
	close(conn);
	return result;
}

	//게시글 수정
	public int insertUpdateMrCon(ArrayList<MrContent> mrclist2) {
		Connection conn = getConnection();
		int result = mdao.insertUpdateMrCon(conn, mrclist2);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deletemrConOne(int mrBoardNo, int i) {
		Connection conn = getConnection();
		int result = mdao.deletemrConOne(conn, mrBoardNo, i);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public ArrayList<MyRecipe> selectAllUser(int currentPage, int limit, String userid) {
		Connection conn = getConnection();
		ArrayList<MyRecipe> list = mdao.selectAllUser(conn, currentPage, limit, userid);
		close(conn);

		return list;
	}

	public int deleteMrMaterial(int mrno) {
		Connection conn = getConnection();
		int result = mdao.deleteMrMaterial(conn, mrno);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int updateReadCount(int mrno) {
		Connection conn = getConnection();
		int result = mdao.updateReadCount(conn, mrno);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}



}























