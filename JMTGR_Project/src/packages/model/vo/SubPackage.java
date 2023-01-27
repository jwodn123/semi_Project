package packages.model.vo;

public class SubPackage {
	private int people;
	private String maName;
	private int gram;
	public SubPackage() {
		
	}
	public SubPackage(int people, String maName, int gram) {
		super();
		this.people = people;
		this.maName = maName;
		this.gram = gram;
	}
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
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
	@Override
	public String toString() {
		return "SubPackage [people=" + people + ", maName=" + maName + ", gram=" + gram + "]";
	}
	
}
