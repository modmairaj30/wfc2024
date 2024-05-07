package com.webapp.apis.transactions.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.webapp.apis.transactions.domain.StockincnoteDetail;

public class StockincnoteFormBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Date docDt;
    private String docNo;
    private String uid;
    private Integer locationId;
    private String cPrefix;
    private String fromNo;
    private String toNo;
    
    private String createdBy;
	private String updatedBy;
	private Date createdDate;
	private Date updatedDate;

    
    private List<StockincnoteDetailFormBean> stockincnoteDetailList;
    
  
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
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	
	public String getcPrefix() {
		return cPrefix;
	}
	public void setcPrefix(String cPrefix) {
		this.cPrefix = cPrefix;
	}
	public String getFromNo() {
		return fromNo;
	}
	public void setFromNo(String fromNo) {
		this.fromNo = fromNo;
	}
	public String getToNo() {
		return toNo;
	}
	public void setToNo(String toNo) {
		this.toNo = toNo;
	}
	public List<StockincnoteDetailFormBean> getStockincnoteDetailList() {
		return stockincnoteDetailList;
	}
	public void setStockincnoteDetailList(List<StockincnoteDetailFormBean> stockincnoteDetailList) {
		this.stockincnoteDetailList = stockincnoteDetailList;
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
	
	
	
    
}