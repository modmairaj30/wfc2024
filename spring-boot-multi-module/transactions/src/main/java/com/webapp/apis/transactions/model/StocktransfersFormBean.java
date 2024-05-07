package com.webapp.apis.transactions.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class StocktransfersFormBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
    private Integer id;    
    private Integer locationId;
    private Integer tolocation;
    private Date docDt;    
    private String docNo;
    private String comment;
   
//    private String truckNo;
//    private String driverName;
//    private String employee;
//    private BigDecimal grossAmt;
//    private BigDecimal discAmt;
//    private BigDecimal netAmt;
//    private String fldDocno;
   
    private String createdBy;
	private String updatedBy;
	private Date createdDate;
	private Date updatedDate;
	private String verifiedBy;
	private String userId;
    private List<StocktransfersDetailFormBean> stocktransfersDetailList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
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
	
	
	
	public Integer getTolocation() {
		return tolocation;
	}
	public void setTolocation(Integer tolocation) {
		this.tolocation = tolocation;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
	public List<StocktransfersDetailFormBean> getStocktransfersDetailList() {
		return stocktransfersDetailList;
	}
	public void setStocktransfersDetailList(List<StocktransfersDetailFormBean> stocktransfersDetailList) {
		this.stocktransfersDetailList = stocktransfersDetailList;
	}
	public String getVerifiedBy() {
		return verifiedBy;
	}
	public void setVerifiedBy(String verifiedBy) {
		this.verifiedBy = verifiedBy;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
		
		
	
	
	
    
    

}
