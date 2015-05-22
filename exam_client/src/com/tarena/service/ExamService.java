package com.tarena.service;


import java.util.List;
import java.util.Map;

import com.tarena.entity.Paper;
import com.tarena.entity.Question;
import com.tarena.entity.User;
import com.tarena.exception.ExamException;

/**
 * 处理 试卷 接口。 
 * 逻辑层。
 * @author xiaoyao
 *
 */
public interface ExamService {
	
	/**
	 * 丛数据库中读取试卷
	 * @param paperId
	 * 试卷ID
	 * @return
	 * @throws ExamException
	 */
	public Paper loadPaper(Integer paperId)throws ExamException;
	
	/**
	 * 读取问题
	 * @param questionIndex
	 * 问题 ID 号。
	 * @return
	 * 提取的 Question 。
	 * @throws ExamException
	 */
	public Question next(Integer questionIndex)throws ExamException;
	
	public Question prev(Integer questionIndex)throws ExamException;
	
	/**
	 * 交卷
	 * @param user
	 * @param answers
	 * @throws ExamException
	 */
	public void handExamPaper(User user, Map<Integer,List<Integer>> answers) throws ExamException;

	
}
