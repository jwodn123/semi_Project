package korea_recipe_board.model.vo;

import java.sql.Date;

public class Comment implements java.io.Serializable {
	
	public static final long serialVersionUID = 3113L;
	
	private int commNo;
	private int krBoardNo;
	private String commContent;
	private java.sql.Date commDate;
	private String userId;
	
	public Comment () {
		
	}

	
	public Comment(int commNo, int krBoardNo, String commContent, Date commDate, String userId) {
		super();
		this.commNo = commNo;
		this.krBoardNo = krBoardNo;
		this.commContent = commContent;
		this.commDate = commDate;
		this.userId = userId;
		
	}



	public int getCommNo() {
		return commNo;
	}

	public void setCommNo(int commNo) {
		this.commNo = commNo;
	}

	public int getKrBoardNo() {
		return krBoardNo;
	}

	public void setKrBoardNo(int krBoardNo) {
		this.krBoardNo = krBoardNo;
	}

	public String getCommContent() {
		return commContent;
	}

	public void setCommContent(String commContent) {
		this.commContent = commContent;
	}

	public java.sql.Date getCommDate() {
		return commDate;
	}

	public void setCommDate(java.sql.Date commDate) {
		this.commDate = commDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "Comment [commNo=" + commNo + ", krBoardNo=" + krBoardNo + ", commContent=" + commContent + ", commDate="
				+ commDate + ", userId=" + userId + "]";
	}

	
	
}
