package com.webapp.apis.masters.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class TariffTypeMasterFormBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;

	private String type;

	private BigDecimal weight;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

}
