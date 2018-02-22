package com.jony.platform.model.entity;

import java.io.Serializable;

public class User implements Serializable{
    private static final long serialVersionUID = 3148176768559230888L;
    public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String uid;
    private String userName;
    private int age;
    private int sex;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
}
