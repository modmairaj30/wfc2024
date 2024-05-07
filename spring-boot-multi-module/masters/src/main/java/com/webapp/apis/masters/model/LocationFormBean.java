package com.webapp.apis.masters.model;

import java.io.Serializable;

public class LocationFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String locationName;

	private String locationCode;

	private String locationId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

}
