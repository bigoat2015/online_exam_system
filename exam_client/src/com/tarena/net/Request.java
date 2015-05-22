package com.tarena.net;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 向服务端请求。
 * @author xiaoyao
 *
 */
public class Request implements Serializable{

	private static final long serialVersionUID = -318436566779881557L;

	public static final int LOGIN_REQ = 1;
	
	public static final int REGIST_REQ = 2;
	
	public static final int LOAD_PAPER_REQ = 3;
	
	public static final int WRITE_USER_PAPER_REQ = 4;
	
	public static final int FINT_USER_PAPER_REQ = 5;
	
	private int reqState;
	
	public void setReqState(int reqState) {
		this.reqState = reqState;
	}
	
	public int getReqState() {
		return reqState;
	}
	
	private Map<String,Object> data = 
		new HashMap<String,Object>();
	
	public void addData(String key,Object value){
		data.put(key, value);
	}
	
	
}
