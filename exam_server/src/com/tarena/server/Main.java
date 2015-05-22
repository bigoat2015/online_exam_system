package com.tarena.server;

import java.io.IOException;

import com.tarena.dao.ExamDAO;
import com.tarena.dao.UserDAO;
import com.tarena.dao.impl.ExamDAOImpl;
import com.tarena.dao.impl.UserDAOImpl;
import com.tarena.service.ExamService;
import com.tarena.service.UserService;
import com.tarena.service.impl.ExamServiceImpl;
import com.tarena.service.impl.UserServiceImpl;

/**
 * 服务器端软件入口类
 * @author zhengyin
 *
 */
public class Main {
	public static void main(String[] args) throws IOException{
		UserDAO userDAO = 
			new UserDAOImpl();
		ExamDAO examDAO = 
			new ExamDAOImpl();
		
		UserService userService = 
			new UserServiceImpl(userDAO);
		ExamService examService = 
			new ExamServiceImpl(examDAO);
		
		ExamServerControl examServerControl = 
			new ExamServerControl();
		
		examServerControl.setUserService(userService);
		examServerControl.setExamService(examService);
		
		ExamServer examServer = 
			new ExamServer(examServerControl);
	}
}
