package com.tarena.entity;

import java.io.Serializable;
import java.util.List;

/**
 *  ‘æÌŒ Ã‚°£
 * @author xiaoyao
 *
 */
public class Question implements Serializable{

	private static final long serialVersionUID = 5681770350088469282L;

	private Integer id;
	
	private Float score;
	
	private Integer q_level;
	
	private String que_desc;
	
	private List<Integer> answer;
	
	private List<Option> options;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Integer getQ_level() {
		return q_level;
	}

	public void setQ_level(Integer q_level) {
		this.q_level = q_level;
	}

	public String getQue_desc() {
		return que_desc;
	}

	public void setQue_desc(String que_desc) {
		this.que_desc = que_desc;
	}

	public List<Integer> getAnswer() {
		return answer;
	}

	public void setAnswer(List<Integer> answer) {
		this.answer = answer;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}
}
