package com.tarena.service;

import com.tarena.entity.User;
import com.tarena.entity.UserhandPaper;

/**
 * 
 * @author xiaoyao
 *
 */
public interface UserService {

	/**
	 * ��¼
	 * @param user
	 * @return
	 */
	public User login(User user);
	
	/**
	 * ע��
	 * @param user
	 * @return
	 */
	public User regist(User user);
	
	/**
	 * �����Ծ�
	 * @param uid
	 * @return
	 */
	public UserhandPaper findScore(String uid);
}
