package my_recipe_board.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import korea_recipe_board.model.vo.KRContent;
import my_recipe_board.model.vo.Comment;
import my_recipe_board.model.vo.LH;
import my_recipe_board.model.vo.MrContent;
import my_recipe_board.model.vo.MyRecipe;

public class MyRecipeDao {

	public MyRecipeDao() {

	}

	// 게시글 상세보기 처리용 컨트롤러 끝
	public ArrayList<MyRecipe> selectList(Connection conn, int currentPage, int limit) {
		ArrayList<MyRecipe> list = new ArrayList<MyRecipe>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from  (SELECT ROWNUM RNUM, MR_BOARD_NO, MR_BOARD_TITLE, MR_BOARD_CONTENT,  MR_BOARD_DATE, MR_BOARD_ORIGINFILE, MR_BOARD_RENAMEFILE, USER_ID, READ_COUNT FROM (SELECT * FROM MY_RECIPE ORDER BY MR_BOARD_DATE)) WHERE RNUM >= ? AND RNUM <= ?";

		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				MyRecipe mr = new MyRecipe();
				mr.setMrBoardNo(rset.getInt("mr_board_no"));
				mr.setMrBoardTitle(rset.getString("mr_board_title"));
				mr.setMrBoardContent(rset.getString("mr_board_content"));
				mr.setMrBoardDate(rset.getDate("mr_board_date"));
				mr.setMrboardoriginFile(rset.getString("mr_board_originfile"));
				mr.setMrboardrenameFile(rset.getString("mr_board_renamefile"));
				mr.setUserId(rset.getString("user_id"));
				mr.setReadCount(rset.getInt("read_count"));

				list.add(mr);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;

	}

