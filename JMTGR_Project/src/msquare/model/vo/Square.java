package msquare.model.vo;

import java.sql.Date;

public class Square implements java.io.Serializable {
	private static final long serialVersionUID = 1111L;
	
	private int ms_board_no;
	private String ms_board_title;
	private String ms_board_content;
	private java.sql.Date ms_board_date;
	private String user_id;
	private String address;
	private int read_count;
	
	public Square() {}

	public Square(int ms_board_no, String ms_board_title, String ms_board_content, Date ms_board_date, String user_id,
			String address, int read_count) {
		super();
		this.ms_board_no = ms_board_no;
		this.ms_board_title = ms_board_title;
		this.ms_board_content = ms_board_content;
		this.ms_board_date = ms_board_date;
		this.user_id = user_id;
		this.address = address;
		this.read_count = read_count;
	}

	public int getMs_board_no() {
		return ms_board_no;
	}

	public void setMs_board_no(int ms_board_no) {
		this.ms_board_no = ms_board_no;
	}

	public String getMs_board_title() {
		return ms_board_title;
	}

	public void setMs_board_title(String ms_board_title) {
		this.ms_board_title = ms_board_title;
	}

	public String getMs_board_content() {
		return ms_board_content;
	}

	public void setMs_board_content(String ms_board_content) {
		this.ms_board_content = ms_board_content;
	}

	public java.sql.Date getMs_board_date() {
		return ms_board_date;
	}

	public void setMs_board_date(java.sql.Date ms_board_date) {
		this.ms_board_date = ms_board_date;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRead_count() {
		return read_count;
	}

	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Square [ms_board_no=" + ms_board_no + ", ms_board_title=" + ms_board_title + ", ms_board_content="
				+ ms_board_content + ", ms_board_date=" + ms_board_date + ", user_id=" + user_id + ", address="
				+ address + ", read_count=" + read_count + "]";
	}

	

	
}	
	

	