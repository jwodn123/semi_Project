package recipe_matelial_list.model.vo;

public class MaterialList {
	private int lmList;
	private int rBoardNo;
	private int maNo;
	private int gram;
	private String maName;
	public MaterialList() {
		super();
		
	}
	
	public MaterialList(int lmList, int rBoardNo, int maNo, int gram, String maName) {
		super();
		this.lmList = lmList;
		this.rBoardNo = rBoardNo;
		this.maNo = maNo;
		this.gram = gram;
		this.maName = maName;
	}
	
	public String getMaName() {
		return maName;
	}

	public void setMaName(String maName) {
		this.maName = maName;
	}

	public int getLmList() {
		return lmList;
	}
	public void setLmList(int lmList) {
		this.lmList = lmList;
	}
	public int getrBoardNo() {
		return rBoardNo;
	}
	public void setrBoardNo(int rBoardNo) {
		this.rBoardNo = rBoardNo;
	}
	public int getMaNo() {
		return maNo;
	}
	public void setMaNo(int maNo) {
		this.maNo = maNo;
	}
	public int getGram() {
		return gram;
	}
	public void setGram(int gram) {
		this.gram = gram;
	}

	@Override
	public String toString() {
		return "MaterialList [lmList=" + lmList + ", rBoardNo=" + rBoardNo + ", maNo=" + maNo + ", gram=" + gram
				+ ", maName=" + maName + "]";
	}
	
	
}

