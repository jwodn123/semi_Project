package recipe_matelial_list.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import recipe_matelial_list.model.vo.Material;




public class MaterialDao {

	public MaterialDao() {
		super();
		
	}

	public ArrayList<Material> selectList(Connection conn) {

		ArrayList<Material> list = new ArrayList<Material>();
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select * from material";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			while (rset.next()) {
				Material material = new Material();
				list.add(material);
				
				material.setMaNo(rset.getInt("ma_No"));
				material.setMaName(rset.getString("ma_Name"));
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return list;
	
	}

	public ArrayList<Material> selectSearchMaName(Connection conn, String keyword) {
		

		ArrayList<Material> list = new ArrayList<Material>();
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;

		String query = "select * from material where ma_name like ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Material material = new Material();
				list.add(material);

				material.setMaNo(rset.getInt("ma_No"));
				material.setMaName(rset.getString("ma_Name"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	
	
	}

	public Material selectOne(Connection conn, int maNo) {
		Material material = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from material where ma_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, maNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				material = new Material();
				material.setMaNo(rset.getInt("ma_No"));
				material.setMaName(rset.getString("ma_Name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);

		}

		return material;

	
	}

	public int deleteList(Connection conn, int mano) {


		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete MATERIAL where MA_NO = ?";

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

	public int insertMaterialList(Connection conn, Material mlist) {

		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into MATERIAL values (?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			
			
			pstmt.setInt(1, mlist.getMaNo());
			pstmt.setString(2, mlist.getMaName());
			
			

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	
	}
	
}
