package com.webapp.apis.transactions.model;

import java.io.Serializable;
import java.util.List;

public class StockincnoteDetailFormBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer baseId;
    private String productId;
    private String cNoteNo;
    private Integer qty;
    private Integer sNo;
    
    
    
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public String getcNoteNo() {
		return cNoteNo;
	}
	public void setcNoteNo(String cNoteNo) {
		this.cNoteNo = cNoteNo;
	}
	public Integer getsNo() {
		return sNo;
	}
	public void setsNo(Integer sNo) {
		this.sNo = sNo;
	}
	
	
	
    
    
}
