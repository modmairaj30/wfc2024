package com.webapp.apis.transactions.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mairaj
 */
@Entity
@Table(name = "invoice")

public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "doc_dt")
    @Temporal(TemporalType.DATE)
    private Date docDt;
    @Column(name = "doc_no2")
    private String docNo2;
    @Column(name = "vch_id")
    private Integer vchId;
    @Column(name = "party_id")
    private String partyId;
    @Column(name = "address")
    private String address;
    @Column(name = "total_qty")
    private BigDecimal totalQty;
    @Column(name = "doc_no")
    private String docNo;
    @Column(name = "dest_branch")
    private String destBranch;
    @Column(name = "source_stn")
    private String sourceStn;
    @Column(name = "consignee_dest")
    private String consigneeDest;
    @Column(name = "Consignee_add")
    private String consigneeadd;
    @Column(name = "consigner_mob_no")
    private String consignerMobNo;
    @Column(name = "Consignee_name")
    private String consigneename;
    @Column(name = "consignee_mob_no")
    private String consigneeMobNo;
    @Column(name = "ser_amt")
    private BigDecimal serAmt;
    @Column(name = "teriff_location")
    private String teriffLocation;
    @Column(name = "gross_amt")
    private BigDecimal grossAmt;
    @Column(name = "net_amt2")
    private BigDecimal netAmt2;
    @Column(name = "time")
    @Temporal(TemporalType.TIME)
    private Date time;
    @Column(name = "cust_name")
    private String custName;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "dec_val")
    private BigDecimal decVal;
    @Column(name = "dec_per")
    private BigDecimal decPer;
    @Column(name = "Dec_amt")
    private BigDecimal decamt;
    @Column(name = "status")
    private String status;
    @Column(name = "location_id")
    private Integer locationId;
    @Column(name = "no_of_pieces")
    private Integer noOfPieces;
    @Column(name = "credit_acc")
    private Integer creditAcc;
    @Column(name = "weight")
    private BigDecimal weight;
    @Column(name = "tariff_id")
    private String tariffId;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "net_amt")
    private BigDecimal netAmt;
    @Column(name = "Prefix")
    private String prefix;
    @Column(name = "CNote_No")
    private String cNoteNo;
    @Column(name = "party_label")
    private String partyLabel;
    @Column(name = "batch_no2")
    private String batchNo2;
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "qty")
    private Integer qty;
    @Column(name = "qty2")
    private Integer qty2;
    @Column(name = "upload")
    private String upload;
    @Column(name = "branch_forwarding")
    private String branchForwarding;
    @Column(name = "serv_tax")
    private BigDecimal servTax;
    @Column(name = "fuel_tax")
    private BigDecimal fuelTax;
    @Column(name = "serv_amt")
    private BigDecimal servAmt;
    @Column(name = "fuel_amt")
    private BigDecimal fuelAmt;
    @Column(name = "od_charges")
    private BigDecimal odCharges;
    @Column(name = "edcess_per")
    private BigDecimal edcessPer;
    @Column(name = "shess_per")
    private BigDecimal shessPer;
    @Column(name = "shecess_amt")
    private BigDecimal shecessAmt;
    @Column(name = "educess_amt")
    private BigDecimal educessAmt;
    @Column(name = "rpt_netamt")
    private BigDecimal rptNetamt;
    @Column(name = "docket_charges")
    private BigDecimal docketCharges;
    @Column(name = "ref_no")
    private String refNo;
    @Column(name = "cnt_no")
    private Integer cntNo;
    @Column(name = "orgin_fld")
    private Integer orginFld;
    @Column(name = "system_date")
    @Temporal(TemporalType.DATE)
    private Date systemDate;
    @Column(name = "consigner_email_id")
    private String consignerEmailId;
    @Column(name = "batch_no")
    private String batchNo;
    @Column(name = "card_type")
    private String cardType;
    @Column(name = "dummy")
    private Integer dummy;
    @Column(name = "location_code")
    private String locationCode;
  /*  @Column(name = "upload_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadTime;*/
    @Column(name = "type")
    private String type;
    @Column(name = "invoice_no")
    private String invoiceNo;
    @Column(name = "cgst")
    private BigDecimal cgst;
    @Column(name = "sgst")
    private BigDecimal sgst;
    @Column(name = "cgst_amt")
    private BigDecimal cgstAmt;
    @Column(name = "sgst_amt")
    private BigDecimal sgstAmt;
    @Column(name = "cst_cal")
    private BigDecimal cstCal;
    @Column(name = "gst_cal")
    private BigDecimal gstCal;
    @Column(name = "igst_cal")
    private BigDecimal igstCal;
    @Column(name = "igst")
    private BigDecimal igst;
    @Column(name = "igst_amt")
    private BigDecimal igstAmt;
    @Column(name = "cgst_dummy")
    private BigDecimal cgstDummy;
    @Column(name = "sgst_dummy")
    private BigDecimal sgstDummy;
    @Column(name = "igst_dummy")
    private BigDecimal igstDummy;
    @Column(name = "stock_id")
    private Integer stockId;
    public Invoice() {
    }

    public Invoice(Integer id) {
        this.id = id;
    }

    

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

    public String getCNoteNo() {
        return cNoteNo;
    }

    public void setCNoteNo(String cNoteNo) {
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

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.Invoice[ id=" + id + " ]";
    }
    
}
