package com.tarena.net;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 接受服务端请求。
 * @author xiaoyao
 *
 */
public class Response implements Serializable{

	private static final long serialVersionUID = -3215300500273426553L;

	public static final int LOGIN_SUCCESS_RES = 1;
	
	public static final int LOGIN_FAIL_RES = 2;
	
	public static final int LOAD_PAPER_SUCCESS_RES = 3;
	
	public static final int LOAD_LOGIN_FAIL_RES = 4;
	
	public static final int REGIST_SUCCESS_RES = 5;
	
	public static final int REGIST_FALL_RES = 6;
	
	public static final int WRITE_USER_PAPER_SUCCESS_RES = 7;
	
	public static final int WRITE_USER_PAPER_FALL_RES = 8;
	
	public static final int FINT_USER_PAPER_FALL_RES = 9;
	
	public static final int FINT_USER_PAPER_SUCCESS_RES = 10;
	
	private int resState;
	
	private Map<String,Object> data = new HashMap<String,Object>();
	
	public Map<String,Object> f(){
		return data;
		
	}
	
	public int getResState() {
		return resState;
	}

	public void setResState(int resState) {
		this.resState = resState;
	}

	public void addData(String key,Object value){
		data.put(key, value);
	}
	
	public Object getData(String key){
		return data.get(key);
	}
	
}
