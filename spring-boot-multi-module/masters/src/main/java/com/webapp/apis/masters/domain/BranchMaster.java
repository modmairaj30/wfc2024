package com.webapp.apis.masters.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mohammed.mirajuddin
 */
@Entity
@Table(name = "tbl_location")
public class BranchMaster implements Serializable {

		
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @NotNull
   @Column(name = "location_name")
    private String locationName;
    
    @NotNull
    @Column(name = "location_code")
    private String locationCode;
    @Column(name = "address")
    private String address;
    @Column(name = "DLno")
    private String dLno;
    @Column(name = "TINNo")
    private String tINNo;
    @Column(name = "address1")
    private String address1;
    @Column(name = "cont_name")
    private String contName;
    
    @Column(name = "cont_no")
    private String contNo;
    
    @Column(name = "mob_no")
    private String mobNo;
    
    @Column(name = "region")
    private String region;
    
    @Column(name = "email_id")
    private String emailId;
    
    @Column(name = "branch")
    private String branch;
    @Column(name = "parent")
    private Integer parent;
    
    @Column(name = "district")
    private String district;
    
    @Column(name = "db_name")
    private String dbName;
    @Column(name = "local_OS")
    private String localOS;
    
    @Column(name = "l_type")
    private String lType;
    @Column(name = "Adress")
    private String adress;
    
    @Column(name = "Loca_Long_Name")
    private String locaLongName;
    @Column(name = "vat_pc")
    private BigDecimal vatPc;
    
    @Column(name = "VAT_No")
    private String vATNo;
    
    @Column(name = "CST_No")
    private String cSTNo;
    @Column(name = "Declaration")
    private String declaration;
    
    @Column(name = "location_id")
    private String locationId;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "newfld")
    private String newfld;
    
    @Column(name = "loc_type")
    private String locType;
    @Column(name = "rate")
    private BigDecimal rate;
    @Column(name = "ward_no")
    private String wardNo;
    @Column(name = "state_code")
    private Integer stateCode;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getdLno() {
		return dLno;
	}
	public void setdLno(String dLno) {
		this.dLno = dLno;
	}
	public String gettINNo() {
		return tINNo;
	}
	public void settINNo(String tINNo) {
		this.tINNo = tINNo;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getContName() {
		return contName;
	}
	public void setContName(String contName) {
		this.contName = contName;
	}
	public String getContNo() {
		return contNo;
	}
	public void setContNo(String contNo) {
		this.contNo = contNo;
	}
	public String getMobNo() {
		return mobNo;
	}
	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getLocalOS() {
		return localOS;
	}
	public void setLocalOS(String localOS) {
		this.localOS = localOS;
	}
	public String getlType() {
		return lType;
	}
	public void setlType(String lType) {
		this.lType = lType;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getLocaLongName() {
		return locaLongName;
	}
	public void setLocaLongName(String locaLongName) {
		this.locaLongName = locaLongName;
	}
	public BigDecimal getVatPc() {
		return vatPc;
	}
	public void setVatPc(BigDecimal vatPc) {
		this.vatPc = vatPc;
	}
	public String getvATNo() {
		return vATNo;
	}
	public void setvATNo(String vATNo) {
		this.vATNo = vATNo;
	}
	public String getcSTNo() {
		return cSTNo;
	}
	public void setcSTNo(String cSTNo) {
		this.cSTNo = cSTNo;
	}
	public String getDeclaration() {
		return declaration;
	}
	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getNewfld() {
		return newfld;
	}
	public void setNewfld(String newfld) {
		this.newfld = newfld;
	}
	public String getLocType() {
		return locType;
	}
	public void setLocType(String locType) {
		this.locType = locType;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public String getWardNo() {
		return wardNo;
	}
	public void setWardNo(String wardNo) {
		this.wardNo = wardNo;
	}
	public Integer getStateCode() {
		return stateCode;
	}
	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}
    
    
    
    
	        
}
