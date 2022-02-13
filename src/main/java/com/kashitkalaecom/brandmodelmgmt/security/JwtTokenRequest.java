package com.kashitkalaecom.brandmodelmgmt.security;

public class JwtTokenRequest {

	private String userName;
	//private String ssn;
	private String email;
	private String password;

	/*
	 * public String getSsn() { return ssn; } public void setSsn(String ssn) {
	 * this.ssn = ssn; }
	 */
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
