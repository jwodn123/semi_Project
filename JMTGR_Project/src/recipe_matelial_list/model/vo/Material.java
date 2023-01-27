package recipe_matelial_list.model.vo;

public class Material {
	private int maNo;
	private String maName;
	public Material() {
		super();
		
	}
	public Material(int maNo, String maName) {
		super();
		this.maNo = maNo;
		this.maName = maName;
	}
	public int getMaNo() {
		return maNo;
	}
	public void setMaNo(int maNo) {
		this.maNo = maNo;
	}
	public String getMaName() {
		return maName;
	}
	public void setMaName(String maName) {
		this.maName = maName;
	}
	@Override
	public String toString() {
		return "Material [maNo=" + maNo + ", maName=" + maName + "]";
	}
	
	
}
