package declaration.model.vo;

import java.sql.Date;

public class Declaration implements java.io.Serializable {
	private static final long SerialVisionUID = 2222L;

	private int deNo;
	private String deTitle;
	private String deContent;
	private String dePwd;
	private String userId;
	private java.sql.Date deBoardDate;
	private String deOriginalFileName;
	private String deRenameFileName;

	public Declaration() {
	}

	public Declaration(int deNo, String deTitle, String deContent, String dePwd, String userId, Date deBoardDate,
			String deOriginalFileName, String deRenameFileName) {
		super();
		this.deNo = deNo;
		this.deTitle = deTitle;
		this.deContent = deContent;
		this.dePwd = dePwd;
		this.userId = userId;
		this.deBoardDate = deBoardDate;
		this.deOriginalFileName = deOriginalFileName;
		this.deRenameFileName = deRenameFileName;
	}

	public void setDeNo(int deNo) {
		this.deNo = deNo;
	}

	public void setDeTitle(String deTitle) {
		this.deTitle = deTitle;
	}

	public void setDeContent(String deContent) {
		this.deContent = deContent;
	}

	public void setDePwd(String dePwd) {
		this.dePwd = dePwd;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setDeBoardDate(java.sql.Date deBoardDate) {
		this.deBoardDate = deBoardDate;
	}

	public void setDeOriginalFileName(String deOriginalFileName) {
		this.deOriginalFileName = deOriginalFileName;
	}

	public void setDeRenameFileName(String deRenameFileName) {
		this.deRenameFileName = deRenameFileName;
	}

	public static long getSerialvisionuid() {
		return SerialVisionUID;
	}

	public int getDeNo() {
		return deNo;
	}

	public String getDeTitle() {
		return deTitle;
	}

	public String getDeContent() {
		return deContent;
	}

	public String getDePwd() {
		return dePwd;
	}

	public String getUserId() {
		return userId;
	}

	public java.sql.Date getDeBoardDate() {
		return deBoardDate;
	}

	public String getDeOriginalFileName() {
		return deOriginalFileName;
	}

	public String getDeRenameFileName() {
		return deRenameFileName;
	}

	@Override
	public String toString() {
		return "Declaration [deNo=" + deNo + ", deTitle=" + deTitle + ", deContent=" + deContent + ", dePwd=" + dePwd
				+ ", userId=" + userId + ", deBoardDate=" + deBoardDate + ", deOriginalFileName=" + deOriginalFileName
				+ ", deRenameFileName=" + deRenameFileName + "]";
	}

}
