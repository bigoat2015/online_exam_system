package com.tarena.entity;

import java.io.Serializable;

/**
 *  �û�����
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
		String info = "�û��˻���"+userId+"\n"+"�Ծ���룺"+paperId+"\n"+"������"+score;
		return info;
				
	}
	
}
