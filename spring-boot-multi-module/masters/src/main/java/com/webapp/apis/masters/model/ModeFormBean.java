package com.webapp.apis.masters.model;

import java.io.Serializable;

public class ModeFormBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String modeOfConsignment;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getModeOfConsignment() {
		return modeOfConsignment;
	}
	public void setModeOfConsignment(String modeOfConsignment) {
		this.modeOfConsignment = modeOfConsignment;
	}
	
	
}
