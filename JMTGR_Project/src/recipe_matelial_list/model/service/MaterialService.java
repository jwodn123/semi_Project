package recipe_matelial_list.model.service;

import static common.JDBCTemp.close;
import static common.JDBCTemp.commit;
import static common.JDBCTemp.getConnection;
import static common.JDBCTemp.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import recipe_matelial_list.model.dao.MaterialDao;
import recipe_matelial_list.model.vo.Material;



public class MaterialService {
	private MaterialDao mdao = new MaterialDao();
	
	public MaterialService() {
		super();
		
	}

	public ArrayList<Material> selectList() {
		Connection conn = getConnection();
		ArrayList<Material> list = mdao.selectList(conn);
		close(conn);
		return list;
	}

	public ArrayList<Material> selectSearchMaName(String keyword) {

		Connection conn = getConnection();
		ArrayList<Material> list = mdao.selectSearchMaName(conn, keyword);
		close(conn);
		return list;
	
	}

	public Material selectSelectOne(int mano) {
		Connection conn = getConnection();
		Material list = mdao.selectOne(conn, mano);
		close(conn);
		return list;
	}

	public int deleteList(int mano) {

		Connection conn = getConnection();
		int result = mdao.deleteList(conn, mano);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
		}

	public int insertMatrial(Material mlist) {
		Connection conn = getConnection();
		int result = mdao.insertMaterialList(conn, mlist);
		if (result > 0) {
			commit(conn);

		} else {
			rollback(conn);

		}
		close(conn);
		return result;
	}
	
	

}