	// 번호 게시글 조회 (selectOne) 끝
	public MyRecipe selectMyRecipe(Connection conn, int mrno) {

		MyRecipe mr = new MyRecipe();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "SELECT * FROM MY_RECIPE WHERE MR_BOARD_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mrno);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				mr.setMrBoardNo(mrno);
				mr.setMrBoardTitle(rset.getString("mr_board_title"));
				mr.setMrBoardContent(rset.getString("mr_board_content"));
				mr.setMrBoardDate(rset.getDate("mr_board_date"));
				mr.setMrboardoriginFile(rset.getString("mr_board_originfile"));
				mr.setMrboardrenameFile(rset.getString("mr_board_renamefile"));
				mr.setUserId(rset.getString("user_id"));
				mr.setReadCount(rset.getInt("read_count"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return mr;
	}

	// 글쓰기 쿼리 ok
	public int insertMyRecipe(Connection conn, MyRecipe myrecipe) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "insert into my_recipe values((select max(mr_board_no) from my_recipe) + 1, ?, ?, sysdate, ?, ?, ?, default)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, myrecipe.getMrBoardTitle());
			pstmt.setString(2, myrecipe.getMrBoardContent());
			pstmt.setString(3, myrecipe.getMrboardoriginFile());
			pstmt.setString(4, myrecipe.getMrboardrenameFile());
			pstmt.setString(5, myrecipe.getUserId());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	

	// 글 삭제 .... 쿼리 ok
	public int delectMyRecipe(Connection conn, int mrno) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete from my_recipe where mr_board_no = ? ";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mrno);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 제목으로 검색 끝
	public ArrayList<MyRecipe> searchMRT(Connection conn, String mrtitle) {
		ArrayList<MyRecipe> mrlist = new ArrayList<MyRecipe>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "SELECT * FROM MY_RECIPE WHERE MR_BOARD_TITLE LIKE ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + mrtitle + "%");

			rset = pstmt.executeQuery();

			while (rset.next()) {

				MyRecipe mr = new MyRecipe();

				mr.setMrBoardNo(rset.getInt("mr_board_no"));
				mr.setMrBoardTitle(rset.getString("mr_board_title"));
				mr.setMrBoardContent(rset.getString("mr_board_content"));
				mr.setMrBoardDate(rset.getDate("mr_board_date"));
				mr.setMrboardoriginFile(rset.getString("mr_board_originfile"));
				mr.setMrboardrenameFile(rset.getString("mr_board_renamefile"));
				mr.setUserId(rset.getString("user_id"));
				mr.setReadCount(rset.getInt("read_count"));
				mrlist.add(mr);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return mrlist;

	}

	// 댓글 삽입
	public int insertCom(Connection conn, Comment cm) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into mr_comment values((select count(comm_no) from mr_comment) + 1, ?, ?, sysdate, ? )";
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, cm.getMrBoardNo());
			pstmt.setString(2, cm.getCommContent());
			pstmt.setString(3, cm.getUserId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 댓글 뭐시기 쿼리 ok
	public ArrayList<Comment> selectComment(Connection conn, int mrno) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Comment> colist = new ArrayList<Comment>();

		String query = "select * from MR_COMMENT where MR_BOARD_NO = ? order by COMM_NO";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mrno);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Comment co = new Comment();
				co.setMrBoardNo(mrno);
				co.setCommContent(rset.getString("comm_content"));
				co.setCommDate(rset.getDate("comm_date"));
				co.setUserId(rset.getString("user_id"));
				co.setCommNo(rset.getInt("comm_no"));

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

	// 댓들 삭제 피요 delete 쿼리 ok
	public int deleteComment(Connection conn, int cono) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete from mr_comment where comm_no = ?";

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

	// 조회수 증가 쿼리 ok
	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;

		String query = "SELECT COUNT(mr_board_no) " + "FROM my_recipe";

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

	// 정렬 끝
	public ArrayList<MyRecipe> Orderby(Connection conn, int currentPage, int limit, int orderby) {
		ArrayList<MyRecipe> mrlist = new ArrayList<MyRecipe>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = null;

		if (orderby == 1) {
			query = "SELECT * FROM (SELECT ROWNUM RNUM, MR_BOARD_NO, MR_BOARD_TITLE, MR_BOARD_CONTENT, MR_BOARD_DATE, MR_BOARD_ORIGINFILE, MR_BOARD_RENAMEFILE, USER_ID, READ_COUNT FROM (SELECT * FROM MY_RECIPE ORDER BY 2)) WHERE RNUM >= ? AND RNUM <= ?";
		} else {
			query = "SELECT * FROM (SELECT ROWNUM RNUM, MR_BOARD_NO, MR_BOARD_TITLE, MR_BOARD_CONTENT, MR_BOARD_DATE, MR_BOARD_ORIGINFILE, MR_BOARD_RENAMEFILE, USER_ID, READ_COUNT FROM (SELECT * FROM MY_RECIPE ORDER BY 2 DESC)) WHERE RNUM >= ? AND RNUM <= ?";
		}

		int start = (currentPage - 1) * limit + 1;
		int last = start + limit - 1;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, last);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				MyRecipe mr = new MyRecipe();
				mr.setMrBoardTitle(rset.getString("mr_board_title"));
				mr.setMrBoardContent(rset.getString("mr_board_content"));
				mr.setMrBoardDate(rset.getDate("mr_board_date"));
				mr.setMrboardoriginFile(rset.getString("mr_board_originfile"));
				mr.setMrboardrenameFile(rset.getString("mr_board_renamefile"));
				mr.setUserId(rset.getString("user_id"));
				mr.setReadCount(rset.getInt("read_count"));
				mr.setMrBoardNo(rset.getInt("mr_board_no"));
				mrlist.add(mr);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return mrlist;
	}

	// 댓글 수정
	public int selectCommentOne(Connection conn, Comment co) {

		PreparedStatement pstmt = null;
		int result = 0;

		String query = "update mr_comment set comm_content = ? where mr_board_no = ? and comm_no = ?";

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, co.getCommContent());
			pstmt.setInt(2, co.getMrBoardNo());
			pstmt.setInt(3, co.getCommNo());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 좋아요 수 증가
	public int insertLh(Connection conn, LH lh, String key) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = null;
		if (key.equals("Y")) {
			query = "insert into lh values(?, ?, ?, null)";
		} else {
			query = "insert into lh values(?, ?, null, ?)";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, lh.getMrBoardNo());
			pstmt.setString(2, lh.getUserId());
			pstmt.setString(3, key);

			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int[] selectLh(Connection conn, int mrno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int[] result = new int[2];
		String query = "select count(likes), count(hates) from lh where mr_board_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mrno);

			rset = pstmt.executeQuery();
			if (rset.next()) {
				result[0] = rset.getInt(1);
				result[1] = rset.getInt(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return result;
	}

	public int deleteAllComment(Connection conn, int mrno) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete from mr_comment where mr_board_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mrno);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<MrContent> selectMrc(Connection conn, int mrno) {
		ArrayList<MrContent> mrclist = new ArrayList<MrContent>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from mr_content where mr_board_no = ? order by mr_count";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mrno);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				MrContent mrc = new MrContent();
				mrc.setMrBoardNo(mrno);
				mrc.setMrBoardContent(rset.getString("mr_board_content"));
				mrc.setMrCount(rset.getInt("mr_count"));
				mrc.setMrOriginFile(rset.getString("mr_origin_file"));
				mrc.setMrRenamefile(rset.getString("mr_rename_file"));
				
				mrclist.add(mrc);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return mrclist;
	}

	public int selectMrno(Connection conn, String userId) {

		Statement stmt = null;
		ResultSet rset = null;
		int mrno = 0;
		String query = "select mr_board_no from (select rownum rum, mr_board_no from my_recipe order by mr_board_no desc) where rum = 1";

		try {
			stmt = conn.createStatement();

			rset = stmt.executeQuery(query);
			if (rset.next()) {
				mrno = rset.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return mrno;

	}

	public int insertMrCon(Connection conn, ArrayList<MrContent> mrclist) {
		PreparedStatement pstmt = null;
		int result = 0;
		int insert = 0;
		String query = null;
		try {
			for (int i = 0; i < mrclist.size(); i++) {

				MrContent mrc = mrclist.get(i);
				
					query = "insert into mr_content values (?,(select count(mr_count) from mr_content where mr_board_no = ?)+1, ?, ?, ?)";
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, mrc.getMrBoardNo());
					pstmt.setInt(2, mrc.getMrBoardNo());
					pstmt.setString(3, mrc.getMrBoardContent());
					pstmt.setString(4, mrc.getMrOriginFile());
					pstmt.setString(5, mrc.getMrRenamefile());

					insert += pstmt.executeUpdate();
			}

			if (insert == mrclist.size()) {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateMrc(Connection conn, ArrayList<MrContent> mrclist) {
		PreparedStatement pstmt = null;
		int result = 0;
		int insert = 0;
		String query = null;
		

		try {
			for(int i = 0; i < mrclist.size(); i++) {
				if((mrclist.get(i).getMrRenamefile() == null && mrclist.get(i).getMrOriginFile() == null)) {
			query = "update mr_content set mr_board_content = ?, mr_origin_file = null, mr_rename_file = null where mr_board_no = ? and mr_count = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mrclist.get(i).getMrBoardContent());
			pstmt.setInt(2, mrclist.get(i).getMrBoardNo());
			pstmt.setInt(3, mrclist.get(i).getMrCount());
			
			} else {
				query = "update mr_content set mr_board_content = ?, mr_origin_file = ?, mr_rename_file = ? where mr_board_no = ? and mr_count = ?";
				pstmt.setString(1, mrclist.get(i).getMrBoardContent());
				pstmt.setString(2, mrclist.get(i).getMrOriginFile());
				pstmt.setString(3, mrclist.get(i).getMrRenamefile());
				pstmt.setInt(4, mrclist.get(i).getMrBoardNo());
				pstmt.setInt(5, mrclist.get(i).getMrCount());
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		if(insert == mrclist.size()) {
			result = 1;
		}
		return result;
	}

	public int deleteLH(Connection conn, int mrno) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete from lh where mr_board_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mrno);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 게시글 수정
	public int insertUpdateMrCon(Connection conn, ArrayList<MrContent> mrclist2) {
		PreparedStatement pstmt = null;
		int result = 0;
		int insert = 0;
		String query = null;
		
		try {
			for (int i = 0; i < mrclist2.size(); i++) {
				MrContent mrc = mrclist2.get(i);
				
				query = "insert into mr_content values (?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, mrc.getMrBoardNo());
				pstmt.setInt(2, mrc.getMrCount());
				pstmt.setString(3, mrc.getMrBoardContent());
				pstmt.setString(4, mrc.getMrOriginFile());
				pstmt.setString(5, mrc.getMrRenamefile());

				insert += pstmt.executeUpdate();
				
			}
			if (insert == mrclist2.size()) {
				result = 1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}

	public int deletemrConOne(Connection conn, int mrBoardNo, int i) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete from mr_content where mr_board_no = ? and mr_count = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mrBoardNo);
			pstmt.setInt(2, i);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	
	
	public int updateMR(Connection conn, MyRecipe mrno) {

		int result = 0;
		PreparedStatement pstmt = null;

		String query = "UPDATE MY_RECIPE set MR_BOARD_TITLE = ? , MR_BOARD_CONTENT = ?, MR_BOARD_ORIGINFILE = ?, MR_BOARD_RENAMEFILE = ? where MR_BOARD_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mrno.getMrBoardTitle());
			pstmt.setString(2, mrno.getMrBoardContent());
			pstmt.setString(3, mrno.getMrboardoriginFile());
			pstmt.setString(4, mrno.getMrboardrenameFile());
			pstmt.setInt(5, mrno.getMrBoardNo());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<MyRecipe> selectAllUser(Connection conn, int currentPage, int limit, String userid) {
		ArrayList<MyRecipe> list = new ArrayList<MyRecipe>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from  (SELECT ROWNUM RNUM, MR_BOARD_NO, MR_BOARD_TITLE, MR_BOARD_CONTENT,  MR_BOARD_DATE, MR_BOARD_ORIGINFILE, MR_BOARD_RENAMEFILE, USER_ID, READ_COUNT FROM (SELECT * FROM MY_RECIPE where user_id = ? ORDER BY MR_BOARD_DATE)) WHERE RNUM >= ? AND RNUM <= ?";

		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				MyRecipe mr = new MyRecipe();
				mr.setMrBoardNo(rset.getInt("mr_board_no"));
				mr.setMrBoardTitle(rset.getString("mr_board_title"));
				mr.setMrBoardContent(rset.getString("mr_board_content"));
				mr.setMrBoardDate(rset.getDate("mr_board_date"));
				mr.setMrboardoriginFile(rset.getString("mr_board_originfile"));
				mr.setMrboardrenameFile(rset.getString("mr_board_renamefile"));
				mr.setUserId(rset.getString("user_id"));
				mr.setReadCount(rset.getInt("read_count"));

				list.add(mr);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int deleteMrMaterial(Connection conn, int mrno) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from MRECIPE_MATERIAL_LIST where mr_board_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mrno);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int updateReadCount(Connection conn, int mrno) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update my_recipe set read_count = (select max(read_count) from my_recipe where mr_board_no = ?)+1 where mr_board_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mrno);
			pstmt.setInt(2, mrno);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	
	
	
	
}
