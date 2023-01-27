package packages.model.vo;

import java.util.ArrayList;

public class PackagesResult {

	public PackagesResult() {
		super();
		
	}
	private String userId;
	private int krBoardNo;
	private int mrBoardNo;
	private String krBoardTitle;
	
	ArrayList<SubPackage> subpagelist;
	public PackagesResult(String userId, int people, int krBoardNo, int mrBoardNo, String maName, int gram,
			String krBoardTitle,ArrayList<SubPackage> subpagelist) {
		super();
		this.userId = userId;
		this.krBoardNo = krBoardNo;
		this.mrBoardNo = mrBoardNo;
		this.krBoardTitle = krBoardTitle;
		this.subpagelist = subpagelist;
	}

	
	public ArrayList<SubPackage> getSubpagelist() {
		return subpagelist;
	}


	public void setSubpagelist(ArrayList<SubPackage> subpagelist) {
		this.subpagelist = subpagelist;
	}


	public String getKrBoardTitle() {
		return krBoardTitle;
	}


	public void setKrBoardTitle(String krBoardTitle) {
		this.krBoardTitle = krBoardTitle;
	}





	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}






	public int getKrBoardNo() {
		return krBoardNo;
	}


	public void setKrBoardNo(int krBoardNo) {
		this.krBoardNo = krBoardNo;
	}


	public int getMrBoardNo() {
		return mrBoardNo;
	}


	public void setMrBoardNo(int mrBoardNo) {
		this.mrBoardNo = mrBoardNo;
	}


	@Override
	public String toString() {
		return "PackagesResult [userId=" + userId + ", krBoardNo=" + krBoardNo + ", mrBoardNo=" + mrBoardNo
				+ ", krBoardTitle=" + krBoardTitle + ", subpagelist=" + subpagelist + "]";
	}







	
	
	
	
	
}
