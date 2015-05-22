package com.tarena.service;

import java.io.IOException;

import com.tarena.entity.User;
import com.tarena.entity.UserhandPaper;
import com.tarena.exception.ExamException;
import com.tarena.exception.RegistException;
import com.tarena.exception.UserOrPasswordException;

/**
 * 
 * @author xiaoyao
 *
 */
public interface UserService {
	
	/**
	 * ��½��
	 * @param uId
	 * @param pwd
	 * @throws UserOrPasswordException
	 * @throws IOException
	 */
	public void login(String uId,String pwd)throws UserOrPasswordException,IOException;
	
	/**
	 * ���ص�½���û���
	 * @return
	 */
	public User getLoginUser();
	
	/**
	 * �û�ע�ᡣ
	 * @param uId
	 * Id
	 * @param name
	 * ����
	 * @param password
	 * ����
	 * @param rePassword
	 * ����
	 * @param phone
	 * �ֻ�����
	 * @param email
	 * ����
	 * @throws RegistException
	 * ע��ʧ�ܡ�
	 */
	public void regist(
			String uId,String name,
			String password,String rePassword,
			String phone,String email) throws RegistException;
	
	/**
	 * �û� ���� �Ծ�
	 * @param userid
	 * @return
	 * @throws ExamException
	 */
	public UserhandPaper fintPapr(String userid) throws ExamException;
}
