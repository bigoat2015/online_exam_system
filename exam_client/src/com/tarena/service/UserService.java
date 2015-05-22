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
	 * 登陆：
	 * @param uId
	 * @param pwd
	 * @throws UserOrPasswordException
	 * @throws IOException
	 */
	public void login(String uId,String pwd)throws UserOrPasswordException,IOException;
	
	/**
	 * 还回登陆的用户。
	 * @return
	 */
	public User getLoginUser();
	
	/**
	 * 用户注册。
	 * @param uId
	 * Id
	 * @param name
	 * 姓名
	 * @param password
	 * 密码
	 * @param rePassword
	 * 密码
	 * @param phone
	 * 手机号码
	 * @param email
	 * 邮箱
	 * @throws RegistException
	 * 注册失败。
	 */
	public void regist(
			String uId,String name,
			String password,String rePassword,
			String phone,String email) throws RegistException;
	
	/**
	 * 用户 查找 试卷。
	 * @param userid
	 * @return
	 * @throws ExamException
	 */
	public UserhandPaper fintPapr(String userid) throws ExamException;
}
