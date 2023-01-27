package msquare.model.vo;

import java.sql.Date;

public class SquareComment {
	private int comm_no;
	private int ms_board_no;
	private String comm_content;
	private java.sql.Date comm_date;
	private String user_id;
	
	public SquareComment() {}

	public SquareComment(int comm_no, int ms_board_no, String comm_content, Date comm_date, String user_id) {
		super();
		this.comm_no = comm_no;
		this.ms_board_no = ms_board_no;
		this.comm_content = comm_content;
		this.comm_date = comm_date;
		this.user_id = user_id;
	}

	public int getComm_no() {
		return comm_no;
	}

	public void setComm_no(int comm_no) {
		this.comm_no = comm_no;
	}

	public int getMs_board_no() {
		return ms_board_no;
	}

	public void setMs_board_no(int ms_board_no) {
		this.ms_board_no = ms_board_no;
	}

	public String getComm_content() {
		return comm_content;
	}

	public void setComm_content(String comm_content) {
		this.comm_content = comm_content;
	}

	public java.sql.Date getComm_date() {
		return comm_date;
	}

	public void setComm_date(java.sql.Date comm_date) {
		this.comm_date = comm_date;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "SquareComment [comm_no=" + comm_no + ", ms_board_no=" + ms_board_no + ", comm_content=" + comm_content
				+ ", comm_date=" + comm_date + ", user_id=" + user_id + "]";
	}
	
	
}
