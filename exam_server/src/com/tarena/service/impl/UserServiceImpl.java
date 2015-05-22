package com.tarena.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.tarena.dao.UserDAO;
import com.tarena.entity.User;
import com.tarena.entity.UserhandPaper;
import com.tarena.serverUtil.ConnectionUtil;
import com.tarena.service.UserService;

public class UserServiceImpl implements UserService{

	private UserDAO userDAO;
	
	public UserServiceImpl(UserDAO userDAO){
		this.userDAO = userDAO;
	}
	
	public User login(User user) {
		User u = null;
		String uid = user.getUid();
		String password = user.getPassword();
	    Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			u = userDAO.loginUser(uid,password,conn);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public User regist(User user) {
		
		User u = null;
	    Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			
			u = userDAO.findUser(user.getUid(), conn);
			if(u != null){
				u = null;
				return u;
			}
			
			u = userDAO.registUser(user.getUid(), 
					user.getName(), 
					user.getPassword(),
					user.getPhone(),
					user.getEmail(),
					conn);
		
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}


	public UserhandPaper findScore(String uid) {
		UserhandPaper up = null;
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			up = userDAO.fintScore(uid, conn);
			
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return up;
	}

}
