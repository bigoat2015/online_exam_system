package com.tarena.ui;

import javax.swing.ImageIcon;

import com.tarena.ClientUtil.ClientTool;
import com.tarena.service.ExamService;
import com.tarena.service.UserService;
import com.tarena.service.impl.ExamServiceAgent;
import com.tarena.service.impl.UserServiceAgent;

/**
 * ����������
 * @author zhengyin
 *
 */
public class Main {

	public static void main(String[] args) {
		//��ɴ����������
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
		
		
		//����������
		ClientContext clientContext = 
			new ClientContext();
		
		//����ҵ���߼���
		UserService userService = 
			new UserServiceAgent();
		ExamService examService = 
			new ExamServiceAgent();
		
		
		//�ѿ�����ע�������
		loginFrame.setClientContext(clientContext);
		menuFrame.setClientContext(clientContext);
		examFrame.setClientContext(clientContext);
		registFram.setClientContext(clientContext);
		examrule.setClientContext(clientContext);
		scoreInfo.setClientContext(clientContext);
		
		//�ѽ���ע���������
		clientContext.setLoginFrame(loginFrame);
		clientContext.setMenuFrame(menuFrame);
		clientContext.setExamFrame(examFrame);
		clientContext.setRestFrame(registFram);
		clientContext.setExamRule(examrule);
		clientContext.setScoreFrame(scoreInfo);
		
		//��ҵ���߼���ע���������
		clientContext.setUserService(userService);
		clientContext.setExamService(examService);
	}
	
}
