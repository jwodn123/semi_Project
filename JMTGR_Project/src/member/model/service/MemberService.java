package member.model.service;

import static common.JDBCTemp.close;
import static common.JDBCTemp.commit;
import static common.JDBCTemp.getConnection;
import static common.JDBCTemp.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {
	//의존성 주입
	private MemberDao mdao = new MemberDao();
	
	public MemberService() {}
	
	public Member loginCheck(String userid, String userpwd) {
		Connection conn = getConnection();
		Member member = mdao.loginCheck(conn, userid, userpwd);
		close(conn);		
		return member;
	}
	
	public Member selectMember(String userid) {
		Connection conn = getConnection();
		Member member = mdao.selectMember(conn, userid);
		close(conn);
		return member;
	}
	
	public ArrayList<Member> selectList(){
		Connection conn = getConnection();
		ArrayList<Member> list = mdao.selectList(conn);
		close(conn);
		
		return list;
	}
	
	public ArrayList<Member> selectSearchUserid(String keyword){
		Connection conn = getConnection();
		ArrayList<Member> list = mdao.selectSearchUserid(conn, keyword);
		close(conn);
		
		return list;
	}
	
	public ArrayList<Member> selectSearchLoginOk(String keyword){
		Connection conn = getConnection();
		ArrayList<Member> list = mdao.selectSearchLoginOk(conn, keyword);
		close(conn);
		
		return list;
	}
	
	public int insertMember(Member Member) {
		Connection conn = getConnection();
		int result = mdao.insertMember(conn, Member);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
			return result;
	}
	
	public int updateMember(Member Member) {
		Connection conn = getConnection();
		int result = mdao.updateMember(conn, Member);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;		
	}
	
	
	
	public int updateLoginOk(String userid, String loginok) {
		Connection conn = getConnection();
		int result = mdao.updateLoginOk(conn, userid, loginok);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int deleteMember(String userid, String userpwd) {
		Connection conn = getConnection();
		int result = mdao.deleteMember(conn, userid, userpwd);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int selectCheckId(String userid) {
		Connection conn = getConnection();
		int idcount = mdao.selectCheckId(conn, userid);
		close(conn);
		return idcount;
	}
	
	public Member MemberSearchId(String username, String userno) {
		Connection conn = getConnection();
		Member member = mdao.memberSearchId(conn, username, userno);
		close(conn);
		
		return member;
	}
	
	public int MemberSearchPwd(String userid, String username, String userno) {
		Connection conn = getConnection();
		int result = mdao.memberSearchPwd(conn, userid, username, userno);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	
	public int updatePwd(String userid, String getTempPassword) {
		//임시비밀번호 저장용
		Connection conn = getConnection();
		int result = mdao.updatePwd(conn, userid, getTempPassword);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
		
}




























