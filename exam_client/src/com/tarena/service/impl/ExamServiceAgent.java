package com.tarena.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tarena.entity.Paper;
import com.tarena.entity.Question;
import com.tarena.entity.User;
import com.tarena.entity.UserhandPaper;
import com.tarena.exception.ExamException;
import com.tarena.exception.RegistException;
import com.tarena.net.Net;
import com.tarena.net.Request;
import com.tarena.net.Response;
import com.tarena.service.ExamService;
/**
 * 考试处理
 * 逻辑层
 * @author xiaoyao
 *
 */
public class ExamServiceAgent implements ExamService{

	private Question currentQuestion;
	private List<Question> questions;
	private Paper paper;
	private UserhandPaper userHandPaper;
	
	/**
	 * 加载试卷
	 */
	public Paper loadPaper(Integer paperId) throws  ExamException{
		Request reqLoadPaper = new Request();
		reqLoadPaper.setReqState(Request.LOAD_PAPER_REQ);
		
		Paper p = new Paper();
		p.setId(paperId);
		
		reqLoadPaper.addData("loadPaper", p);
	
		try {
			Response res = Net.remoteCall(reqLoadPaper);
			int resState = 
					res.getResState();
			if(resState == Response.LOAD_LOGIN_FAIL_RES){
				throw new ExamException("sorry ,试卷加载失败,请稍后再试！");
			}
			
			paper = (Paper)res.getData("loadPaper");
			questions = paper.getQuestions();		
			
		} catch (IOException e) {
			throw new ExamException("sorry , 连接失败！");
		}
		return paper;
	}
	

	/**
	 * 加载  题目。
	 */
	public Question next(Integer questionIndex) throws ExamException {
		
		currentQuestion = questions.get(questionIndex);	
		return currentQuestion;
	}

	public Question prev(Integer questionIndex) throws ExamException {
		
		currentQuestion = questions.get(questionIndex);
		
		return currentQuestion;
	}

	/**
	 * 交卷
	 */
	public void handExamPaper(User user,Map<Integer, List<Integer>> answers)throws ExamException {
		Request reqUserWritPaper = new Request();
		reqUserWritPaper.setReqState(Request.WRITE_USER_PAPER_REQ);
		
		float score = 0;
		
		for(int i=0, size=answers.size(); i<size; i++){
			Question q = questions.get(i);
			List<Integer> chooseAnswers = answers.get(i);
			
			score+=alterExam(q,q.getAnswer(),chooseAnswers);
			
		}
		
		userHandPaper = new UserhandPaper();
		userHandPaper.setPaperId(paper.getId());
		userHandPaper.setScore(score);
		userHandPaper.setUserId(user.getUid());
		
		reqUserWritPaper.addData("handExamPaper", userHandPaper);
		try {
			Response res = Net.remoteCall(reqUserWritPaper);
			int resState = 
					res.getResState();
			if(resState == Response.WRITE_USER_PAPER_FALL_RES){
				throw new ExamException("sorry 交卷失败,请稍后再试！");
			}
			
			userHandPaper = (UserhandPaper) res.getData("handExamPaper");
		
		
		
		} catch (IOException e) {
			throw new ExamException("sorry 连接！");
		}
		
		
	}

	/**
	 * 修改试卷
	 * @param q
	 * @param t
	 * @param c
	 * @return
	 */
	private float alterExam(Question q,List<Integer> t,List<Integer> c) {
		
		float score = 0;
		int count = 0;
		
		// 选择的 答案 多余 正确答案 直接 past
		if(c.size()>t.size()){
			return score;
		}
		
		// 判断问题是否相等。
		for(int i=0; i<t.size(); i++){
			for(int j=0; j<c.size(); j++){
				if(t.get(i).intValue() == c.get(j).intValue()){
					++count;
				}
			}
			
			
		}
		
		score = q.getScore()/t.size()*count;
		return score;
		
		
	}

}
