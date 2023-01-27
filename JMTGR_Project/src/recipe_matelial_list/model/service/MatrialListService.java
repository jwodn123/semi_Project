package recipe_matelial_list.model.service;

import static common.JDBCTemp.close;
import static common.JDBCTemp.commit;
import static common.JDBCTemp.getConnection;
import static common.JDBCTemp.rollback;

import java.sql.Connection;
import java.util.ArrayList;


import recipe_matelial_list.model.dao.MatrialListDao;
import recipe_matelial_list.model.vo.MaterialList;

public class MatrialListService {
	private MatrialListDao mdao = new MatrialListDao();
	public MatrialListService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int insertMatrialList(MaterialList mlist) {
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

	

	public ArrayList<MaterialList> ingreView(int krno) {
		Connection conn = getConnection();
		ArrayList<MaterialList> mvlist = mdao.ingreView(conn, krno);
		close(conn);
		return mvlist;
	}

	public int deleteList(int mano) {
		Connection conn = getConnection();
		int result = mdao.MrdeleteList(conn, mano);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	public int MrdeleteList(int mano) {
		Connection conn = getConnection();
		int result = mdao.MrdeleteList(conn, mano);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int mrInsertMatrialList(MaterialList mlist) {
		Connection conn = getConnection();
		int result = mdao.mrInsertMatrialList(conn, mlist);
		if (result > 0) {
			commit(conn);

		} else {
			rollback(conn);

		}
		close(conn);
		return result;
	}

	public ArrayList<MaterialList> MringreView(int mrno) {

		Connection conn = getConnection();
		ArrayList<MaterialList> mvlist = mdao.MringreView(conn, mrno);
		close(conn);
		return mvlist;
	
	}

}
