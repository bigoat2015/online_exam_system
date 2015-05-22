package com.tarena.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.tarena.ClientUtil.ClientTool;
import com.tarena.entity.Paper;
import com.tarena.entity.Question;
import com.tarena.entity.User;
import com.tarena.entity.UserhandPaper;
import com.tarena.exception.ExamException;
import com.tarena.exception.RegistException;
import com.tarena.exception.UserOrPasswordException;
import com.tarena.service.ExamService;
import com.tarena.service.UserService;
import com.tarena.ui.ExamFrame.OptionJBox;

/**
 * ������ �Դ��� ������ʾ����
 * @author xiaoyao
 *
 */
public class ClientContext {

	private LoginFrame loginFrame;
	
	private MenuFrame menuFrame;
	
	private ExamFrame examFrame;
	
	private RegistFrame registFrame;
	
	private RuleFrame examRule;
	
	private ScoreFrame scoreInfo;
	
	private UserService userService;
	
	private ExamService examService;
	

	public void setExamFrame(ExamFrame examFrame) {
		this.examFrame = examFrame;
	}
	
	public void setMenuFrame(MenuFrame menuFrame) {
		this.menuFrame = menuFrame;
	}
	
	public void setLoginFrame(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}
	
	public void setRestFrame(RegistFrame registFram) {
		this.registFrame = registFram;
		
	}
	
	public void setScoreFrame(ScoreFrame scoreInfo) {
		this.scoreInfo = scoreInfo;
		
	}
	
	public void setExamService(ExamService examService) {
		this.examService = examService;
	}
	

