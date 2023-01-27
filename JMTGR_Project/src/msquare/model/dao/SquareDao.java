package msquare.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import msquare.model.vo.Square;
import msquare.model.vo.SquareComment;

public class SquareDao {
	public SquareDao() {}
	
	public ArrayList<Square> selectList(Connection conn, int currentPage, int limit) {
		ArrayList<Square> list = new ArrayList<Square>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query ="SELECT * " + 
				"FROM (SELECT ROWNUM RNUM, MS_BOARD_NO, MS_BOARD_TITLE, MS_BOARD_CONTENT, MS_BOARD_DATE, USER_ID, ADDRESS, READ_COUNT " + 
				"      FROM (SELECT * FROM MSQUARE ORDER BY MS_BOARD_no DESC)) " + 
				"WHERE RNUM >= ? AND RNUM <= ?";
		
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Square square = new Square();
				
				square.setMs_board_no(rset.getInt("ms_board_no"));
				square.setMs_board_title(rset.getString("ms_board_title"));
				square.setMs_board_content(rset.getString("ms_board_content"));
				square.setMs_board_date(rset.getDate("ms_board_date"));
				square.setUser_id(rset.getString("user_id"));
				square.setAddress(rset.getString("address"));
				square.setRead_count(rset.getInt("read_count"));	
				
				list.add(square);				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
				
	}

