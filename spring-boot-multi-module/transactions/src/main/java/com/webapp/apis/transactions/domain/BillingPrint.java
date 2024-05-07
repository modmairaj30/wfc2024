package com.webapp.apis.transactions.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mohammed.mirajuddin
 */
/**
 * @author mohammed.mirajuddin
 *
 */
@Entity
@Table(name = "billing_print")
public class BillingPrint implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "frm_dt")
	@Temporal(TemporalType.DATE)
	private Date frmDt;
	@Column(name = "to_dt")
	@Temporal(TemporalType.DATE)
	private Date toDt;
	@Column(name = "party_id")
	private Integer partyId;
	@Column(name = "party_label")
	private String partyLabel;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "Total_amt")
	private BigDecimal totalamt;
	@Column(name = "Total_Wt")
	private BigDecimal totalWt;
	@Column(name = "Total_Dec_Amt")
	private BigDecimal totalDecAmt;
	@Column(name = "Total_Dec_Val")
	private BigDecimal totalDecVal;
	@Column(name = "tax")
	private BigDecimal tax;
	@Column(name = "amt_aft_tax")
	private BigDecimal amtAftTax;
	@Column(name = "address")
	private String address;
	@Column(name = "Old_Balance")
	private BigDecimal oldBalance;
	@Column(name = "new_balance")
	private BigDecimal newBalance;
	@Column(name = "address1")
	private String address1;
	@Column(name = "address_2")
	private String address2;
	@Column(name = "address_3")
	private String address3;
	@Column(name = "address_4")
	private String address4;
	@Column(name = "fuel_Surcharge")
	private BigDecimal fuelSurcharge;
	@Column(name = "location_id")
	private String locationId;
	@Column(name = "location_code")
	private String locationCode;
	@Column(name = "doc_dt")
	@Temporal(TemporalType.DATE)
	private Date docDt;
	@Column(name = "doc_no")
	private String docNo;
	@Column(name = "due_dt")
	@Temporal(TemporalType.DATE)
	private Date dueDt;
	@Column(name = "fuel_surcharge_pc")
	private BigDecimal fuelSurchargePc;
	@Column(name = "service_tax_pc")
	private BigDecimal serviceTaxPc;
	@Column(name = "edu_cess")
	private BigDecimal eduCess;
	@Column(name = "SHE_cess")
	private BigDecimal sHEcess;
	@Column(name = "edu_cess_amt")
	private BigDecimal eduCessAmt;
	@Column(name = "SHE_cess_amt")
	private BigDecimal sHEcessamt;
	@Column(name = "cgst")
	private BigDecimal cgst;
	@Column(name = "sgst")
	private BigDecimal sgst;
	@Column(name = "cgst_amt")
	private BigDecimal cgstAmt;
	@Column(name = "sgst_amt")
	private BigDecimal sgstAmt;
	@Column(name = "state")
	private String state;
	@Column(name = "address6")
	private String address6;
	@Column(name = "gst")
	private String gst;
	@OneToMany(mappedBy = "baseId")
	private List<BillingPrintDetail> billingPrintDetailCollection;

	public BillingPrint() {
	}

	public BillingPrint(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFrmDt() {
		return frmDt;
	}

	public void setFrmDt(Date frmDt) {
		this.frmDt = frmDt;
	}

	public Date getToDt() {
		return toDt;
	}

	public void setToDt(Date toDt) {
		this.toDt = toDt;
	}

	public Integer getPartyId() {
		return partyId;
	}

	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}

	public String getPartyLabel() {
		return partyLabel;
	}

	public void setPartyLabel(String partyLabel) {
		this.partyLabel = partyLabel;
	}

	public BigDecimal getTotalamt() {
		return totalamt;
	}

	public void setTotalamt(BigDecimal totalamt) {
		this.totalamt = totalamt;
	}

	public BigDecimal getTotalWt() {
		return totalWt;
	}

	public void setTotalWt(BigDecimal totalWt) {
		this.totalWt = totalWt;
	}

	public BigDecimal getTotalDecAmt() {
		return totalDecAmt;
	}

	public void setTotalDecAmt(BigDecimal totalDecAmt) {
		this.totalDecAmt = totalDecAmt;
	}

	public BigDecimal getTotalDecVal() {
		return totalDecVal;
	}

	public void setTotalDecVal(BigDecimal totalDecVal) {
		this.totalDecVal = totalDecVal;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getAmtAftTax() {
		return amtAftTax;
	}

	public void setAmtAftTax(BigDecimal amtAftTax) {
		this.amtAftTax = amtAftTax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getOldBalance() {
		return oldBalance;
	}

	public void setOldBalance(BigDecimal oldBalance) {
		this.oldBalance = oldBalance;
	}

	public BigDecimal getNewBalance() {
		return newBalance;
	}

	public void setNewBalance(BigDecimal newBalance) {
		this.newBalance = newBalance;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public BigDecimal getFuelSurcharge() {
		return fuelSurcharge;
	}

	public void setFuelSurcharge(BigDecimal fuelSurcharge) {
		this.fuelSurcharge = fuelSurcharge;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public Date getDocDt() {
		return docDt;
	}

	public void setDocDt(Date docDt) {
		this.docDt = docDt;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public Date getDueDt() {
		return dueDt;
	}

	public void setDueDt(Date dueDt) {
		this.dueDt = dueDt;
	}

	public BigDecimal getFuelSurchargePc() {
		return fuelSurchargePc;
	}

	public void setFuelSurchargePc(BigDecimal fuelSurchargePc) {
		this.fuelSurchargePc = fuelSurchargePc;
	}

	public BigDecimal getServiceTaxPc() {
		return serviceTaxPc;
	}

	public void setServiceTaxPc(BigDecimal serviceTaxPc) {
		this.serviceTaxPc = serviceTaxPc;
	}

	public BigDecimal getEduCess() {
		return eduCess;
	}

	public void setEduCess(BigDecimal eduCess) {
		this.eduCess = eduCess;
	}

	public BigDecimal getSHEcess() {
		return sHEcess;
	}

	public void setSHEcess(BigDecimal sHEcess) {
		this.sHEcess = sHEcess;
	}

	public BigDecimal getEduCessAmt() {
		return eduCessAmt;
	}

	public void setEduCessAmt(BigDecimal eduCessAmt) {
		this.eduCessAmt = eduCessAmt;
	}

	public BigDecimal getSHEcessamt() {
		return sHEcessamt;
	}

	public void setSHEcessamt(BigDecimal sHEcessamt) {
		this.sHEcessamt = sHEcessamt;
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

	public BigDecimal getCgstAmt() {
		return cgstAmt;
	}

	public void setCgstAmt(BigDecimal cgstAmt) {
		this.cgstAmt = cgstAmt;
	}

	public BigDecimal getSgstAmt() {
		return sgstAmt;
	}

	public void setSgstAmt(BigDecimal sgstAmt) {
		this.sgstAmt = sgstAmt;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddress6() {
		return address6;
	}

	public void setAddress6(String address6) {
		this.address6 = address6;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof BillingPrint)) {
			return false;
		}
		BillingPrint other = (BillingPrint) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "BillingPrint[ id=" + id + " ]";
	}

	public BigDecimal getsHEcess() {
		return sHEcess;
	}

	public void setsHEcess(BigDecimal sHEcess) {
		this.sHEcess = sHEcess;
	}

	public BigDecimal getsHEcessamt() {
		return sHEcessamt;
	}

	public void setsHEcessamt(BigDecimal sHEcessamt) {
		this.sHEcessamt = sHEcessamt;
	}

	public List<BillingPrintDetail> getBillingPrintDetailCollection() {
		return billingPrintDetailCollection;
	}

	public void setBillingPrintDetailCollection(List<BillingPrintDetail> billingPrintDetailCollection) {
		this.billingPrintDetailCollection = billingPrintDetailCollection;
	}
	
	

}
