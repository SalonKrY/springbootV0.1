package com.example.demo.bean;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class User implements Serializable{
 
	private static final long serialVersionUID = 7201208838451952980L;
	
	private String id;
	private String userName;
	private String password;
	@JSONField(format="yyyy-MM-dd")
	private Date birthday;
	
	public User() {
		
	}
	
	public User(String id,String userName,String password,Date birthday) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.birthday = birthday;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
