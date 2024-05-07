package com.webapp.apis.transactions.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.io.Serializable;
import com.webapp.apis.transactions.domain.BillingPrintDetail;

public class BillingPrintFormBean implements Serializable {
    private static final long serialVersionUID = 1L;
	private Integer id;
    private Date frmDt;
    private Date toDt;
    private Integer partyId;
    private String partyLabel;
    private BigDecimal totalamt;
    private BigDecimal totalWt;
    private BigDecimal totalDecAmt;
    private BigDecimal totalDecVal;
    private BigDecimal tax;
    private BigDecimal amtAftTax;
    private String address;
    private BigDecimal oldBalance;
    private BigDecimal newBalance;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private BigDecimal fuelSurcharge;
    private String locationId;
    private String locationCode;
    private Date docDt;
    private String docNo;
    private Date dueDt;
    private BigDecimal fuelSurchargePc;
    private BigDecimal serviceTaxPc;
    private BigDecimal eduCess;
    private BigDecimal sHEcess;
    private BigDecimal eduCessAmt;
    private BigDecimal sHEcessamt;
    private BigDecimal cgst;
    private BigDecimal sgst;
    private BigDecimal cgstAmt;
    private BigDecimal sgstAmt;
    private String state;
    private String address6;
    private String gst;
    private Collection<BillingPrintDetail> billingPrintDetailCollection;
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
	public BigDecimal getsHEcess() {
		return sHEcess;
	}
	public void setsHEcess(BigDecimal sHEcess) {
		this.sHEcess = sHEcess;
	}
	public BigDecimal getEduCessAmt() {
		return eduCessAmt;
	}
	public void setEduCessAmt(BigDecimal eduCessAmt) {
		this.eduCessAmt = eduCessAmt;
	}
	public BigDecimal getsHEcessamt() {
		return sHEcessamt;
	}
	public void setsHEcessamt(BigDecimal sHEcessamt) {
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
	public Collection<BillingPrintDetail> getBillingPrintDetailCollection() {
		return billingPrintDetailCollection;
	}
	public void setBillingPrintDetailCollection(Collection<BillingPrintDetail> billingPrintDetailCollection) {
		this.billingPrintDetailCollection = billingPrintDetailCollection;
	}
    
    

}
