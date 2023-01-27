package packages.model.service;

import static common.JDBCTemp.close;
import static common.JDBCTemp.commit;
import static common.JDBCTemp.getConnection;
import static common.JDBCTemp.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import packages.model.dao.PackagesDao;
import packages.model.vo.Packages;
import packages.model.vo.PackagesResult;
import packages.model.vo.SubPackage;


public class PackagesService {
	
	private PackagesDao pdao = new PackagesDao();
	
	public PackagesService() {
		super();
		
	}



	public ArrayList<PackagesResult> selectList(String userid) {
		Connection conn = getConnection();
		ArrayList<Packages> packagelist = pdao.selectList(conn, userid);
		close(conn);
		
		//레시피별로 나누어 담을 ArrayList선언
		ArrayList<PackagesResult> resultlist = new ArrayList<PackagesResult>();
		ArrayList<SubPackage> sublist = null;
		PackagesResult mainpackage = null;//db 조회한 결과에서 일반적인 내용을 추출해서 담을  VO
		SubPackage sub = null;//재료를 담을  VO
		
		
		HashSet<Integer> colset = new HashSet<Integer>();
		for(int i=0;i<packagelist.size();i++) {
			
				colset.add(packagelist.get(i).getKrBoardNo());
		
				
			
		}
		
		//packagelist를 ArrayList<PackagesResult>의 타입으로 바꾸어 저장한 후 리턴
		Iterator<Integer> it = colset.iterator();
		int preno = 0;
		int no_count = 0;
		while(it.hasNext()) {
			int no = it.next();//레시피번호
			sublist = new ArrayList<SubPackage>();
			mainpackage = new PackagesResult();
			for(int i=0;i<packagelist.size();i++) {
				Packages data = packagelist.get(i);
				
				if(data.getKrBoardNo()==no) {
					
						mainpackage.setKrBoardNo(data.getKrBoardNo());
					
						
				
					
					
					
					
					mainpackage.setUserId(data.getUserId());
					sub = new SubPackage(data.getPeople(), data.getMaName(), data.getGram());
					sublist.add(sub);
					mainpackage.setSubpagelist(sublist);
				}else {
					continue;
				}
				
			}
			resultlist.add(mainpackage);
		}
		return resultlist;
	}



	public int Mrdeletelist(String userid, int mrboardno) {

		Connection conn = getConnection();
		int result = pdao.MrdeleteList(conn, userid, mrboardno);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	
	}
	
	public int Krdeletelist(String userid, int krboardno) {

		Connection conn = getConnection();
		int result = pdao.KrdeleteList(conn, userid, krboardno);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	
	}



	public int packagesInsert(Packages packages) {
		Connection conn = getConnection();
		int result = pdao.packagesInsert(conn, packages);
		if (result > 0) {
			commit(conn);

		} else {
			rollback(conn);

		}
		close(conn);
		return result;
	}



	public int MrpackagesInsert(Packages packages) {
		Connection conn = getConnection();
		int result = pdao.MrpackagesInsert(conn, packages);
		if (result > 0) {
			commit(conn);

		} else {
			rollback(conn);

		}
		close(conn);
		return result;
	}



	public ArrayList<PackagesResult> selectMrList(String userid) {
		Connection conn = getConnection();
		ArrayList<Packages> packagelist = pdao.selectMrList(conn, userid);
		close(conn);
		
		//레시피별로 나누어 담을 ArrayList선언
		ArrayList<PackagesResult> resultlist = new ArrayList<PackagesResult>();
		ArrayList<SubPackage> sublist = null;
		PackagesResult mainpackage = null;//db 조회한 결과에서 일반적인 내용을 추출해서 담을  VO
		SubPackage sub = null;//재료를 담을  VO
		
		
		HashSet<Integer> colset = new HashSet<Integer>();
		for(int i=0;i<packagelist.size();i++) {
			
				
		
				colset.add(packagelist.get(i).getMrBoardNo());
			
		}
		
		//packagelist를 ArrayList<PackagesResult>의 타입으로 바꾸어 저장한 후 리턴
		Iterator<Integer> it = colset.iterator();
		int preno = 0;
		int no_count = 0;
		while(it.hasNext()) {
			int no = it.next();//레시피번호
			sublist = new ArrayList<SubPackage>();
			mainpackage = new PackagesResult();
			for(int i=0;i<packagelist.size();i++) {
				Packages data = packagelist.get(i);
				
				if(data.getMrBoardNo()==no) {
					
						
					
						mainpackage.setMrBoardNo(data.getMrBoardNo());
		
					
					mainpackage.setUserId(data.getUserId());
					sub = new SubPackage(data.getPeople(), data.getMaName(), data.getGram());
					sublist.add(sub);
					mainpackage.setSubpagelist(sublist);
				}else {
					continue;
				}
				
			}
			resultlist.add(mainpackage);
		}
		
		return resultlist;
	}





}
