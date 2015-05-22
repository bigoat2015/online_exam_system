package com.tarena.entity;

import java.io.Serializable;

/**
 * 用户信息。
 * @author xiaoyao
 *
 */
public class User implements Serializable{

	private static final long serialVersionUID = 446639493160444405L;

	private Integer id;
	
	private String uid;
	
	private String name;
	
	private String password;
	
	private String phone;
	
	private String email;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString() {
		
		return "用户编号:"+getUid()+"  用户姓名:"+getName()+"  ";
	}
	
}
