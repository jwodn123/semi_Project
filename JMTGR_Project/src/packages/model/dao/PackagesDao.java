package packages.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import packages.model.vo.Packages;


public class PackagesDao {

	public PackagesDao() {
		super();
		
	}



	public ArrayList<Packages> selectList(Connection conn, String userid) {

		ArrayList<Packages> list = new ArrayList<Packages>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select user_id,kr_board_no,ma_name, gram,people, mr_board_no " + 
				"from package " + 
				"join (select * from krecipe_material_list join material using (ma_no)) using (kr_board_no) " + 
				"where user_id = ? order by Kr_board_no";
		
		//packages로 변경해야 함 !!!!!!
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userid);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				Packages packages = new Packages();
				
				list.add(packages);
				packages.setUserId(userid);
				packages.setKrBoardNo(rset.getInt("kr_board_no"));
				packages.setPeople(rset.getInt("people"));
				packages.setMrBoardNo(rset.getInt("mr_board_no"));
				packages.setMaName(rset.getString("ma_name"));
				packages.setGram(rset.getInt("gram"));
	
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	
	}



	public int MrdeleteList(Connection conn, String userid, int mrboardno) {

		int result = 0;
		PreparedStatement pstmt = null;
		String query = null;

			query = "delete from package where user_id = ? and mr_board_no = ? ";	
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			pstmt.setInt(2, mrboardno);	
			
			result = pstmt.executeUpdate();
			
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	
	}

	public int KrdeleteList(Connection conn, String userid, int krboardno) {

		int result = 0;
		PreparedStatement pstmt = null;
		String query = null;
		
		query = "delete from package where user_id = ? and kr_board_no = ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			pstmt.setInt(2, krboardno);
			result = pstmt.executeUpdate();
			
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	
	}


	public int packagesInsert(Connection conn, Packages packages) {


		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into package values (?,?,?,null)";
		try {
			pstmt = conn.prepareStatement(query);
			
			
			pstmt.setString(1, packages.getUserId());
			pstmt.setInt(2, packages.getPeople());
			pstmt.setInt(3, packages.getKrBoardNo());
			
			
			

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	
	
	}



	public int MrpackagesInsert(Connection conn, Packages packages) {



		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into package values (?,?,null,?)";
		try {
			pstmt = conn.prepareStatement(query);
			
			
			pstmt.setString(1, packages.getUserId());
			pstmt.setInt(2, packages.getPeople());
			pstmt.setInt(3, packages.getMrBoardNo());
			
			
			

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	
	
	
	}



	public ArrayList<Packages> selectMrList(Connection conn, String userid) {

		ArrayList<Packages> list = new ArrayList<Packages>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select user_id,kr_board_no,ma_name, gram,people, mr_board_no " + 
				"from package " + 
				"join (select * from mrecipe_material_list join material using (ma_no)) using (mr_board_no) " + 
				"where user_id = ? order by Mr_board_no";
		
		//packages로 변경해야 함 !!!!!!
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userid);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				Packages packages = new Packages();
				
				list.add(packages);
				packages.setUserId(userid);
				packages.setKrBoardNo(rset.getInt("kr_board_no"));
				packages.setPeople(rset.getInt("people"));
				packages.setMrBoardNo(rset.getInt("mr_board_no"));
				packages.setMaName(rset.getString("ma_name"));
				packages.setGram(rset.getInt("gram"));
	
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
