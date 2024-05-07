package com.webapp.apis.masters.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "tbl_party_info")
public class PartyMaster implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "reff_by")
	private String reffBy;
	
	@Column(name = "party_name")
	private String partyName;
	
	@Column(name = "Party_label")
	private String partylabel;
	
	/*@Column(name = "party_name_branch")
	String party_name_location;*/
	
	@Column(name = "party_type")
	private Integer partyType;
	@Column(name = "address_1")
	private String address1;
	
	@Column(name = "customer_gstno")
	private String customerGstno;
	
	@Column(name = "address_2")
	private String address2;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "location_id")
	private String locationId;
	@Column(name = "customer_panno")
	private String customerPanno;
	
	@Column(name = "Contactperson")
	private String contactperson;
	
	@Column(name = "Trasporter")
	private String trasporter;
	
	@Column(name = "service_tax_pc")
	private BigDecimal serviceTaxPc;
	@Column(name = "fuel_surcharge_pc")
	private BigDecimal fuelSurchargePc;
	@Column(name = "cgst")
	private BigDecimal cgst;
	@Column(name = "sgst")
	private BigDecimal sgst;
	@Column(name = "igst")
	private BigDecimal igst;
	@Column(name = "phno")
	private String phno;
	
	@Column(name = "mobileno")
	private String mobileno;
	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "pincode")
	private String pincode;
	@Column(name = "email")
	private String email;
	@Column(name = "address_3")
	private String address3;
	@Column(name = "doc_no")
	private String docNo;
	
	@Column(name = "branch")
	private Integer branchId;
	
	@Column(name = "declaration")
	private BigDecimal declaration;

	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "updated_date")
	private Date updatedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	

	public String getPartylabel() {
		return partylabel;
	}

	public void setPartylabel(String partylabel) {
		this.partylabel = partylabel;
	}

	public Integer getPartyType() {
		return partyType;
	}

	public void setPartyType(Integer partyType) {
		this.partyType = partyType;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getCustomerGstno() {
		return customerGstno;
	}

	public void setCustomerGstno(String customerGstno) {
		this.customerGstno = customerGstno;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
	public String getCustomerPanno() {
		return customerPanno;
	}

	public void setCustomerPanno(String customerPanno) {
		this.customerPanno = customerPanno;
	}

	public String getReffBy() {
		return reffBy;
	}

	public void setReffBy(String reffBy) {
		this.reffBy = reffBy;
	}

	public String getContactperson() {
		return contactperson;
	}

	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}

	public String getTrasporter() {
		return trasporter;
	}

	public void setTrasporter(String trasporter) {
		this.trasporter = trasporter;
	}

	public BigDecimal getServiceTaxPc() {
		return serviceTaxPc;
	}

	public void setServiceTaxPc(BigDecimal serviceTaxPc) {
		this.serviceTaxPc = serviceTaxPc;
	}

	public BigDecimal getFuelSurchargePc() {
		return fuelSurchargePc;
	}

	public void setFuelSurchargePc(BigDecimal fuelSurchargePc) {
		this.fuelSurchargePc = fuelSurchargePc;
	}

	public BigDecimal getCgst() {
		return cgst;
	}

	public void setCgst(BigDecimal cgst) {
		this.cgst = cgst;
	}

	public BigDecimal getSgst() {
		return sgst;
	}

	public void setSgst(BigDecimal sgst) {
		this.sgst = sgst;
	}

	public BigDecimal getIgst() {
		return igst;
	}

	public void setIgst(BigDecimal igst) {
		this.igst = igst;
	}

	public String getMobileno() {
		return mobileno;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPincode() {
		return pincode;
	}

	/*public String getParty_name_location() {
		return party_name_location;
	}

	public void setParty_name_location(String party_name_location) {
		this.party_name_location = party_name_location;
	}*/

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public BigDecimal getDeclaration() {
		return declaration;
	}

	public void setDeclaration(BigDecimal declaration) {
		this.declaration = declaration;
	}
	
	
}
