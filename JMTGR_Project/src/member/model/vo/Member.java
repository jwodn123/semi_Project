package member.model.vo;

import java.sql.Date;


public class Member implements java.io.Serializable{
	private static final long serialVersionUID = 6666L;
	
	private String userId;
	private String userPwd;
	private String userName;
	private String userNo;
	private String phone;
	private String gender;
	private String address1;
	private String address2;
	private String address3;
	private String email;
	private String login_Ok;
	
	
	public Member() {}

	public Member(String userId, String userPwd, String userName, String userNo, String phone, String gender,
			String address1, String address2, String address3, String email, String login_Ok) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userNo = userNo;
		this.phone = phone;
		this.gender = gender;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.email = email;
		this.login_Ok = login_Ok;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin_Ok() {
		return login_Ok;
	}

	public void setLogin_Ok(String login_Ok) {
		this.login_Ok = login_Ok;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", userNo=" + userNo
				+ ", phone=" + phone + ", gender=" + gender + ", address1=" + address1 + ", address2=" + address2
				+ ", address3=" + address3 + ", email=" + email + ", login_Ok=" + login_Ok + "]";
	}
			
}
