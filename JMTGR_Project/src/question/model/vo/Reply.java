package question.model.vo;

import java.sql.Date;

public class Reply implements java.io.Serializable{
	
	public static final long serialVersionUID = 2357;
	
	private int qusNo;
	private String qusReplyContent;
	private java.sql.Date qusReplyDate ;
	
	public Reply() {}

	public Reply(int qusNo, String qusReplyContent, Date qusReplyDate) {
		super();
		this.qusNo = qusNo;
		this.qusReplyContent = qusReplyContent;
		this.qusReplyDate = qusReplyDate;
	}

	public void setQusNo(int qusNo) {
		this.qusNo = qusNo;
	}

	public void setQusReplyContent(String qusReplyContent) {
		this.qusReplyContent = qusReplyContent;
	}

	public void setQusReplyDate(java.sql.Date qusReplyDate) {
		this.qusReplyDate = qusReplyDate;
	}


	public int getQusNo() {
		return qusNo;
	}

	public String getQusReplyContent() {
		return qusReplyContent;
	}

	public java.sql.Date getQusReplyDate() {
		return qusReplyDate;
	}

	@Override
	public String toString() {
		return "Comment [qusNo=" + qusNo + ", qusReplyContent=" + qusReplyContent + ", qusReplyDate=" + qusReplyDate
				+ "]";
	}
	
	

}
