package com.webapp.apis.masters.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mohammed.mirajuddin
 */
@Entity
@Table(name = "mode")
public class Mode implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "Modeofconsignment")
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
