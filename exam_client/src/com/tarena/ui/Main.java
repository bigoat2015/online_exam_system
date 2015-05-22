package com.tarena.ui;

import javax.swing.ImageIcon;

import com.tarena.ClientUtil.ClientTool;
import com.tarena.service.ExamService;
import com.tarena.service.UserService;
import com.tarena.service.impl.ExamServiceAgent;
import com.tarena.service.impl.UserServiceAgent;

/**
 * 软件的入口类
 * @author zhengyin
 *
 */
public class Main {

	public static void main(String[] args) {
		//完成创建界面对象
		LoginFrame loginFrame = 
			new LoginFrame(300,200);
		ClientTool.getImgPane(loginFrame, new ImageIcon("src/login.png"));
		
		MenuFrame menuFrame = 
			new MenuFrame(560,400);
		ExamFrame examFrame = 
			new ExamFrame(600, 600);
		RegistFrame registFram =
			new RegistFrame(300, 400);
		RuleFrame examrule = 
				new RuleFrame(500, 350);
		ScoreFrame scoreInfo = 
				new ScoreFrame(500, 350);
		
		
		//创建控制器
		ClientContext clientContext = 
			new ClientContext();
		
		//创建业务逻辑层
		UserService userService = 
			new UserServiceAgent();
		ExamService examService = 
			new ExamServiceAgent();
		
		
		//把控制器注入给界面
		loginFrame.setClientContext(clientContext);
		menuFrame.setClientContext(clientContext);
		examFrame.setClientContext(clientContext);
		registFram.setClientContext(clientContext);
		examrule.setClientContext(clientContext);
		scoreInfo.setClientContext(clientContext);
		
		//把界面注入给控制器
		clientContext.setLoginFrame(loginFrame);
		clientContext.setMenuFrame(menuFrame);
		clientContext.setExamFrame(examFrame);
		clientContext.setRestFrame(registFram);
		clientContext.setExamRule(examrule);
		clientContext.setScoreFrame(scoreInfo);
		
		//把业务逻辑层注入给控制器
		clientContext.setUserService(userService);
		clientContext.setExamService(examService);
	}
	
}
