package packages.model.vo;

public class Packages {

	public Packages() {
		super();
		
	}
	private String userId;
	private int people;
	private int krBoardNo;
	private int mrBoardNo;
	private String maName;
	private int gram;
	
	
	


	public Packages(String userId, int people, int krBoardNo, int mrBoardNo, String maName, int gram) {
		super();
		this.userId = userId;
		this.people = people;
		this.krBoardNo = krBoardNo;
		this.mrBoardNo = mrBoardNo;
		this.maName = maName;
		this.gram = gram;
	}

	
	public String getMaName() {
		return maName;
	}


	public void setMaName(String maName) {
		this.maName = maName;
	}


	public int getGram() {
		return gram;
	}


	public void setGram(int gram) {
		this.gram = gram;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public int getPeople() {
		return people;
	}


	public void setPeople(int people) {
		this.people = people;
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
		return "Packages [userId=" + userId + ", people=" + people + ", krBoardNo=" + krBoardNo + ", mrBoardNo="
				+ mrBoardNo + ", maName=" + maName + ", gram=" + gram + "]";
	}


	
	
	
	
	
}
