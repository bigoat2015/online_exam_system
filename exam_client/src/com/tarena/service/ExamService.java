package com.tarena.service;


import java.util.List;
import java.util.Map;

import com.tarena.entity.Paper;
import com.tarena.entity.Question;
import com.tarena.entity.User;
import com.tarena.exception.ExamException;

/**
 * ���� �Ծ� �ӿڡ� 
 * �߼��㡣
 * @author xiaoyao
 *
 */
public interface ExamService {
	
	/**
	 * �����ݿ��ж�ȡ�Ծ�
	 * @param paperId
	 * �Ծ�ID
	 * @return
	 * @throws ExamException
	 */
	public Paper loadPaper(Integer paperId)throws ExamException;
	
	/**
	 * ��ȡ����
	 * @param questionIndex
	 * ���� ID �š�
	 * @return
	 * ��ȡ�� Question ��
	 * @throws ExamException
	 */
	public Question next(Integer questionIndex)throws ExamException;
	
	public Question prev(Integer questionIndex)throws ExamException;
	
	/**
	 * ����
	 * @param user
	 * @param answers
	 * @throws ExamException
	 */
	public void handExamPaper(User user, Map<Integer,List<Integer>> answers) throws ExamException;

	
}