	public Square selectSquare(Connection conn, int ms_board_no) {
		Square square = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from msquare where ms_board_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ms_board_no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				square = new Square();
				
				square.setMs_board_no(ms_board_no);
				square.setMs_board_title(rset.getString("ms_board_title"));
				square.setMs_board_content(rset.getString("ms_board_content"));
				square.setMs_board_date(rset.getDate("ms_board_date"));
				square.setUser_id(rset.getString("user_id"));
				square.setAddress(rset.getString("address"));
				square.setRead_count(rset.getInt("read_count"));			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}	
		return square;		
				
	}

	public int insertSquare(Connection conn, Square square) {
		int result = 0;
		PreparedStatement pstmt = null;
		   
		String query = "insert into msquare values ((select max(ms_board_no) + 1 from msquare), ?, ?, sysdate, ?, ?, default)";
				   
		try {
		    pstmt = conn.prepareStatement(query);
			pstmt.setString(1, square.getMs_board_title());
			pstmt.setString(2, square.getMs_board_content());
			pstmt.setString(3, square.getUser_id());
			pstmt.setString(4, square.getAddress());
			System.out.println(square.getUser_id());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	    return result;
	    
	}

	public int updateSquare(Connection conn, Square square) {
			int result = 0;
			PreparedStatement pstmt = null;

			String query = "update msquare set ms_board_title = ?, ms_board_content = ?, ms_board_date = sysdate,"
					+ " read_count = default, address = ? where ms_board_no = ?";

			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, square.getMs_board_title());
				pstmt.setString(2, square.getMs_board_content());
				pstmt.setString(3, square.getAddress());
				pstmt.setInt(4, square.getMs_board_no());
				
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}

	public int deleteSquare(Connection conn, int ms_board_no) {
		   int result = 0;
		   PreparedStatement pstmt = null;
		   
		   String query = "delete from msquare where ms_board_no = ?";
		   
		   try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ms_board_no);
			
			result = pstmt.executeUpdate();		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	    return result;
	}

	public ArrayList<Square> selectSearch(Connection conn, String ms_board_title, String ms_board_content, int currentPage,
			int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from msquare";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	public int addReadCount(Connection conn, int ms_board_no) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update msquare set read_count = read_count + 1 where ms_board_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ms_board_no);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<SquareComment> selectComm(Connection conn, int squareno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<SquareComment> comlist = new ArrayList<SquareComment>();
		
		String query = "select * from ms_comment where ms_board_no = ? order by comm_no";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, squareno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				SquareComment sco = new SquareComment();
				sco.setComm_no(rset.getInt("comm_no"));
				sco.setMs_board_no(squareno);
				sco.setComm_content(rset.getString("comm_content"));
				sco.setComm_date(rset.getDate("comm_date"));
				sco.setUser_id(rset.getString("user_id"));
	
				comlist.add(sco);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return comlist;
	}

	public int insertCom(Connection conn, SquareComment sco) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into ms_comment values((select max(comm_no) from ms_comment) + 1, ?, ?, sysdate, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, sco.getMs_board_no());
			pstmt.setString(2, sco.getComm_content());	
			pstmt.setString(3, sco.getUser_id());
			
			result = pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		return result;
	}

	public int deleteComm(Connection conn, int comno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from ms_comment where comm_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, comno);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateComm(Connection conn, SquareComment reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update ms_comment set comm_content = ? where ms_board_no = ? and comm_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reply.getComm_content());
			pstmt.setInt(2, reply.getMs_board_no());
			pstmt.setInt(3, reply.getComm_no());

			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}			
		return result;
	}

	public ArrayList<Square> searchT(Connection conn, String btitle) {
		ArrayList<Square> slist = new ArrayList<Square>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from msquare where ms_board_title like ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + btitle + "%");

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Square square = new Square();
				square.setMs_board_no(rset.getInt("ms_board_no"));
				square.setMs_board_title(rset.getString("ms_board_title"));
				square.setMs_board_content(rset.getString("ms_board_content"));
				square.setMs_board_date(rset.getDate("ms_board_date"));
				square.setUser_id(rset.getString("user_id"));
				square.setAddress(rset.getString("address"));
				square.setRead_count(rset.getInt("read_count"));	

				slist.add(square);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return slist;
	}

	public ArrayList<Square> searchN(Connection conn, String btitle) {
		ArrayList<Square> slist = new ArrayList<Square>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from msquare where user_id like ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + btitle + "%");

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Square square = new Square();
				square.setMs_board_no(rset.getInt("ms_board_no"));
				square.setMs_board_title(rset.getString("ms_board_title"));
				square.setMs_board_content(rset.getString("ms_board_content"));
				square.setMs_board_date(rset.getDate("ms_board_date"));
				square.setUser_id(rset.getString("user_id"));
				square.setAddress(rset.getString("address"));
				square.setRead_count(rset.getInt("read_count"));
				
				slist.add(square);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return slist;
	}

	public ArrayList<Square> searchTitle(Connection conn, String keyword, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Square> slist = new ArrayList<Square>();
		
		String query ="SELECT * " + 
				"FROM (SELECT ROWNUM RNUM, MS_BOARD_NO, MS_BOARD_TITLE, MS_BOARD_CONTENT, MS_BOARD_DATE, USER_ID, ADDRESS, READ_COUNT " + 
				"      FROM (SELECT * FROM MSQUARE where ms_board_title like ? ORDER BY MS_BOARD_DATE DESC)) " + 
				"WHERE RNUM >= ? AND RNUM <= ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, currentPage);
			pstmt.setInt(3, limit);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Square square = new Square();
				square.setMs_board_no(rset.getInt("ms_board_no"));
				square.setMs_board_title(rset.getString("ms_board_title"));
				square.setMs_board_content(rset.getString("ms_board_content"));
				square.setMs_board_date(rset.getDate("ms_board_date"));
				square.setUser_id(rset.getString("user_id"));
				square.setAddress(rset.getString("address"));
				square.setRead_count(rset.getInt("read_count"));
				
				slist.add(square);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return slist;
	}

	public ArrayList<Square> searchId(Connection conn, String keyword, int currentPage, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Square> slist = new ArrayList<Square>();
		
		String query ="SELECT * " + 
				"FROM (SELECT ROWNUM RNUM, MS_BOARD_NO, MS_BOARD_TITLE, MS_BOARD_CONTENT, MS_BOARD_DATE, USER_ID, ADDRESS, READ_COUNT " + 
				"      FROM (SELECT * FROM MSQUARE where user_id like ? ORDER BY MS_BOARD_DATE DESC)) " + 
				"WHERE RNUM >= ? AND RNUM <= ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, currentPage);
			pstmt.setInt(3, limit);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Square square = new Square();
				square.setMs_board_no(rset.getInt("ms_board_no"));
				square.setMs_board_title(rset.getString("ms_board_title"));
				square.setMs_board_content(rset.getString("ms_board_content"));
				square.setMs_board_date(rset.getDate("ms_board_date"));
				square.setUser_id(rset.getString("user_id"));
				square.setAddress(rset.getString("address"));
				square.setRead_count(rset.getInt("read_count"));
				
				slist.add(square);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return slist;
	}

	public int getSearchCountT(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = "select count(ms_board_no) from msquare where ms_board_title like ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,"%"+keyword+"%");
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int getSearchCountI(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = "select count(ms_board_no) from msquare where user_id like ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,"%"+keyword+"%");
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	

}
