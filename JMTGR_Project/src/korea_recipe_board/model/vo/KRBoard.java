package korea_recipe_board.model.vo;

import java.sql.Date;

public class KRBoard implements java.io.Serializable{
	
	public static final long serialVersionUID = 3111L;
	
	public KRBoard() {
		
	}
	
	private int krBoardNo;
	private String krBoardTitle;
	private String thumbNailFile;
	private String renameFile;
	private java.sql.Date krBoardDate;
	private String cookName;
	private String adminId;
	private int readCount;
	
	
	
	
	public KRBoard(int krBoardNo, String krBoardTitle, String thumbNailFile, String renameFile, Date krBoardDate,
			String cookName, String adminId, int readCount) {
		super();
		this.krBoardNo = krBoardNo;
		this.krBoardTitle = krBoardTitle;
		this.thumbNailFile = thumbNailFile;
		this.renameFile = renameFile;
		this.krBoardDate = krBoardDate;
		this.cookName = cookName;
		this.adminId = adminId;
		this.readCount = readCount;
	}


	public String getRenameFile() {
		return renameFile;
	}



	public void setRenameFile(String renameFile) {
		this.renameFile = renameFile;
	}



	public String getThumbNailFile() {
		return thumbNailFile;
	}



	public void setThumbNailFile(String thumbNailFile) {
		this.thumbNailFile = thumbNailFile;
	}



	public int getKrBoardNo() {
		return krBoardNo;
	}

	public void setKrBoardNo(int krBoardNo) {
		this.krBoardNo = krBoardNo;
	}

	public String getKrBoardTitle() {
		return krBoardTitle;
	}

	public void setKrBoardTitle(String krBoardTitle) {
		this.krBoardTitle = krBoardTitle;
	}

	
	public java.sql.Date getKrBoardDate() {
		return krBoardDate;
	}

	public void setKrBoardDate(java.sql.Date krBoardDate) {
		this.krBoardDate = krBoardDate;
	}

	
	public String getCookName() {
		return cookName;
	}

	public void setCookName(String cookName) {
		this.cookName = cookName;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}


	@Override
	public String toString() {
		return "KRBoard [krBoardNo=" + krBoardNo + ", krBoardTitle=" + krBoardTitle + ", thumbNailFile=" + thumbNailFile
				+ ", renameFile=" + renameFile + ", krBoardDate=" + krBoardDate + ", cookName=" + cookName
				+ ", adminId=" + adminId + ", readCount=" + readCount + "]";
	}


	
	
}
