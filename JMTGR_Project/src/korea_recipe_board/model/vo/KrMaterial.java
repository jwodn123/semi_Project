package korea_recipe_board.model.vo;

public class KrMaterial implements java.io.Serializable{
	
	public static final long serialVersionUID = 3112L;
	
	public KrMaterial() {
		
	}
	
	private int maNo;
	private int krBoardNo;
	private String maName;
	private int Gram;

	public KrMaterial(int maNo, int krBoardNo, String maName, int gram) {
		super();
		this.maNo = maNo;
		this.krBoardNo = krBoardNo;
		this.maName = maName;
		Gram = gram;
	}

	public int getMaNo() {
		return maNo;
	}

	public void setMaNo(int maNo) {
		this.maNo = maNo;
	}

	public int getKrBoardNo() {
		return krBoardNo;
	}

	public void setKrBoardNo(int krBoardNo) {
		this.krBoardNo = krBoardNo;
	}

	public String getMaName() {
		return maName;
	}

	public void setMaName(String maName) {
		this.maName = maName;
	}

	public int getGram() {
		return Gram;
	}

	public void setGram(int gram) {
		Gram = gram;
	}

	@Override
	public String toString() {
		return "KrMaterial [maNo=" + maNo + ", krBoardNo=" + krBoardNo + ", maName=" + maName + ", Gram=" + Gram + "]";
	}
	
	
}
