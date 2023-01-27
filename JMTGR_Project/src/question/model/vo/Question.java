package question.model.vo;

import java.sql.Date;

public class Question implements java.io.Serializable {
	private static final long SerialVisionUID = 2111L;

	private int qusNo;
	private String qusTitle;
	private String qusContent;
	private String qusPwd;
	private String userId;
	private java.sql.Date qusBoardDate;
	private String qusOriginalFileName;
	private String qusRenameFileName;
	private int qusReadCount; // 읽은 조회수

	public Question() {
	}

	public Question(int qusNo, String qusTitle, String qusContent) {
		super();
		this.qusNo = qusNo;
		this.qusTitle = qusTitle;
		this.qusContent = qusContent;
	}

	public Question(int qusNo, String qusTitle, String qusContent, String qusOriginalFileName,
			String qusRenameFileName) {
		super();
		this.qusNo = qusNo;
		this.qusTitle = qusTitle;
		this.qusContent = qusContent;
		this.qusOriginalFileName = qusOriginalFileName;
		this.qusRenameFileName = qusRenameFileName;
	}

	public Question(int qusNo, String qusTitle, String qusContent, String qusPwd, String userId, Date qusBoardDate,
			String qusOriginalFileName, String qusRenameFileName, int qusReadCount) {
		super();
		this.qusNo = qusNo;
		this.qusTitle = qusTitle;
		this.qusContent = qusContent;
		this.qusPwd = qusPwd;
		this.userId = userId;
		this.qusBoardDate = qusBoardDate;
		this.qusOriginalFileName = qusOriginalFileName;
		this.qusRenameFileName = qusRenameFileName;
		this.qusReadCount = qusReadCount;
	}

	public void setQusNo(int qusNo) {
		this.qusNo = qusNo;
	}

	public void setQusTitle(String qusTitle) {
		this.qusTitle = qusTitle;
	}

	public void setQusContent(String qusContent) {
		this.qusContent = qusContent;
	}

	public void setQusPwd(String qusPwd) {
		this.qusPwd = qusPwd;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setQusBoardDate(java.sql.Date qusBoardDate) {
		this.qusBoardDate = qusBoardDate;
	}

	public void setQusOriginalFileName(String qusOriginalFileName) {
		this.qusOriginalFileName = qusOriginalFileName;
	}

	public void setQusRenameFileName(String qusRenameFileName) {
		this.qusRenameFileName = qusRenameFileName;
	}

	public void setQusReadCount(int qusReadCount) {
		this.qusReadCount = qusReadCount;
	}

	public static long getSerialvisionuid() {
		return SerialVisionUID;
	}

	public int getQusNo() {
		return qusNo;
	}

	public String getQusTitle() {
		return qusTitle;
	}

	public String getQusContent() {
		return qusContent;
	}

	public String getQusPwd() {
		return qusPwd;
	}

	public String getUserId() {
		return userId;
	}

	public java.sql.Date getQusBoardDate() {
		return qusBoardDate;
	}

	public String getQusOriginalFileName() {
		return qusOriginalFileName;
	}

	public String getQusRenameFileName() {
		return qusRenameFileName;
	}

	public int getQusReadCount() {
		return qusReadCount;
	}

	@Override
	public String toString() {
		return "Question [qusNo=" + qusNo + ", qusTitle=" + qusTitle + ", qusContent=" + qusContent + ", qusPwd="
				+ qusPwd + ", userId=" + userId + ", qusBoardDate=" + qusBoardDate + ", qusOriginalFileName="
				+ qusOriginalFileName + ", qusRenameFileName=" + qusRenameFileName + ", qusReadCount=" + qusReadCount
				+ "]";
	}

}