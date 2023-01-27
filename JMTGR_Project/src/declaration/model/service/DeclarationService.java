package declaration.model.service;

import static common.JDBCTemp.close;
import static common.JDBCTemp.commit;
import static common.JDBCTemp.getConnection;
import static common.JDBCTemp.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import declaration.model.dao.DeclarationDao;
import declaration.model.vo.Declaration;
import declaration.model.vo.Reply;


public class DeclarationService {
	private DeclarationDao ddao = new DeclarationDao();
	
	public DeclarationService() {}
	
	public ArrayList<Declaration> selectList(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Declaration> list = ddao.selectList(conn, currentPage, limit);
		close(conn);
		return list;
	}

	public int insertOriginDeclaration(Declaration declaration) {
		Connection conn = getConnection();
		int result = ddao.insertOriginDeclaration(conn, declaration);
		if( result > 0) 
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}
	
	public Declaration selectDeclaration(int deNo) {
		Connection conn = getConnection();
		Declaration declaration = ddao.selectDeclaration(conn, deNo);
		close(conn);
		return declaration;
	}
	
	public int deleteDeclaration(int deNo) {
		Connection conn = getConnection();
		int result = ddao.deleteDeclaration(conn, deNo);
		if( result > 0) 
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}
	
	public int updateReply(Reply reply) {
		Connection conn = getConnection();
		int result = ddao.updateReply(conn,reply);
		if( result > 0) 
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}
	
	public int updateOrigin(Declaration declaration) {
		Connection conn = getConnection();
		int result = ddao.updateOrigin(conn,declaration);
		if( result > 0) 
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int getListCount() {
			Connection conn = getConnection();
			int listCount = ddao.getListCount(conn);
			close(conn);
			return listCount;
	}


	public int updateDeclaration(Declaration declaration) {
		Connection conn = getConnection();
		int result = ddao.updateDeclaration(conn, declaration);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int insertReply(Reply reply) {
		Connection conn = getConnection();
		int result = ddao.insertReply(conn, reply);
		if( result > 0) 
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public ArrayList<Declaration> selectSearchUserid(String keyword) {
		Connection conn = getConnection();
		ArrayList<Declaration> list = ddao.selectSearchUserid(conn, keyword);
		close(conn);
		return list;
	}
	
}



