package question.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import question.model.vo.Question;
import question.model.vo.Reply;

public class QuestionDao {
	public QuestionDao() {}

	public ArrayList<Question> selectTop3(Connection conn) {
		ArrayList<Question> list = new ArrayList<Question>();
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select * from ( select rownum rnum, qus_no, qus_title, read_count from ("
				     + "select * from question order by read_count desc )) "
				     + "where rnum >= 1 and rnum <= 5 " ;

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				Question question = new Question();

				question.setQusNo(rset.getInt("qus_no"));
				question.setQusTitle(rset.getString("qus_title"));
				question.setQusReadCount(rset.getInt("read_count"));

				list.add(question);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
	}

	public ArrayList<Question> selectList(Connection conn, int currentPage, int limit) {
		ArrayList<Question> list = new ArrayList<Question>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = " select * from (select rownum rnum, qus_no, qus_title, qus_content, qus_board_date, qus_pwd, user_id, qus_original_filename, qus_rename_filename, read_count "
				     + " from(select * from question )) "
				     + " where rnum >= ? and rnum <= ?";

		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Question question = new Question();

				question.setQusNo(rset.getInt("qus_no"));
				question.setQusTitle(rset.getString("qus_title"));
				question.setQusContent(rset.getString("qus_content"));
				question.setQusBoardDate(rset.getDate("qus_board_date"));
				question.setQusPwd(rset.getString("qus_pwd"));
				question.setUserId(rset.getString("user_Id"));
				question.setQusOriginalFileName(rset.getString("qus_original_filename"));
				question.setQusRenameFileName(rset.getString("qus_rename_filename"));
				question.setQusReadCount(rset.getInt("read_count"));
				
				list.add(question);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int insertOriginBoard(Connection conn, Question question) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "insert into question values ((select max(qus_no) + 1 from question), ?, ?, ?, 'user01', sysdate, ?, ?, default)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, question.getQusTitle());
			pstmt.setString(2, question.getQusContent());
			pstmt.setString(3, question.getQusPwd());
			pstmt.setString(4, question.getQusOriginalFileName());
			pstmt.setString(5, question.getQusRenameFileName());
  
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int addReadCount(Connection conn, int qusNo) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update question set read_count = read_count + 1 where qus_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qusNo);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public Question selectQuestion(Connection conn, int qusNo) {
		Question question = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from question where qus_no = ? ";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qusNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				question = new Question();

				question.setQusNo(qusNo);
				question.setQusTitle(rset.getString("qus_title"));
				question.setQusContent(rset.getString("qus_content"));
				question.setQusBoardDate(rset.getDate("qus_board_date"));
				question.setQusOriginalFileName("qus_original_filename");
				question.setQusRenameFileName(rset.getString("qus_rename_filename"));
				question.setQusReadCount(rset.getInt("read_count"));
				question.setUserId(rset.getString("user_id"));
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return question;
	}

	public int deleteQuestion(Connection conn, int qusNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from question where user_id = 'user01'";
		

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
		
		String query = "update question_answer set qus_ans_content= ? where qus_no = ?  " ;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reply.getQusReplyContent());
			pstmt.setInt(2, reply.getQusNo());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateOrigin(Connection conn, Question question) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update question set qus_title = ?, qus_content = ?, qus_original_filename = ?, qus_rename_filename = ? where qus_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, question.getQusTitle());
			pstmt.setString(2, question.getQusContent());
			pstmt.setString(3, question.getQusOriginalFileName());
			pstmt.setString(4, question.getQusRenameFileName());
			pstmt.setInt(5, question.getQusNo());
			
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

		String query = "select count(*) from question";
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
	
	public int updateQuestion(Connection conn, Question question) {
		  int result = 0;
		   PreparedStatement pstmt = null;
		      
		      String query = "update question set qus_title = ? , qus_content = ? , qus_board_date = sysdate where qus_no = ?";
		      
		      try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, question.getQusTitle());
				pstmt.setString(2, question.getQusContent());
				pstmt.setInt(3, question.getQusNo());
				
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
		      return result;
		   }

	public int insertReply(Connection conn, Reply reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into question_answer values (?, ?, sysdate)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reply.getQusNo());
			pstmt.setString(2, reply.getQusReplyContent());

		
			result = pstmt.executeUpdate();
		} catch (Exception e) {
	        e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Question> selectUserid(Connection conn, String keyword) {
		ArrayList<Question> list = new ArrayList<Question>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from question where user_id like ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Question question = new Question();
				
				question.setQusNo(rset.getInt("qus_no"));
				question.setQusTitle(rset.getString("qus_title"));
				question.setQusContent(rset.getString("qus_content"));
				question.setQusBoardDate(rset.getDate("qus_board_date"));
				question.setQusPwd(rset.getString("qus_pwd"));
				question.setUserId(rset.getString("user_Id"));
				question.setQusOriginalFileName(rset.getString("qus_original_filename"));
				question.setQusRenameFileName(rset.getString("qus_rename_filename"));
				question.setQusReadCount(rset.getInt("read_count"));
				
				list.add(question);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
	
		return list;
	}

	public ArrayList<Reply> selectReply(Connection conn, int[] nolist) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Reply> rlist = new ArrayList<Reply>();
		String query = "select * from question_answer where qus_no = ?";
		
		try {
			for(int i = 0; i < nolist.length; i++) {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, nolist[i]);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					Reply r = new Reply();
					r.setQusNo(rset.getInt("qus_no"));
					r.setQusReplyContent(rset.getString("qus_ans_content"));
					r.setQusReplyDate(rset.getDate("qus_ans_date"));
					
					rlist.add(r);
				}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return rlist;
	}

	public int pwdCheck(Connection conn, String qpwd, int qno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(qus_no) from question where qus_pwd = ? and qus_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, qpwd);
			pstmt.setInt(2, qno);
			
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

	public Reply selectReplyOne(Connection conn, int qusNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Reply reply = null;
		String query = "select * from question_answer where qus_no = ?";
				
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qusNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				reply = new Reply();
				reply.setQusNo(qusNo);
				reply.setQusReplyContent(rset.getString("qus_ans_content"));
				reply.setQusReplyDate(rset.getDate("qus_ans_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return reply;
	}

	public int deleteReply(Connection conn, int qno) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete question_answer where qus_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qno);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}




















