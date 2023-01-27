package korea_recipe_board.model.vo;

public class KRContent implements java.io.Serializable{
	
	public static final long serialVersionUID = 3114L;
	
	private int krBoardNo;
	private int krCount;
	private String krBoardContent;
	private String krOriginFile;
	private String krRenameFile;
	
	public KRContent() {
		
	}
	
	public KRContent(int krBoardNo, int krCount, String krBoardContent, String krOriginFile, String krRenameFile) {
		super();
		this.krBoardNo = krBoardNo;
		this.krCount = krCount;
		this.krBoardContent = krBoardContent;
		this.krOriginFile = krOriginFile;
		this.krRenameFile = krRenameFile;
	}


	public int getKrBoardNo() {
		return krBoardNo;
	}


	public void setKrBoardNo(int krBoardNo) {
		this.krBoardNo = krBoardNo;
	}


	public int getKrCount() {
		return krCount;
	}


	public void setKrCount(int krCount) {
		this.krCount = krCount;
	}


	public String getKrBoardContent() {
		return krBoardContent;
	}


	public void setKrBoardContent(String krBoardContent) {
		this.krBoardContent = krBoardContent;
	}


	public String getKrOriginFile() {
		return krOriginFile;
	}


	public void setKrOriginFile(String krOriginFile) {
		this.krOriginFile = krOriginFile;
	}


	public String getKrRenameFile() {
		return krRenameFile;
	}


	public void setKrRenameFile(String krRenameFile) {
		this.krRenameFile = krRenameFile;
	}


	@Override
	public String toString() {
		return "KRContent [krBoardNo=" + krBoardNo + ", krCount=" + krCount + ", krBoradContent=" + krBoardContent
				+ ", krOriginFile=" + krOriginFile + ", krRenameFile=" + krRenameFile + "]";
	}
	
	
}
