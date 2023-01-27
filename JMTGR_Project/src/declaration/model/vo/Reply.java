package declaration.model.vo;

import java.sql.Date;

public class Reply implements java.io.Serializable{
	
	public static final long serialVersionUID = 2135;
	
	private int deNo;
	private String deReplyContent;
	private java.sql.Date deReplyDate ;
	
	public Reply() {}

	public Reply(int deNo, String deReplyContent, Date deReplyDate) {
		super();
		this.deNo = deNo;
		this.deReplyContent = deReplyContent;
		this.deReplyDate = deReplyDate;
	}

	public void setDeNo(int deNo) {
		this.deNo = deNo;
	}

	public void setDeReplyContent(String deReplyContent) {
		this.deReplyContent = deReplyContent;
	}

	public void setDeReplyDate(java.sql.Date deReplyDate) {
		this.deReplyDate = deReplyDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getDeNo() {
		return deNo;
	}

	public String getDeReplyContent() {
		return deReplyContent;
	}

	public java.sql.Date getDeReplyDate() {
		return deReplyDate;
	}

	@Override
	public String toString() {
		return "Reply [deNo=" + deNo + ", deReplyContent=" + deReplyContent + ", deReplyDate=" + deReplyDate + "]";
	}
}
