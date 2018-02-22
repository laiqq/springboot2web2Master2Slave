package com.jony.platform.model.res;

/**
 * 
 * @author laiquanqiang
 * @since 2017-5-10 18:10
 */
public class BaseResHeader {
	/**
	 * return result is success or fail
	 */
	private String resultCode;
	/**
	 * this is the entity of return data
	 */
	private String resultMessage;

	private String authenticateMessage; //登录认证返回信息
	private String authenticateCode;     //登录认证返回码
	private String authorityMessage;	    //权限认证信息
	private String authorityCode;       //权限返回码

	public String getAuthenticateCode() {
		return authenticateCode;
	}

	public void setAuthenticateCode(String authenticateCode) {
		this.authenticateCode = authenticateCode;
	}

	public String getAuthenticateMessage() {
		return authenticateMessage;
	}

	public void setAuthenticateMessage(String authenticateMessage) {
		this.authenticateMessage = authenticateMessage;
	}

	public String getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}

	public String getAuthorityMessage() {
		return authorityMessage;
	}

	public void setAuthorityMessage(String authorityMessage) {
		this.authorityMessage = authorityMessage;
	}

	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
}

