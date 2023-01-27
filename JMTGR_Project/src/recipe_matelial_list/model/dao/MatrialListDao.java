package recipe_matelial_list.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import recipe_matelial_list.model.vo.MaterialList;

public class MatrialListDao {

	public MatrialListDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int insertMaterialList(Connection conn, MaterialList mlist) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into KRECIPE_MATERIAL_LIST values (?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			
			
			pstmt.setInt(1, mlist.getrBoardNo());
			pstmt.setInt(2, mlist.getMaNo());
			pstmt.setInt(3, mlist.getGram());
			
			

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	
	public int insertMrMaterialList(Connection conn, MaterialList mlist) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into MRECIPE_MATERIAL_LIST values (?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			
			
			pstmt.setInt(1, mlist.getrBoardNo());
			pstmt.setInt(2, mlist.getMaNo());
			pstmt.setInt(3, mlist.getGram());
			
			

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	

	public ArrayList<MaterialList> ingreView(Connection conn, int krno) {


		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MaterialList> mvlist = new ArrayList<MaterialList>();
		
		String query = "select * from KRECIPE_MATERIAL_LIST " + 
				"join material using(ma_no) " + 
				"where kr_board_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, krno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MaterialList list = new MaterialList();
				list.setMaNo(rset.getInt("ma_no"));
				list.setGram(rset.getInt("gram"));
				list.setMaName(rset.getString("ma_name"));
				
				
				
				mvlist.add(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return mvlist;
	
	
	}
	
	
	public ArrayList<MaterialList> MringreView(Connection conn, int mrno) {


		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MaterialList> mvlist = new ArrayList<MaterialList>();
		
		String query = "select * from MRECIPE_MATERIAL_LIST " + 
				"join material using(ma_no) " + 
				"where MR_BOARD_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mrno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MaterialList list = new MaterialList();
				list.setMaNo(rset.getInt("ma_no"));
				list.setGram(rset.getInt("gram"));
				list.setMaName(rset.getString("ma_name"));
				
				
				
				mvlist.add(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return mvlist;
	
	
	}
	

	public int deleteList(Connection conn, int mano) {

		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete KRECIPE_MATERIAL_LIST where MA_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mano);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	
	}
	public int MrdeleteList(Connection conn, int mano) {

		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete MRECIPE_MATERIAL_LIST where MA_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mano);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	
	}
	


	public int mrInsertMatrialList(Connection conn, MaterialList mlist) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into MRECIPE_MATERIAL_LIST values (?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			
			
			pstmt.setInt(1, mlist.getrBoardNo());
			pstmt.setInt(2, mlist.getMaNo());
			pstmt.setInt(3, mlist.getGram());
			
			

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	

}
