package com.webapp.apis.masters.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "party_master_detail")
public class PartyMasterDetail implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private Integer Id;
	@Column(name="branch_id")
	private Integer branchId;
	@Column(name="branch_name")
	private String branchName;
	@Column(name="branch_code")
	private String branchCode;
	@Column(name="location_name")
	private String locationName;
	@Column(name="party_fname")
	private String partyFname;
	@Column(name="party_sname")
	private String partySname;
	@Column(name="party_code")
	private String partyCode;
	
	@JoinColumn(name = "base_id", referencedColumnName = "id")
	@ManyToOne
	private PartyMaster baseId;


	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

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

	public PartyMaster getBaseId() {
		return baseId;
	}

	public void setBaseId(PartyMaster baseId) {
		this.baseId = baseId;
	}

}
