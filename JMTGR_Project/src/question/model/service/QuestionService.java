package question.model.service;

import static common.JDBCTemp.close;
import static common.JDBCTemp.commit;
import static common.JDBCTemp.getConnection;
import static common.JDBCTemp.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import question.model.dao.QuestionDao;
import question.model.vo.Question;
import question.model.vo.Reply;

public class QuestionService {
	private QuestionDao qdao = new QuestionDao();
	
	public QuestionService() {}
	
	public ArrayList<Question> selectTop3() {
		Connection conn = getConnection();
		ArrayList<Question> list = qdao.selectTop3(conn);
		close(conn);
		return list;
	}

	public ArrayList<Question> selectList(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Question> list = qdao.selectList(conn, currentPage, limit);
		close(conn);
		return list;
	}

	public int insertOriginQuestion(Question question) {
		Connection conn = getConnection();
		int result = qdao.insertOriginBoard(conn, question);
		if( result > 0) 
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public void addReadCount(int qusNo) {
		Connection conn = getConnection();
		int result = qdao.addReadCount(conn, qusNo);
		if( result > 0) 
			commit(conn);
		else 
			rollback(conn);
		close(conn);
	}
	public Question selectQuestion(int qusNo) {
		Connection conn = getConnection();
		Question question = qdao.selectQuestion(conn, qusNo);
		close(conn);
		return question;
	}

	public int updateReply(Reply reply) {
		Connection conn = getConnection();
		int result = qdao.updateReply(conn,reply);
		if( result > 0) 
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}
	
	public int updateOrigin(Question question) {
		Connection conn = getConnection();
		int result = qdao.updateOrigin(conn,question);
		if( result > 0) 
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int getListCount() {
			Connection conn = getConnection();
			int listCount = qdao.getListCount(conn);
			close(conn);
			return listCount;
		}



	public int updateQuestion(Question question) {
		Connection conn = getConnection();
		int result = qdao.updateQuestion(conn, question);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int insertReply(Reply reply) {
		Connection conn = getConnection();
		int result = qdao.insertReply(conn, reply);
		if( result > 0) 
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int deleteQuestion(int qusNo) {
		Connection conn = getConnection();
		int result = qdao.deleteQuestion(conn, qusNo);
		if( result > 0) 
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public ArrayList<Question> selectSearchUserid(String keyword) {
		Connection conn = getConnection();
		ArrayList<Question> list = qdao.selectUserid(conn, keyword);
		close(conn);
		return list;
		
	}

	public ArrayList<Reply> selectReply(int[] nolist) {
		Connection conn = getConnection();
		ArrayList<Reply> list = qdao.selectReply(conn, nolist);
		close(conn);
		return list;
	}

	public int pwdCheck(String qpwd, int qno) {
		Connection conn = getConnection();
		int result = qdao.pwdCheck(conn, qpwd, qno);
		close(conn);
		if(result > 0) {
			return result;
		}else {
			return 0;
		}
	}

	public Reply selectReplyOne(int qusNo) {
		Connection conn = getConnection();
		Reply reply = qdao.selectReplyOne(conn, qusNo);
		close(conn);
		return reply;
	}

	public int deleteReply(int qno) {
		Connection conn = getConnection();
		int result = qdao.deleteReply(conn, qno);
		close(conn);
		if( result > 0) 
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}


}


