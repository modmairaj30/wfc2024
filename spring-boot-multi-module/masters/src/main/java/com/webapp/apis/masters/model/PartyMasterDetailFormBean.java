package com.webapp.apis.masters.model;

import java.io.Serializable;

public class PartyMasterDetailFormBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	
	private Integer branchId;
	
	private Integer baseId;
	
	private String branchName;
	
	private String branchCode;
	
	private String locationName;
	
	private String partyFname;
	
	private String partySname;
	
	private String partyCode;

	
	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getPartyFname() {
		return partyFname;
	}

	public void setPartyFname(String partyFname) {
		this.partyFname = partyFname;
	}

	public String getPartySname() {
		return partySname;
	}

	public void setPartySname(String partySname) {
		this.partySname = partySname;
	}

	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBaseId() {
		return baseId;
	}

	public void setBaseId(Integer baseId) {
		this.baseId = baseId;
	}
	
	

}
