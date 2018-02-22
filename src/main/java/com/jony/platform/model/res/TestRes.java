package com.jony.platform.model.res;

import java.io.Serializable;
import java.util.List;
import com.jony.platform.model.entity.User;




public class TestRes implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<User> list;
	private int successSize;

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	public int getSuccessSize() {
		return successSize;
	}

	public void setSuccessSize(int successSize) {
		this.successSize = successSize;
	}

}
