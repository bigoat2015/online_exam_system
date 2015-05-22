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
 * 控制器 对窗体 内容显示处理。
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
	 * 提取问题索引
	 */
	private Integer questIndex = 0;
	
	/**
	 * 保存用户选择的答案。
	 */
	private Map<Integer,List<Integer>> saveUserChooseAnswers = new HashMap<Integer,List<Integer>>();
	
	
	
	/**
	 * 上一题 
	 * @param userChooseAnswers
	 * 从床窗体 ExamException 得到选择按钮的状态。
	 */
	public void prev(List<Integer> userChooseAnswers){
		try {
			
			// 保存用户当前选择的答案。
			saveUserChooseAnswers.put(questIndex,userChooseAnswers);
			
			// 还回上一题 
			--questIndex;
			Question question = examService.prev(questIndex);
			
			// 使用 ClientTool.formatQuestionShow() 方法 格式化 问题显示。
			String showQuestion = ClientTool.formatQuestionShow(question);
			
			//显示问题到窗体中 更新题号。
			examFrame.showTitleProgress("题目:20的"+(questIndex+1)+"题");
			examFrame.showQuestion(showQuestion);
			
			// 读取用户 该题 选择的答案。
			readUserChooseAnswers(questIndex);
			
			examFrame.getNextBtn().setEnabled(true);
			
			if(questIndex == 0){
				examFrame.getPrevBtn().setEnabled(false);
			}
			
		} catch (ExamException e) {
			JOptionPane.showMessageDialog(examFrame, "读取试卷失败！");
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
			examFrame.showTitleProgress("题目:20的"+(questIndex+1)+"题");
			
			readUserChooseAnswers(questIndex);
	
			examFrame.getPrevBtn().setEnabled(true);
			
			if(questIndex == 19){
			
				examFrame.getNextBtn().setEnabled(false);
			}	
		
		} catch (ExamException e) {
			JOptionPane.showMessageDialog(examFrame, "读取试卷失败！");
			examFrame.hideView();
			menuFrame.hideView();
		} 
	}

	/**
	 * 读取用户选择的答案。
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
	 * 点击 考试 按键  
	 * 初始化：
	 * 选择按钮。
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
			examFrame.showTitleProgress("题目:20的"+(questIndex+1)+"题");
			
			// 加载 考试时间：
			new Thread(new TestTime(paper.getExam_time())).start();
				
			examFrame.getPrevBtn().setEnabled(false);
		
		} catch (ExamException e) {
			examFrame.showQuestion(e.getMessage());
		}
		
		//1,调用考试的业务逻辑层加载某一套试卷
		//2,更新考试界面上的考试信息
		//3,把试卷的第一题显示在考试界面上
		//4,设置上一题按钮不可用
	}
	
	// 实现登陆
	public void login(){
		String userId = loginFrame.getUser();
		String password = loginFrame.getPassword();
		try {
			
			userService.login(userId,password);
			loginFrame.hideView();
			menuFrame.showView();
			menuFrame.showUserInfo("欢迎  "+ userService.getLoginUser().getName()+" 的到来");
			
			UserhandPaper up = userService.fintPapr(userService.getLoginUser().getUid());
			if(up != null){
				menuFrame.getStartBtn().setEnabled(false);
				scoreInfo.showScoreInfo(up.toString());
				menuFrame.getScoreBtn().setEnabled(true);
				JOptionPane.showMessageDialog(menuFrame,(userService.getLoginUser().getName()+"：您已考试过！"));
			}
		
		} catch (UserOrPasswordException e) {
			loginFrame.showError(e.getMessage());
			
		} catch (IOException e) {
			loginFrame.showError("连接异常");
		} catch (ExamException e) {
			JOptionPane.showMessageDialog(menuFrame,(userService.getLoginUser().getName()+"：您 还没有考试 ！"));
		}
	}

	/**
	 * 交卷 
	 * 保存 当前用户 当前选择题目的答案
	 * @param userChooseAnswers
	 */
	public void over(List<Integer> userChooseAnswers) {
		try {
			
			saveUserChooseAnswers.put(questIndex, userChooseAnswers);
			
			int temp = JOptionPane.showConfirmDialog(examFrame, "亲：确定交卷");
			
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
	
	// 考试时间显示：
	private class TestTime implements Runnable{

		private Integer examTime;
		
		private TestTime(Integer examTime){
			this.examTime = examTime;
		}
		
		public void run() {
			try {
				
				while(examTime > 0){
					examFrame.showRestTime("剩余分钟数 :"+examTime+"分钟");
					Thread.sleep(1000*60);
					examTime--;
				}		
				
				examFrame.getPrevBtn().setEnabled(false);
				examFrame.getNextBtn().setEnabled(false);
				JOptionPane.showMessageDialog(menuFrame, "时间到！");
				
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(examFrame, "亲,出现异常 ，请从新登陆 考试！ ");	
			}
		}
	}

	// 显示 注册窗体。
	public void regist() {
		registFrame.showView();
		
	}

	// 重置注册窗体的 信息。
	public void rest() {
		registFrame.getUid().setText("");
		registFrame.getUserName().setText("");
		registFrame.getPassword().setText("");
		registFrame.getRePassword().setText("");
		registFrame.getPhone().setText("");
		registFrame.getEmail().setText("");
		
	}

	// 用户注册。
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
		
		JOptionPane.showMessageDialog(registFrame,"亲，注册成功，转到登陆界面");
		registFrame.hideView();
		rest();
		
	}

	/**
	 * 从文件 加载考试信息显示。
	 */
	public void lookExamInfo() {
		examRule.showView();
		try {
			examRule.showExamInfo(ClientTool.readFileInfo("examInfo.txt"));
		} catch (IOException e) {
			examRule.showExamInfo("sorry ，读取考试规则失败！");
		}
		
	}
	
	// 分数窗体。
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
	 * 还回 主界面。
	 */
	public void returnMenu() {
		int temp = JOptionPane.showConfirmDialog(examFrame, "返回主界面后将不会保存选择的答案？");
		if(temp ==0 ){
			menuFrame.showView();
			examFrame.hideView();
		}
	}

	

	


	
}
