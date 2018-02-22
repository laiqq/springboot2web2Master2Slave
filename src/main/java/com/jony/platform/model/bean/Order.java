package com.jony.platform.model.bean;

public class Order {
	private String orderNo;
	private String userId;
	private String print;
	private String msgId;
	
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPrint() {
		return print;
	}
	public void setPrint(String print) {
		this.print = print;
	}
	

}
