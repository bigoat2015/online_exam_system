package com.tarena.exception;

/**
 * ��½�쳣��
 * @author xiaoyao
 *
 */
public class UserOrPasswordException extends Exception{

	public UserOrPasswordException(){}
	
	public UserOrPasswordException(String msg){
		super(msg);
	}
}
