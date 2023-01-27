package korea_recipe_board.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import korea_recipe_board.model.vo.Comment;
import korea_recipe_board.model.vo.KRBoard;
import korea_recipe_board.model.vo.KRContent;

public class KRDao {

	public KRDao() {

	}

	public ArrayList<KRBoard> selectAll(Connection conn, int currentPage, int limit) {
		ArrayList<KRBoard> kblist = new ArrayList<KRBoard>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "SELECT * FROM (SELECT ROWNUM RNUM, KR_BOARD_NO, KR_BOARD_TITLE, THUMBNAIL_FILE, RENAME_FILE, KR_BOARD_DATE, COOK_NAME, ADMIN_ID, READ_COUNT "
				+ "FROM (SELECT * FROM KOREA_RECIPE ORDER BY kr_board_date)) WHERE RNUM >= ? AND RNUM <= ?";

		int start = (currentPage - 1) * limit + 1;
		int last = start + limit - 1;
		
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, last);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				KRBoard kb = new KRBoard();
				kb.setAdminId(rset.getString("admin_id"));
				kb.setCookName(rset.getString("cook_name"));
				kb.setKrBoardDate(rset.getDate("kr_board_date"));
				kb.setKrBoardNo(rset.getInt("kr_board_no"));
				kb.setKrBoardTitle(rset.getString("kr_board_title"));
				kb.setReadCount(rset.getInt("read_count"));
				kb.setThumbNailFile(rset.getString("thumbnail_file"));
				kb.setRenameFile(rset.getString("rename_file"));
				kblist.add(kb);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return kblist;
	}

	public KRBoard selectOne(Connection conn, int kbno) {
		KRBoard kb = new KRBoard();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from korea_recipe where kr_board_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, kbno);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				kb.setAdminId(rset.getString("admin_id"));
				kb.setCookName(rset.getString("cook_name"));
				kb.setKrBoardDate(rset.getDate("kr_board_date"));
				kb.setKrBoardNo(kbno);
				kb.setKrBoardTitle(rset.getString("kr_board_title"));
				kb.setReadCount(rset.getInt("read_count"));
				kb.setThumbNailFile(rset.getString("thumbnail_file"));
				kb.setRenameFile(rset.getString("rename_file"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return kb;
	}

	public int insertKR(Connection conn, KRBoard kb) {

		PreparedStatement pstmt = null;
		int result = 0;

		String query = "insert into korea_recipe values((select max(kr_board_no) from korea_recipe) + 1, ?, ?, ?, sysdate, ?, default, default)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, kb.getKrBoardTitle());
			pstmt.setString(2, kb.getThumbNailFile());
			pstmt.setString(3, kb.getRenameFile());
			pstmt.setString(4, kb.getCookName());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt); 
		}
		return result;
	}

	public int updateKR(Connection conn, KRBoard kb) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update korea_recipe set kr_board_title = ?, thumbnail_file = ?, rename_file = ?, cook_name = ? where kr_board_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, kb.getKrBoardTitle());
			pstmt.setString(2, kb.getThumbNailFile());
			pstmt.setString(3, kb.getRenameFile());
			pstmt.setString(4, kb.getCookName());
			pstmt.setInt(5, kb.getKrBoardNo());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteKR(Connection conn, int krno) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete korea_recipe where kr_board_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, krno);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<KRBoard> searchKrT(Connection conn, String krtitle, int currentPage, int limit) {
		ArrayList<KRBoard> krblist = new ArrayList<KRBoard>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM (SELECT ROWNUM RNUM, KR_BOARD_NO, KR_BOARD_TITLE, THUMBNAIL_FILE, RENAME_FILE, KR_BOARD_DATE, COOK_NAME, ADMIN_ID, READ_COUNT "
				+ "FROM (SELECT * FROM KOREA_RECIPE WHERE KR_BOARD_TITLE LIKE ? ORDER BY kr_board_date)) WHERE RNUM >= ? AND RNUM <= ?";

		
		int start = (currentPage - 1) * limit + 1;
		int last = start + limit - 1;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + krtitle + "%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, last);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				KRBoard kb = new KRBoard();
				kb.setAdminId(rset.getString("admin_id"));
				kb.setCookName(rset.getString("cook_name"));
				kb.setKrBoardDate(rset.getDate("kr_board_date"));
				kb.setKrBoardNo(rset.getInt("kr_board_no"));
				kb.setKrBoardTitle(rset.getString("kr_board_title"));
				kb.setReadCount(rset.getInt("read_count"));
				kb.setThumbNailFile(rset.getString("thumbnail_file"));
				kb.setRenameFile(rset.getString("rename_file"));

				krblist.add(kb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return krblist;
	}

	public ArrayList<KRBoard> searchKrC(Connection conn, String krtitle, int currentPage, int limit) {
		ArrayList<KRBoard> krblist = new ArrayList<KRBoard>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int start = (currentPage - 1) * limit + 1;
		int last = start + limit - 1;
		
		String query = "SELECT * FROM (SELECT ROWNUM RNUM, KR_BOARD_NO, KR_BOARD_TITLE, THUMBNAIL_FILE, RENAME_FILE, KR_BOARD_DATE, COOK_NAME, ADMIN_ID, READ_COUNT "
				+ "FROM (SELECT * FROM KOREA_RECIPE WHERE cook_name LIKE ? ORDER BY kr_board_date)) WHERE RNUM >= ? AND RNUM <= ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + krtitle + "%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, last);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				KRBoard kb = new KRBoard();
				kb.setAdminId(rset.getString("admin_id"));
				kb.setCookName(rset.getString("cook_name"));
				kb.setKrBoardDate(rset.getDate("kr_board_date"));
				kb.setKrBoardNo(rset.getInt("kr_board_no"));
				kb.setKrBoardTitle(rset.getString("kr_board_title"));
				kb.setReadCount(rset.getInt("read_count"));
				kb.setThumbNailFile(rset.getString("thumbnail_file"));
				kb.setRenameFile(rset.getString("rename_file"));
				krblist.add(kb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return krblist;
	}

	public int insertCom(Connection conn, Comment c) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "insert into kr_comment values((select max(comm_no) from kr_comment) + 1, ?, ?, sysdate, ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, c.getKrBoardNo());
			pstmt.setString(2, c.getCommContent());
			pstmt.setString(3, c.getUserId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Comment> selectComment(Connection conn, int krno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Comment> colist = new ArrayList<Comment>();

		String query = "select * from kr_comment where kr_board_no = ? order by comm_no";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, krno);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Comment co = new Comment();
				co.setCommContent(rset.getString("comm_content"));
				co.setCommDate(rset.getDate("comm_date"));
				co.setCommNo(rset.getInt("comm_no"));
				co.setKrBoardNo(krno);
				co.setUserId(rset.getString("user_id"));

				colist.add(co);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return colist;
	}

	public int deletcComment(Connection conn, int cono) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete from kr_comment where comm_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cono);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int getListCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String query = "SELECT COUNT(kr_board_no) FROM korea_recipe";

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

	public ArrayList<KRBoard> Orderby(Connection conn, int currentPage, int limit, int orderby) {
		ArrayList<KRBoard> kblist = new ArrayList<KRBoard>();
		System.out.println("실행");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = null;
		if(orderby == 1) {
		query = "SELECT * FROM (SELECT ROWNUM RNUM, KR_BOARD_NO, KR_BOARD_TITLE, THUMBNAIL_FILE, RENAME_FILE, KR_BOARD_DATE, COOK_NAME, ADMIN_ID, READ_COUNT "
				+ "FROM (SELECT * FROM KOREA_RECIPE ORDER BY 2)) WHERE RNUM >= ? AND RNUM <= ?";
		} else if(orderby == 2){
		query = "SELECT * FROM (SELECT ROWNUM RNUM, KR_BOARD_NO, KR_BOARD_TITLE, THUMBNAIL_FILE, RENAME_FILE, KR_BOARD_DATE, COOK_NAME, ADMIN_ID, READ_COUNT "
				+ "FROM (SELECT * FROM KOREA_RECIPE ORDER BY 2 desc)) WHERE RNUM >= ? AND RNUM <= ?";
		} else if(orderby == 3) {
			query = "SELECT * FROM (SELECT ROWNUM RNUM, KR_BOARD_NO, KR_BOARD_TITLE, THUMBNAIL_FILE, RENAME_FILE, KR_BOARD_DATE, COOK_NAME, ADMIN_ID, READ_COUNT "
					+ "FROM (SELECT * FROM KOREA_RECIPE ORDER BY read_count desc, kr_board_date)) WHERE RNUM >= ? AND RNUM <= ?";
		}
		int start = (currentPage - 1) * limit + 1;
		int last = start + limit - 1;
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, last);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				KRBoard kb = new KRBoard();
				kb.setAdminId(rset.getString("admin_id"));
				kb.setCookName(rset.getString("cook_name"));
				kb.setKrBoardDate(rset.getDate("kr_board_date"));
				kb.setKrBoardNo(rset.getInt("kr_board_no"));
				kb.setKrBoardTitle(rset.getString("kr_board_title"));
				kb.setReadCount(rset.getInt("read_count"));
				kb.setThumbNailFile(rset.getString("thumbnail_file"));
				kb.setRenameFile(rset.getString("rename_file"));
				kblist.add(kb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return kblist;
	}

	
	public int updateComment(Connection conn, Comment co) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update kr_comment set comm_content = ? where kr_board_no = ? and comm_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, co.getCommContent());
			pstmt.setInt(2, co.getKrBoardNo());
			pstmt.setInt(3, co.getCommNo());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<KRContent> selectKrc(Connection conn, int krno) {
		ArrayList<KRContent> krclist = new ArrayList<KRContent>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from kr_content where kr_board_no = ? order by kr_count";

	
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, krno);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				KRContent krc = new KRContent();
				krc.setKrBoardNo(krno);
				krc.setKrBoardContent(rset.getString("kr_board_content"));
				krc.setKrCount(rset.getInt("kr_count"));
				krc.setKrOriginFile(rset.getString("kr_origin_file"));
				krc.setKrRenameFile(rset.getString("kr_rename_file"));
				
				krclist.add(krc);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return krclist;
	}

	
	public int insertKrCon(Connection conn, ArrayList<KRContent> krclist) {
		PreparedStatement pstmt = null;
		int result = 0;
		int insert = 0;
		String query = null;
		
		try {
			for(int i = 0; i < krclist.size(); i++) {
				
				KRContent krc = krclist.get(i);
				
				if(i == 0) {					
					query = "insert into kr_content values (?, 1, ?, ?, ?)";		
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, krc.getKrBoardNo());
					pstmt.setString(2, krc.getKrBoardContent());
					pstmt.setString(3, krc.getKrOriginFile());
					pstmt.setString(4, krc.getKrRenameFile());
					
					insert += pstmt.executeUpdate();
				
				}else {
					query = "insert into kr_content values (?,(select max(kr_count) from kr_content where kr_board_no = ?)+1, ?, ?, ?)";
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, krc.getKrBoardNo());
					pstmt.setInt(2,krc.getKrBoardNo());
					pstmt.setString(3, krc.getKrBoardContent());
					pstmt.setString(4, krc.getKrOriginFile());
					pstmt.setString(5, krc.getKrRenameFile());
					
					insert += pstmt.executeUpdate();
				
				}			
			}
			
			if(insert == krclist.size()) {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectKrno(Connection conn, String adminId) {
		Statement stmt = null;
		ResultSet rset = null;
		int krno = 0;
		String query = "select kr_board_no from (select rownum rum, kr_board_no from korea_recipe order by kr_board_no desc) where rum = 1";
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			if(rset.next()) {
			krno = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return krno;
	}

	public int updateKRC(Connection conn, ArrayList<KRContent> krclist) {
		PreparedStatement pstmt = null;
		int result = 0;
		int insert = 0;
		String query = null;
		try {
			for(int i = 0; i < krclist.size(); i++) {
				if(krclist.get(i).getKrRenameFile() == null && krclist.get(i).getKrOriginFile() == null) {
					query = "update kr_content set kr_board_content = ?, kr_origin_file = null, kr_rename_file = null where kr_board_no = ? and kr_count = ?";
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, krclist.get(i).getKrBoardContent());
					pstmt.setInt(2, krclist.get(i).getKrBoardNo());
					pstmt.setInt(3, krclist.get(i).getKrCount());
					insert += pstmt.executeUpdate();
				} else {
					query = "update kr_content set kr_board_content = ?, kr_origin_file = ?, kr_rename_file = ? where kr_board_no = ? and kr_count = ?";
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, krclist.get(i).getKrBoardContent());
					pstmt.setString(2, krclist.get(i).getKrOriginFile());
					pstmt.setString(3, krclist.get(i).getKrRenameFile());
					pstmt.setInt(4, krclist.get(i).getKrBoardNo());
					pstmt.setInt(5, krclist.get(i).getKrCount());
					insert += pstmt.executeUpdate();
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		if(insert == krclist.size()) {
			
			result = 1;
		}
		return result;
	}

	public int updateReadCount(Connection conn, int krno) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update korea_recipe set read_count = (select max(read_count) from korea_recipe where kr_board_no = ?)+1 where kr_board_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, krno);
			pstmt.setInt(2, krno);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertUpdateKrCon(Connection conn, ArrayList<KRContent> krclist2) {
		PreparedStatement pstmt = null;
		int result = 0;
		int insert = 0;
		String query = null;

		try {
			for (int i = 0; i < krclist2.size(); i++) {

				KRContent krc = krclist2.get(i);

				query = "insert into kr_content values (?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, krc.getKrBoardNo());
				pstmt.setInt(2, krc.getKrCount());
				pstmt.setString(3, krc.getKrBoardContent());
				pstmt.setString(4, krc.getKrOriginFile());
				pstmt.setString(5, krc.getKrRenameFile());

				insert += pstmt.executeUpdate();

			}
			if (insert == krclist2.size()) {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteCom(Connection conn, int krno) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete from kr_comment where kr_board_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, krno);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteKrCon(Connection conn, int krno) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete from kr_content where kr_board_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, krno);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteKrConOne(Connection conn, int krBoardNo, int i) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete from kr_content where kr_board_no = ? and kr_count = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, krBoardNo);
			pstmt.setInt(2, i);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteMaterial(Connection conn, int krno) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete KRECIPE_MATERIAL_LIST where kr_board_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, krno);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deletePackages(Connection conn, int krno) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete package where kr_board_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, krno);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int countKrT(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(kr_board_no) from korea_recipe where kr_board_title like ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
			result = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int countKrC(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(kr_board_no) from korea_recipe where cook_name like ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt(1);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<KRBoard> selectAllList(Connection conn, int currentPage, int limit) {
		ArrayList<KRBoard> kblist = new ArrayList<KRBoard>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "SELECT * FROM (SELECT ROWNUM RNUM, KR_BOARD_NO, KR_BOARD_TITLE, THUMBNAIL_FILE, RENAME_FILE, KR_BOARD_DATE, COOK_NAME, ADMIN_ID, READ_COUNT "
				+ "FROM (SELECT * FROM KOREA_RECIPE ORDER BY kr_board_date)) WHERE RNUM >= ? AND RNUM <= ?";

		int start = (currentPage - 1) * limit + 1;
		int last = start + limit - 1;
		
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, last);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				KRBoard kb = new KRBoard();
				kb.setCookName(rset.getString("cook_name"));
				kb.setKrBoardDate(rset.getDate("kr_board_date"));
				kb.setKrBoardNo(rset.getInt("kr_board_no"));
				kb.setKrBoardTitle(rset.getString("kr_board_title"));
				kb.setReadCount(rset.getInt("read_count"));
				kblist.add(kb);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return kblist;
	
	}

}


















