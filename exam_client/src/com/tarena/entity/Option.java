package com.tarena.entity;

import java.io.Serializable;

/**
 * 问题的选项。
 * @author xiaoyao
 *
 */
public class Option implements Serializable{

	private static final long serialVersionUID = 4800863264157784221L;

	private Integer id;
	
	private String option_desc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOption_desc() {
		return option_desc;
	}

	public void setOption_desc(String option_desc) {
		this.option_desc = option_desc;
	}
}
