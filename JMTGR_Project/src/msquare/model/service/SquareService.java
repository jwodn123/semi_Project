package msquare.model.service;

import static common.JDBCTemp.close;
import static common.JDBCTemp.commit;
import static common.JDBCTemp.getConnection;
import static common.JDBCTemp.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import msquare.model.dao.SquareDao;
import msquare.model.vo.Square;
import msquare.model.vo.SquareComment;

public class SquareService {
    private SquareDao sdao = new SquareDao();
    
    public SquareService() {}
    
    public ArrayList<Square> selectList(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Square> list = sdao.selectList(conn, currentPage, limit);
		close(conn);
		return list;
	}
	
	public Square selectSquare(int ms_board_no) {
		Connection conn = getConnection();
		Square square = sdao.selectSquare(conn, ms_board_no);
		close(conn);
		return square;
	}
	
	 public int insertSquare(Square square) {
		   Connection conn = getConnection();
		   int result = sdao.insertSquare(conn, square);
		   if(result > 0)
			   commit(conn);
		   else
			   rollback(conn);	
		   close(conn);
		   return result;
	   }
	 
	 public int updateSquare(Square square) {
		   Connection conn = getConnection();
		   int result = sdao.updateSquare(conn, square);
		   if(result > 0)
			   commit(conn);
		   else
			   rollback(conn);		   
		   close(conn);
		   return result;
	   }
	 
	 public int deleteSquare(int ms_board_no) {
		   Connection conn = getConnection();
		   int result = sdao.deleteSquare(conn, ms_board_no);
		   if(result > 0)
			   commit(conn);
		   else
			   rollback(conn);		   
		   close(conn);
		   return result;
	   }
	 
	 public ArrayList<Square> selectSearch(String ms_board_no, String ms_board_content, int currentPage, int limit){
			Connection conn = getConnection();
			ArrayList<Square> list = sdao.selectSearch(conn, ms_board_no, ms_board_content, currentPage, limit);
			close(conn);
			return list;
	 }

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = sdao.getListCount(conn);
		close(conn);
		return listCount;
	}

	public void addReadCount(int ms_board_no) {
		Connection conn = getConnection();
		int result = sdao.addReadCount(conn, ms_board_no);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);		
		
	}

	public ArrayList<SquareComment> selectComm(int squareno) {
		Connection conn = getConnection();
		ArrayList<SquareComment> comlist = sdao.selectComm(conn, squareno);
		close(conn);
		return comlist;
	}

	public int insertComm(SquareComment sco) {
		Connection conn = getConnection();
		int result = sdao.insertCom(conn, sco);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteComm(int comno) {
		Connection conn = getConnection();
		int result = sdao.deleteComm(conn, comno);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int updateComm(SquareComment reply) {
		Connection conn = getConnection();
		int result = sdao.updateComm(conn, reply);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public ArrayList<Square> searchTitle(String keyword, int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Square> list = sdao.searchTitle(conn, keyword, currentPage, limit);
		close(conn);
		return list;

	}

	public ArrayList<Square> searchId(String keyword, int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Square> list = sdao.searchId(conn, keyword, currentPage, limit);
		close(conn);
		return list;

	}

	public int getSearchCountT(String keyword) {
		Connection conn = getConnection();
		int listCount = sdao.getSearchCountT(conn, keyword);
		close(conn);
		return listCount;
	}

	public int getSearchCountI(String keyword) {
		Connection conn = getConnection();
		int listCount = sdao.getSearchCountI(conn, keyword);
		close(conn);
		return listCount;
	}

	
	
	 
	 
}

