package com.jony.platform.utils;


/**
 * @author qiulong.zhang
 * @date 2017年4月15日
 * @time 上午10:37:51
 * @desc
 * 
 */
public class HttpClientResult {

	private String code;
	private String msg;
	private String val;
	private Exception e;
	
	
	public HttpClientResult(String code, String msg, String val) {
		super();
		this.code = code;
		this.msg = msg;
		this.val = val;
	}
	
	public HttpClientResult(String code, String msg, String val, Exception e) {
		super();
		this.code = code;
		this.msg = msg;
		this.val = val;
		this.e = e;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public Exception getE() {
		return e;
	}
	public void setE(Exception e) {
		this.e = e;
	}

	@Override
	public String toString() {
		return "HttpClientResult [code=" + code + ", msg=" + msg + ", val="
				+ val + "]";
	}
	
}
