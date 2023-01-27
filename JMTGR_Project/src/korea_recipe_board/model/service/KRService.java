package korea_recipe_board.model.service;

import static common.JDBCTemp.close;
import static common.JDBCTemp.commit;
import static common.JDBCTemp.getConnection;
import static common.JDBCTemp.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import korea_recipe_board.model.dao.KRDao;
import korea_recipe_board.model.vo.Comment;
import korea_recipe_board.model.vo.KRBoard;
import korea_recipe_board.model.vo.KRContent;
import korea_recipe_board.model.vo.KrMaterial;

public class KRService {

	private KRDao kdao = new KRDao();

	public ArrayList<KRBoard> selectAll(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<KRBoard> kblist = kdao.selectAll(conn, currentPage, limit);
		close(conn);
		return kblist;
	}

	public KRBoard selectOne(int kbno) {
		Connection conn = getConnection();
		KRBoard kb = kdao.selectOne(conn, kbno);
		close(conn);
		return kb;
	}

	public int insertKR(KRBoard kb) {
		Connection conn = getConnection();
		int result = kdao.insertKR(conn, kb);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int updateKR(KRBoard kb) {
		Connection conn = getConnection();
		int result = kdao.updateKR(conn, kb);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int deleteKR(int krno) {
		Connection conn = getConnection();
		int result = kdao.deleteKR(conn, krno);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public ArrayList<KRBoard> searchKrT(String krtitle, int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<KRBoard> krblist = kdao.searchKrT(conn, krtitle, currentPage, limit);
		close(conn);
		return krblist;
	}

	public int insertMetrial(String mname, int gram) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertCom(Comment cm) {
		Connection conn = getConnection();
		int result = kdao.insertCom(conn, cm);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public ArrayList<Comment> selectComment(int krno) {
		Connection conn = getConnection();
		ArrayList<Comment> colist = kdao.selectComment(conn, krno);
		close(conn);
		return colist;
	}

	public int deleteComment(int cono) {
		Connection conn = getConnection();
		int result = kdao.deletcComment(conn, cono);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = kdao.getListCount(conn);
		close(conn);
		return listCount;
	}

	public ArrayList<KRBoard> searchKrC(String krtitle, int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<KRBoard> krblist = kdao.searchKrC(conn, krtitle, currentPage, limit);
		close(conn);
		return krblist;
	}

	public ArrayList<KRBoard> Orderby(int currentPage, int limit, int orderby) {
		Connection conn = getConnection();
		ArrayList<KRBoard> kblist = kdao.Orderby(conn, currentPage, limit, orderby);
		close(conn);
		return kblist;
	}


	public int updateCom(Comment co) {
		Connection conn = getConnection();
		int result = kdao.updateComment(conn, co);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public ArrayList<KRContent> selectKrc(int krno) {
		Connection conn = getConnection();
		ArrayList<KRContent> krclist = kdao.selectKrc(conn, krno);
		close(conn);
		return krclist;
	}

	public int insertKrCon(ArrayList<KRContent> krclist) {
		Connection conn = getConnection();
		int result = kdao.insertKrCon(conn, krclist);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int selectKrno(String adminId) {
		Connection conn = getConnection();
		int krno = kdao.selectKrno(conn, adminId);
		close(conn);
		return krno;
	}

	public int updateKRC(ArrayList<KRContent> krclist) {
		Connection conn = getConnection();
		int result = kdao.updateKRC(conn, krclist);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int updateReadCount(int krno) {
		Connection conn = getConnection();
		int result = kdao.updateReadCount(conn, krno);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int insertUpdateKrCon(ArrayList<KRContent> krclist2) {
		Connection conn = getConnection();
		int result = kdao.insertUpdateKrCon(conn, krclist2);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteCom(int krno) {
		Connection conn = getConnection();
		int result = kdao.deleteCom(conn, krno);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteKrCon(int krno) {
		Connection conn = getConnection();
		int result = kdao.deleteKrCon(conn, krno);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteKrConOne(int krBoardNo, int i) {
		Connection conn = getConnection();
		int result = kdao.deleteKrConOne(conn, krBoardNo, i);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteMaterial(int krno) {
		Connection conn = getConnection();
		int result = kdao.deleteMaterial(conn, krno);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deletePackages(int krno) {
		Connection conn = getConnection();
		int result = kdao.deletePackages(conn, krno);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int countKrT(String keyword) {
		Connection conn = getConnection();
		int result = kdao.countKrT(conn, keyword);
		close(conn);
		return result;
	}

	public int countKrC(String keyword) {
		Connection conn = getConnection();
		int result = kdao.countKrC(conn, keyword);
		close(conn);
		return result;
	}

	public ArrayList<KRBoard> selectAllList() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<KRBoard> selectAllList(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<KRBoard> kblist = kdao.selectAllList(conn, currentPage, limit);
		close(conn);
		return kblist;
	}

}