	public void setExamRule(RuleFrame exaRule) {
		this.examRule = exaRule;
		
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * ��ȡ��������
	 */
	private Integer questIndex = 0;
	
	/**
	 * �����û�ѡ��Ĵ𰸡�
	 */
	private Map<Integer,List<Integer>> saveUserChooseAnswers = new HashMap<Integer,List<Integer>>();
	
	
	
	/**
	 * ��һ�� 
	 * @param userChooseAnswers
	 * �Ӵ����� ExamException �õ�ѡ��ť��״̬��
	 */
	public void prev(List<Integer> userChooseAnswers){
		try {
			
			// �����û���ǰѡ��Ĵ𰸡�
			saveUserChooseAnswers.put(questIndex,userChooseAnswers);
			
			// ������һ�� 
			--questIndex;
			Question question = examService.prev(questIndex);
			
			// ʹ�� ClientTool.formatQuestionShow() ���� ��ʽ�� ������ʾ��
			String showQuestion = ClientTool.formatQuestionShow(question);
			
			//��ʾ���⵽������ ������š�
			examFrame.showTitleProgress("��Ŀ:20��"+(questIndex+1)+"��");
			examFrame.showQuestion(showQuestion);
			
			// ��ȡ�û� ���� ѡ��Ĵ𰸡�
			readUserChooseAnswers(questIndex);
			
			examFrame.getNextBtn().setEnabled(true);
			
			if(questIndex == 0){
				examFrame.getPrevBtn().setEnabled(false);
			}
			
		} catch (ExamException e) {
			JOptionPane.showMessageDialog(examFrame, "��ȡ�Ծ�ʧ�ܣ�");
			examFrame.hideView();
			menuFrame.hideView();
		}
	}
	
	public void next(List<Integer> userChooseAnswers){
		
		try {
			
			saveUserChooseAnswers.put(questIndex, userChooseAnswers);
			++questIndex;
			
			Question question = examService.next(questIndex);
			String showQuestion = ClientTool.formatQuestionShow(question);
			examFrame.showQuestion(showQuestion);
			examFrame.showTitleProgress("��Ŀ:20��"+(questIndex+1)+"��");
			
			readUserChooseAnswers(questIndex);
	
			examFrame.getPrevBtn().setEnabled(true);
			
			if(questIndex == 19){
			
				examFrame.getNextBtn().setEnabled(false);
			}	
		
		} catch (ExamException e) {
			JOptionPane.showMessageDialog(examFrame, "��ȡ�Ծ�ʧ�ܣ�");
			examFrame.hideView();
			menuFrame.hideView();
		} 
	}

	/**
	 * ��ȡ�û�ѡ��Ĵ𰸡�
	 * @param index
	 */
	private void readUserChooseAnswers(Integer index){
		
		List<Integer> answers = saveUserChooseAnswers.get(index);
		List<OptionJBox> oJBoxs = examFrame.getOptions();
		
		for(int i=0;i<oJBoxs.size();i++){
			oJBoxs.get(i).setSelected(false);
		}

		for(int i=0; i<answers.size(); i++){
			oJBoxs.get(answers.get(i)-1).setSelected(true);
		}
	}

	
	private User user;
	
	/**
	 * ��� ���� ����  
	 * ��ʼ����
	 * ѡ��ť��
	 *
	 */
	public void start(){
		try {
			
			examFrame.showView();
			menuFrame.hideView();
			
			Paper paper = examService.loadPaper(1);
			user = userService.getLoginUser(); 
			
			List<OptionJBox> oJBoxs = examFrame.getOptions();
			for(int i=0;i<oJBoxs.size();i++){
				oJBoxs.get(i).setSelected(false);
			}
			
			examFrame.showExamInfo(
					"<html><font size=4 color=red >"+
					user.toString()+ paper.toString()+
					"</font></html>" );
			
			int temp = 0;
			while(temp<20){
				saveUserChooseAnswers.put(temp,new ArrayList<Integer>());
				temp++;
			}
			
			Question question = examService.next(questIndex);
			String showQuestion = ClientTool.formatQuestionShow(question);
			examFrame.showQuestion(showQuestion);
			examFrame.showTitleProgress("��Ŀ:20��"+(questIndex+1)+"��");
			
			// ���� ����ʱ�䣺
			new Thread(new TestTime(paper.getExam_time())).start();
				
			examFrame.getPrevBtn().setEnabled(false);
		
		} catch (ExamException e) {
			examFrame.showQuestion(e.getMessage());
		}
		
		//1,���ÿ��Ե�ҵ���߼������ĳһ���Ծ�
		//2,���¿��Խ����ϵĿ�����Ϣ
		//3,���Ծ�ĵ�һ����ʾ�ڿ��Խ�����
		//4,������һ�ⰴť������
	}
	
	// ʵ�ֵ�½
	public void login(){
		String userId = loginFrame.getUser();
		String password = loginFrame.getPassword();
		try {
			
			userService.login(userId,password);
			loginFrame.hideView();
			menuFrame.showView();
			menuFrame.showUserInfo("��ӭ  "+ userService.getLoginUser().getName()+" �ĵ���");
			
			UserhandPaper up = userService.fintPapr(userService.getLoginUser().getUid());
			if(up != null){
				menuFrame.getStartBtn().setEnabled(false);
				scoreInfo.showScoreInfo(up.toString());
				menuFrame.getScoreBtn().setEnabled(true);
				JOptionPane.showMessageDialog(menuFrame,(userService.getLoginUser().getName()+"�����ѿ��Թ���"));
			}
		
		} catch (UserOrPasswordException e) {
			loginFrame.showError(e.getMessage());
			
		} catch (IOException e) {
			loginFrame.showError("�����쳣");
		} catch (ExamException e) {
			JOptionPane.showMessageDialog(menuFrame,(userService.getLoginUser().getName()+"���� ��û�п��� ��"));
		}
	}

	/**
	 * ���� 
	 * ���� ��ǰ�û� ��ǰѡ����Ŀ�Ĵ�
	 * @param userChooseAnswers
	 */
	public void over(List<Integer> userChooseAnswers) {
		try {
			
			saveUserChooseAnswers.put(questIndex, userChooseAnswers);
			
			int temp = JOptionPane.showConfirmDialog(examFrame, "�ף�ȷ������");
			
			if(temp == 1 || temp == 2){
				return;
			}
			examService.handExamPaper(user,saveUserChooseAnswers);
			examFrame.hideView();
			menuFrame.showView();
			menuFrame.getStartBtn().setEnabled(false);
			menuFrame.getScoreBtn().setEnabled(true);
			
		} catch (ExamException e) {
			JOptionPane.showMessageDialog(examFrame, e.getMessage());
		}
		
	}
	
	// ����ʱ����ʾ��
	private class TestTime implements Runnable{

		private Integer examTime;
		
		private TestTime(Integer examTime){
			this.examTime = examTime;
		}
		
		public void run() {
			try {
				
				while(examTime > 0){
					examFrame.showRestTime("ʣ������� :"+examTime+"����");
					Thread.sleep(1000*60);
					examTime--;
				}		
				
				examFrame.getPrevBtn().setEnabled(false);
				examFrame.getNextBtn().setEnabled(false);
				JOptionPane.showMessageDialog(menuFrame, "ʱ�䵽��");
				
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(examFrame, "��,�����쳣 ������µ�½ ���ԣ� ");	
			}
		}
	}

	// ��ʾ ע�ᴰ�塣
	public void regist() {
		registFrame.showView();
		
	}

	// ����ע�ᴰ��� ��Ϣ��
	public void rest() {
		registFrame.getUid().setText("");
		registFrame.getUserName().setText("");
		registFrame.getPassword().setText("");
		registFrame.getRePassword().setText("");
		registFrame.getPhone().setText("");
		registFrame.getEmail().setText("");
		
	}

	// �û�ע�ᡣ
	public void userRegist() {
		
		try {
			userService.regist(
					registFrame.getUid().getText(), 
					registFrame.getUserName().getText(), 
					registFrame.getPassword().getText(),
					registFrame.getRePassword().getText(),
					registFrame.getPhone().getText(), 
					registFrame.getEmail().getText());
		} catch (RegistException e) {
			registFrame.showError(e.getMessage());
			return;
		}
		
		JOptionPane.showMessageDialog(registFrame,"�ף�ע��ɹ���ת����½����");
		registFrame.hideView();
		rest();
		
	}

	/**
	 * ���ļ� ���ؿ�����Ϣ��ʾ��
	 */
	public void lookExamInfo() {
		examRule.showView();
		try {
			examRule.showExamInfo(ClientTool.readFileInfo("examInfo.txt"));
		} catch (IOException e) {
			examRule.showExamInfo("sorry ����ȡ���Թ���ʧ�ܣ�");
		}
		
	}
	
	// �������塣
	public void lookScoreInfo() {
		this.scoreInfo.showView();
		try {
			UserhandPaper up = userService.fintPapr(userService.getLoginUser().getUid());
			scoreInfo.showScoreInfo(up.toString());
		} catch (ExamException e) {
			scoreInfo.showScoreInfo(e.getMessage());
		}
		
	}

	/**
	 * ���� �����档
	 */
	public void returnMenu() {
		int temp = JOptionPane.showConfirmDialog(examFrame, "����������󽫲��ᱣ��ѡ��Ĵ𰸣�");
		if(temp ==0 ){
			menuFrame.showView();
			examFrame.hideView();
		}
	}

	

	


	
}
