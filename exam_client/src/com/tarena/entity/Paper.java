package com.tarena.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 试卷对象。
 * @author xiaoyao
 *
 */
public class Paper implements Serializable{

	private static final long serialVersionUID = 4814297247794792772L;

	private Integer id;
	
	private String paper_desc;
	
	private Integer exam_time;
	
	private Integer total_score;
	
	private List<Question> questions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPaper_desc() {
		return paper_desc;
	}

	public void setPaper_desc(String paper_desc) {
		this.paper_desc = paper_desc;
	}

	public Integer getExam_time() {
		return exam_time;
	}

	public void setExam_time(Integer exam_time) {
		this.exam_time = exam_time;
	}

	public Integer getTotal_score() {
		return total_score;
	}

	public void setTotal_score(Integer total_score) {
		this.total_score = total_score;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public String toString() {
		return "考试时间:"+getExam_time()+"  分钟  "+"  考试科目:"+getPaper_desc();
	}
	
}
