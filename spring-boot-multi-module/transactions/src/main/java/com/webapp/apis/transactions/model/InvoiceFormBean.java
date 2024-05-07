package com.webapp.apis.transactions.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InvoiceFormBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;

	private Date docDt;
	private String docNo2;
	private Integer vchId;

	private String partyId;

	private String address;

	private BigDecimal totalQty;
	private String docNo;

	private String destBranch;

	private String sourceStn;

	private String consigneeDest;

	private String consigneeadd;

	private String consignerMobNo;

	private String consigneename;

	private String consigneeMobNo;
	private BigDecimal serAmt;

	private String teriffLocation;

	private BigDecimal grossAmt;

	private BigDecimal netAmt2;

	private Date time;

	private String custName;
	private String userId;
	private BigDecimal decVal;
	private BigDecimal decPer;
	private BigDecimal decamt;
	private String status;

	private Integer locationId;

	private Integer noOfPieces;

	private Integer creditAcc;
	private BigDecimal weight;

	private String tariffId;
	private BigDecimal amount;
	private BigDecimal netAmt;
	private String prefix;

	private String cNoteNo;

	private String partyLabel;

	private String batchNo2;

	private Integer productId;
	private Integer qty;
	private Integer qty2;

	private String upload;

	private String branchForwarding;

	private BigDecimal servTax;

	private BigDecimal fuelTax;

	private BigDecimal servAmt;

	private BigDecimal fuelAmt;

	private BigDecimal odCharges;

	private BigDecimal edcessPer;

	private BigDecimal shessPer;

	private BigDecimal shecessAmt;

	private BigDecimal educessAmt;

	private BigDecimal rptNetamt;

	private BigDecimal docketCharges;
	private String refNo;
	private Integer cntNo;

	private Integer orginFld;
	private Date systemDate;

	private String consignerEmailId;

	private String batchNo;

	private String cardType;
	private Integer dummy;

	private String locationCode;
	private Date uploadTime;
	private String type;

	private String invoiceNo;
	private BigDecimal cgst;
	private BigDecimal sgst;

	private BigDecimal cgstAmt;

	private BigDecimal sgstAmt;
	private BigDecimal cstCal;
	private BigDecimal gstCal;

	private BigDecimal igstCal;
	private BigDecimal igst;

	private BigDecimal igstAmt;

	private BigDecimal cgstDummy;

	private BigDecimal sgstDummy;

	private BigDecimal igstDummy;
	private Integer stockId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDocDt() {
		return docDt;
	}

	public void setDocDt(Date docDt) {
		this.docDt = docDt;
	}

	public String getDocNo2() {
		return docNo2;
	}

	public void setDocNo2(String docNo2) {
		this.docNo2 = docNo2;
	}

	public Integer getVchId() {
		return vchId;
	}

	public void setVchId(Integer vchId) {
		this.vchId = vchId;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(BigDecimal totalQty) {
		this.totalQty = totalQty;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getDestBranch() {
		return destBranch;
	}

	public void setDestBranch(String destBranch) {
		this.destBranch = destBranch;
	}

	public String getSourceStn() {
		return sourceStn;
	}

	public void setSourceStn(String sourceStn) {
		this.sourceStn = sourceStn;
	}

	public String getConsigneeDest() {
		return consigneeDest;
	}

	public void setConsigneeDest(String consigneeDest) {
		this.consigneeDest = consigneeDest;
	}

	public String getConsigneeadd() {
		return consigneeadd;
	}

	public void setConsigneeadd(String consigneeadd) {
		this.consigneeadd = consigneeadd;
	}

	public String getConsignerMobNo() {
		return consignerMobNo;
	}

	public void setConsignerMobNo(String consignerMobNo) {
		this.consignerMobNo = consignerMobNo;
	}

	public String getConsigneename() {
		return consigneename;
	}

	public void setConsigneename(String consigneename) {
		this.consigneename = consigneename;
	}

	public String getConsigneeMobNo() {
		return consigneeMobNo;
	}

	public void setConsigneeMobNo(String consigneeMobNo) {
		this.consigneeMobNo = consigneeMobNo;
	}

	public BigDecimal getSerAmt() {
		return serAmt;
	}

	public void setSerAmt(BigDecimal serAmt) {
		this.serAmt = serAmt;
	}

	public String getTeriffLocation() {
		return teriffLocation;
	}

	public void setTeriffLocation(String teriffLocation) {
		this.teriffLocation = teriffLocation;
	}

	public BigDecimal getGrossAmt() {
		return grossAmt;
	}

	public void setGrossAmt(BigDecimal grossAmt) {
		this.grossAmt = grossAmt;
	}

	public BigDecimal getNetAmt2() {
		return netAmt2;
	}

	public void setNetAmt2(BigDecimal netAmt2) {
		this.netAmt2 = netAmt2;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getDecVal() {
		return decVal;
	}

	public void setDecVal(BigDecimal decVal) {
		this.decVal = decVal;
	}

	public BigDecimal getDecPer() {
		return decPer;
	}

	public void setDecPer(BigDecimal decPer) {
		this.decPer = decPer;
	}

	public BigDecimal getDecamt() {
		return decamt;
	}

	public void setDecamt(BigDecimal decamt) {
		this.decamt = decamt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getNoOfPieces() {
		return noOfPieces;
	}

	public void setNoOfPieces(Integer noOfPieces) {
		this.noOfPieces = noOfPieces;
	}

	public Integer getCreditAcc() {
		return creditAcc;
	}

	public void setCreditAcc(Integer creditAcc) {
		this.creditAcc = creditAcc;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getTariffId() {
		return tariffId;
	}

	public void setTariffId(String tariffId) {
		this.tariffId = tariffId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getNetAmt() {
		return netAmt;
	}

	public void setNetAmt(BigDecimal netAmt) {
		this.netAmt = netAmt;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getcNoteNo() {
		return cNoteNo;
	}

	public void setcNoteNo(String cNoteNo) {
		this.cNoteNo = cNoteNo;
	}

	public String getPartyLabel() {
		return partyLabel;
	}

	public void setPartyLabel(String partyLabel) {
		this.partyLabel = partyLabel;
	}

	public String getBatchNo2() {
		return batchNo2;
	}

	public void setBatchNo2(String batchNo2) {
		this.batchNo2 = batchNo2;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getQty2() {
		return qty2;
	}

	public void setQty2(Integer qty2) {
		this.qty2 = qty2;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getBranchForwarding() {
		return branchForwarding;
	}

	public void setBranchForwarding(String branchForwarding) {
		this.branchForwarding = branchForwarding;
	}

	public BigDecimal getServTax() {
		return servTax;
	}

	public void setServTax(BigDecimal servTax) {
		this.servTax = servTax;
	}

	public BigDecimal getFuelTax() {
		return fuelTax;
	}

	public void setFuelTax(BigDecimal fuelTax) {
		this.fuelTax = fuelTax;
	}

	public BigDecimal getServAmt() {
		return servAmt;
	}

	public void setServAmt(BigDecimal servAmt) {
		this.servAmt = servAmt;
	}

	public BigDecimal getFuelAmt() {
		return fuelAmt;
	}

	public void setFuelAmt(BigDecimal fuelAmt) {
		this.fuelAmt = fuelAmt;
	}

	public BigDecimal getOdCharges() {
		return odCharges;
	}

	public void setOdCharges(BigDecimal odCharges) {
		this.odCharges = odCharges;
	}

	public BigDecimal getEdcessPer() {
		return edcessPer;
	}

	public void setEdcessPer(BigDecimal edcessPer) {
		this.edcessPer = edcessPer;
	}

	public BigDecimal getShessPer() {
		return shessPer;
	}

	public void setShessPer(BigDecimal shessPer) {
		this.shessPer = shessPer;
	}

	public BigDecimal getShecessAmt() {
		return shecessAmt;
	}

	public void setShecessAmt(BigDecimal shecessAmt) {
		this.shecessAmt = shecessAmt;
	}

	public BigDecimal getEducessAmt() {
		return educessAmt;
	}

	public void setEducessAmt(BigDecimal educessAmt) {
		this.educessAmt = educessAmt;
	}

	public BigDecimal getRptNetamt() {
		return rptNetamt;
	}

	public void setRptNetamt(BigDecimal rptNetamt) {
		this.rptNetamt = rptNetamt;
	}

	public BigDecimal getDocketCharges() {
		return docketCharges;
	}

	public void setDocketCharges(BigDecimal docketCharges) {
		this.docketCharges = docketCharges;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public Integer getCntNo() {
		return cntNo;
	}

	public void setCntNo(Integer cntNo) {
		this.cntNo = cntNo;
	}

	public Integer getOrginFld() {
		return orginFld;
	}

	public void setOrginFld(Integer orginFld) {
		this.orginFld = orginFld;
	}

	public Date getSystemDate() {
		return systemDate;
	}

	public void setSystemDate(Date systemDate) {
		this.systemDate = systemDate;
	}

	public String getConsignerEmailId() {
		return consignerEmailId;
	}

	public void setConsignerEmailId(String consignerEmailId) {
		this.consignerEmailId = consignerEmailId;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Integer getDummy() {
		return dummy;
	}

	public void setDummy(Integer dummy) {
		this.dummy = dummy;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
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

	public BigDecimal getCstCal() {
		return cstCal;
	}

	public void setCstCal(BigDecimal cstCal) {
		this.cstCal = cstCal;
	}

	public BigDecimal getGstCal() {
		return gstCal;
	}

	public void setGstCal(BigDecimal gstCal) {
		this.gstCal = gstCal;
	}

	public BigDecimal getIgstCal() {
		return igstCal;
	}

	public void setIgstCal(BigDecimal igstCal) {
		this.igstCal = igstCal;
	}

	public BigDecimal getIgst() {
		return igst;
	}

	public void setIgst(BigDecimal igst) {
		this.igst = igst;
	}

	public BigDecimal getIgstAmt() {
		return igstAmt;
	}

	public void setIgstAmt(BigDecimal igstAmt) {
		this.igstAmt = igstAmt;
	}

	public BigDecimal getCgstDummy() {
		return cgstDummy;
	}

	public void setCgstDummy(BigDecimal cgstDummy) {
		this.cgstDummy = cgstDummy;
	}

	public BigDecimal getSgstDummy() {
		return sgstDummy;
	}

	public void setSgstDummy(BigDecimal sgstDummy) {
		this.sgstDummy = sgstDummy;
	}

	public BigDecimal getIgstDummy() {
		return igstDummy;
	}

	public void setIgstDummy(BigDecimal igstDummy) {
		this.igstDummy = igstDummy;
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}
	
	

}
