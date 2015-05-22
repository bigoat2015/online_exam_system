package com.tarena.entity;

import java.io.Serializable;

/**
 *  用户交卷。
 * @author xiaoyao
 *
 */
public class UserhandPaper implements Serializable{

	private static final long serialVersionUID = -6257683032938569103L;
	private int id;
	private float score;
	private String userId;
	private int paperId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getPaperId() {
		return paperId;
	}
	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}

	public String toString() {
		String info = "用户账户："+userId+"\n"+"试卷编码："+paperId+"\n"+"分数："+score;
		return info;
				
	}
	
}
