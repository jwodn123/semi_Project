package my_recipe_board.model.vo;

import java.sql.Date;

public class Comment implements java.io.Serializable {
	
	public static final long serialVersionUID = 3123L;
	
	private int commNo;
	private int mrBoardNo;
	private String commContent;
	private java.sql.Date commDate;
	private String userId;

	
	public Comment () {
		
	}


	public Comment(int commNo, int mrBoardNo, String commContent, Date commDate, String userId) {
		super();
		this.commNo = commNo;
		this.mrBoardNo = mrBoardNo;
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


	public int getMrBoardNo() {
		return mrBoardNo;
	}


	public void setMrBoardNo(int mrBoardNo) {
		this.mrBoardNo = mrBoardNo;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Comment [commNo=" + commNo + ", mrBoardNo=" + mrBoardNo + ", commContent=" + commContent + ", commDate="
				+ commDate + ", userId=" + userId + "]";
	}


	
	
}
