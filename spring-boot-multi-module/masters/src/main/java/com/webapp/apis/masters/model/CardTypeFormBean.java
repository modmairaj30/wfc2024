package com.webapp.apis.masters.model;

import java.io.Serializable;

public class CardTypeFormBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String cardName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	
	

}
