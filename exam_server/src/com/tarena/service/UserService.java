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
	 * µÇÂ¼
	 * @param user
	 * @return
	 */
	public User login(User user);
	
	/**
	 * ×¢²á
	 * @param user
	 * @return
	 */
	public User regist(User user);
	
	/**
	 * ²éÕÒÊÔ¾í
	 * @param uid
	 * @return
	 */
	public UserhandPaper findScore(String uid);
}
