package declaration.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import declaration.model.vo.Declaration;
import declaration.model.vo.Reply;
import question.model.vo.Question;

public class DeclarationDao {

	public ArrayList<Declaration> selectList(Connection conn, int currentPage, int limit) {
		ArrayList<Declaration> list = new ArrayList<Declaration>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from ( select rownum rnum, de_no, de_title, de_content, de_pwd, user_id, de_board_date, de_original_filename, de_rename_filename "
				  + " from ( select * from declaration )) "
				  + " where rnum >= ? and rnum <= ?";

		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Declaration declaration = new Declaration();

				declaration.setDeNo(rset.getInt("de_no"));
				declaration.setDeTitle(rset.getString("de_title"));
				declaration.setDeContent(rset.getString("de_content"));
				declaration.setDePwd(rset.getString("de_pwd"));
				declaration.setUserId(rset.getString("user_id"));
				declaration.setDeBoardDate(rset.getDate("de_board_date"));
				declaration.setDeOriginalFileName(rset.getString("de_original_filename"));
				declaration.setDeRenameFileName(rset.getString("de_rename_filename"));

				list.add(declaration);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public Declaration selectDeclaration(Connection conn, int deNo) {
		Declaration declaration = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from declaration where de_no = ? ";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, deNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				declaration = new Declaration();

				declaration.setDeNo(rset.getInt("de_no"));
				declaration.setDeTitle(rset.getString("de_title"));
				declaration.setDeContent(rset.getString("de_content"));
				declaration.setDePwd(rset.getString("de_pwd"));
				declaration.setUserId(rset.getString("user_id"));
				declaration.setDeBoardDate(rset.getDate("de_board_date"));
				declaration.setDeOriginalFileName(rset.getString("de_original_filename"));
				declaration.setDeRenameFileName(rset.getString("de_rename_filename"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return declaration;
	}

	public int insertOriginDeclaration(Connection conn, Declaration declaration) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "insert into declaration values ((select max(de_no) + 1 from declaration), ?, ?, ?, 'user01', sysdate, ?, ?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, declaration.getDeTitle());
			pstmt.setString(2, declaration.getDeContent());
			pstmt.setString(3, declaration.getDePwd());
			pstmt.setString(4, declaration.getDeOriginalFileName());
			pstmt.setString(5, declaration.getDeRenameFileName());
  
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteDeclaration(Connection conn, int deNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from declaration where user_id = 'user01'";
		

		try {
			pstmt = conn.prepareStatement(query);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateReply(Connection conn, Reply reply) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update declaration_answer set de_ans_content= ? where de_no = ?  " ;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reply.getDeReplyContent());
			pstmt.setInt(2, reply.getDeNo());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateOrigin(Connection conn, Declaration declaration) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update declaration set de_title = ?, de_content = ?, de_original_filename = ?, de_rename_filename = ? where de_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, declaration.getDeTitle());
			pstmt.setString(2, declaration.getDeContent());
			pstmt.setString(3, declaration.getDeOriginalFileName());
			pstmt.setString(4, declaration.getDeRenameFileName());
			pstmt.setInt(5, declaration.getDeNo());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select count(*) from declaration";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
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
	
	public int updateDeclaration(Connection conn, Declaration declaration) {
		  int result = 0;
		   PreparedStatement pstmt = null;
		      
		   String query = "update declaration set de_title = ? , de_content = ? , de_board_date = sysdate where de_no = ?";
		      
		      try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, declaration.getDeTitle());
				pstmt.setString(2, declaration.getDeContent());
				pstmt.setInt(3, declaration.getDeNo());
				
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
		      return result;
		   }

	public int insertReply(Connection conn,Reply reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into declaration_answer values (?, ?, sysdate)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reply.getDeNo());
			pstmt.setString(2, reply.getDeReplyContent());

		
			result = pstmt.executeUpdate();
		} catch (Exception e) {
	        e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Declaration> selectSearchUserid(Connection conn, String keyword) {
		ArrayList<Declaration> list = new ArrayList<Declaration>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from declaration where user_id like ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Declaration declaration = new Declaration();
				
				declaration.setDeNo(rset.getInt("de_no"));
				declaration.setDeTitle(rset.getString("de_title"));
				declaration.setDeContent(rset.getString("de_content"));
				declaration.setDeBoardDate(rset.getDate("de_board_date"));
				declaration.setDePwd(rset.getString("de_pwd"));
				declaration.setUserId(rset.getString("user_Id"));
				declaration.setDeOriginalFileName(rset.getString("de_original_filename"));
				declaration.setDeRenameFileName(rset.getString("de_rename_filename"));
				
				list.add(declaration);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
}
