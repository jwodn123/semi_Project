package my_recipe_board.model.vo;

import static common.JDBCTemp.close;
import static common.JDBCTemp.getConnection;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import korea_recipe_board.model.vo.Comment;

public class MyRecipe implements java.io.Serializable{
	private static final long serialVersionUID = 5555L;

	private int mrBoardNo;
	private String mrBoardTitle;
	private String mrBoardContent;
	private java.sql.Date mrBoardDate;
	private String mrboardoriginFile;
	private String mrboardrenameFile;
	private String userId;
	private int readCount;
	
	public MyRecipe() {}

	public MyRecipe(int mrBoardNo, String mrBoardTitle, String mrBoardContent, Date mrBoardDate,
			String mrboardoriginFile, String mrboardrenameFile, String userId, int readCount) {
		super();
		this.mrBoardNo = mrBoardNo;
		this.mrBoardTitle = mrBoardTitle;
		this.mrBoardContent = mrBoardContent;
		this.mrBoardDate = mrBoardDate;
		this.mrboardoriginFile = mrboardoriginFile;
		this.mrboardrenameFile = mrboardrenameFile;
		this.userId = userId;
		this.readCount = readCount;
	}

	public int getMrBoardNo() {
		return mrBoardNo;
	}

	public void setMrBoardNo(int mrBoardNo) {
		this.mrBoardNo = mrBoardNo;
	}

	public String getMrBoardTitle() {
		return mrBoardTitle;
	}

	public void setMrBoardTitle(String mrBoardTitle) {
		this.mrBoardTitle = mrBoardTitle;
	}

	public String getMrBoardContent() {
		return mrBoardContent;
	}

	public void setMrBoardContent(String mrBoardContent) {
		this.mrBoardContent = mrBoardContent;
	}

	public java.sql.Date getMrBoardDate() {
		return mrBoardDate;
	}

	public void setMrBoardDate(java.sql.Date mrBoardDate) {
		this.mrBoardDate = mrBoardDate;
	}

	public String getMrboardoriginFile() {
		return mrboardoriginFile;
	}

	public void setMrboardoriginFile(String mrboardoriginFile) {
		this.mrboardoriginFile = mrboardoriginFile;
	}

	public String getMrboardrenameFile() {
		return mrboardrenameFile;
	}

	public void setMrboardrenameFile(String mrboardrenameFile) {
		this.mrboardrenameFile = mrboardrenameFile;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MyRecipe [mrBoardNo=" + mrBoardNo + ", mrBoardTitle=" + mrBoardTitle + ", mrBoardContent="
				+ mrBoardContent + ", mrBoardDate=" + mrBoardDate + ", mrboardoriginFile=" + mrboardoriginFile
				+ ", mrboardrenameFile=" + mrboardrenameFile + ", userId=" + userId + ", readCount=" + readCount + "]";
	}


	
	
}
