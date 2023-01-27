package member.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import member.model.vo.Member;

public class MemberDao {
	public MemberDao() {
	}

	public Member loginCheck(Connection conn, String userid, String userpwd) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from member where user_id = ? and user_pwd = ? and login_ok = 'Y'";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			pstmt.setString(2, userpwd);


			rset = pstmt.executeQuery();

			if (rset.next()) {
				member = new Member();

				member.setUserId(userid);
				member.setUserPwd(userpwd);
				member.setUserName(rset.getString("user_name"));
				member.setUserNo(rset.getString("user_no"));
				member.setPhone(rset.getString("phone"));
				member.setGender(rset.getString("gender"));
				member.setAddress1(rset.getString("address1"));
				member.setAddress2(rset.getString("address2"));
				member.setAddress3(rset.getString("address3"));
				member.setEmail(rset.getString("email"));
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return member;
	}

	public Member selectMember(Connection conn, String userid) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from member where user_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				member = new Member();
				
				member.setUserId(userid);
				member.setUserPwd(rset.getString("user_pwd"));
				member.setUserName(rset.getString("user_name"));
				member.setUserNo(rset.getString("user_no"));
				member.setPhone(rset.getString("phone"));
				member.setGender(rset.getString("gender"));
				member.setAddress1(rset.getString("address1"));
				member.setAddress2(rset.getString("address2"));
				member.setAddress3(rset.getString("address3"));
				member.setEmail(rset.getString("email"));
				member.setLogin_Ok(rset.getString("login_ok"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		return member;
	}

	public ArrayList<Member> selectList(Connection conn) {
		ArrayList<Member> list = new ArrayList<Member>();
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select * from member";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				Member member = new Member();

				member.setUserId(rset.getString("user_id"));
				member.setUserPwd(rset.getString("user_pwd"));
				member.setUserName(rset.getString("user_name"));
				member.setUserNo(rset.getString("user_no"));
				member.setPhone(rset.getString("phone"));
				member.setGender(rset.getString("gender"));
				member.setAddress1(rset.getString("address1"));
				member.setAddress2(rset.getString("address2"));
				member.setAddress3(rset.getString("address3"));
				member.setEmail(rset.getString("email"));
				member.setLogin_Ok(rset.getString("login_ok"));
				
				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}

		return list;
	}

	public ArrayList<Member> selectSearchUserid(Connection conn, String keyword) {
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from member where user_id like ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Member member = new Member();

				member.setUserId(rset.getString("user_id"));
				member.setUserPwd(rset.getString("user_pwd"));
				member.setUserName(rset.getString("user_name"));
				member.setUserNo(rset.getString("user_no"));
				member.setPhone(rset.getString("phone"));
				member.setGender(rset.getString("gender"));
				member.setAddress1(rset.getString("address1"));
				member.setAddress2(rset.getString("address2"));
				member.setAddress3(rset.getString("address3"));
				member.setEmail(rset.getString("email"));
				member.setLogin_Ok(rset.getString("login_ok"));

				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		return list;
	}
	
	public ArrayList<Member> selectSearchLoginOk(Connection conn, String keyword) {
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from member where login_ok = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Member member = new Member();

				member.setUserId(rset.getString("user_id"));
				member.setUserPwd(rset.getString("user_pwd"));
				member.setUserName(rset.getString("user_name"));
				member.setUserNo(rset.getString("user_no"));
				member.setPhone(rset.getString("phone"));
				member.setGender(rset.getString("gender"));
				member.setAddress1(rset.getString("address1"));
				member.setAddress2(rset.getString("address2"));
				member.setAddress3(rset.getString("address3"));
				member.setEmail(rset.getString("email"));
				member.setLogin_Ok(rset.getString("login_ok"));

				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		return list;
	}

	public int insertMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into member values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, default)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getUserNo());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getGender());
			pstmt.setString(7, member.getAddress1());
			pstmt.setString(8, member.getAddress2());
			pstmt.setString(9, member.getAddress3());
			pstmt.setString(10, member.getEmail());
			
			System.out.println(member);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			
		}
		return result;
	}

	public int updateMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update member set user_pwd = ?, user_no = ?, gender = ?, phone = ?, address1 = ?, address2 = ?, address3 = ?, email = ? where user_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, member.getUserPwd());
			pstmt.setString(2, member.getUserNo());
			pstmt.setString(3, member.getGender());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getAddress1());
			pstmt.setString(6, member.getAddress2());
			pstmt.setString(7, member.getAddress3());
			pstmt.setString(8, member.getEmail());
			pstmt.setString(9, member.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			
		}
		return result;
	}
	
	public int updateLoginOk(Connection conn, String userid, String loginok) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update member set login_ok = ? where user_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginok);
			pstmt.setString(2, userid);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int deleteMember(Connection conn, String userid, String userpwd) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from member where user_id = ? and user_pwd = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			pstmt.setString(2, userpwd);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			
		}
		return result;
	}

	public int selectCheckId(Connection conn, String userid) {
		int idcount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(user_id) from member where user_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);			
			pstmt.setString(1, userid);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				idcount = rset.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
			
		}
		return idcount;
	}

	public Member memberSearchId(Connection conn, String username, String userno) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select user_id, user_name from member where user_name = ? and user_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, userno);
						
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				member = new Member();
				
				member.setUserId(rset.getString("user_id"));
				member.setUserName(rset.getString("user_name"));
								
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		return member;
	
	}

	public int memberSearchPwd(Connection conn, String userid, String username, String userno) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String query = "select count(*) from member where user_id = ? and user_name = ? and user_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			pstmt.setString(2, username);
			pstmt.setString(3, userno);
								
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				result = 1;	
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		
		return result;
	}

	public int updatePwd(Connection conn, String userid, String getTempPassword) {
		//임시비밀번호 저장 
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update member set user_pwd = ? where user_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, getTempPassword);
			pstmt.setString(2, userid);
			
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			
		}
		return result;
	}


}
