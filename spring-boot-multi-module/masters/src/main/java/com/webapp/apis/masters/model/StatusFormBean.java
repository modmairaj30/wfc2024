package com.webapp.apis.masters.model;

import java.io.Serializable;

public class StatusFormBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
