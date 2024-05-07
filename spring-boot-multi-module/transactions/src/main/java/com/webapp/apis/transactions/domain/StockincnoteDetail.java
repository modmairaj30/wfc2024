package com.webapp.apis.transactions.domain;


import java.io.Serializable;

import javax.persistence.Basic;
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
@Table(name = "stockincnote_detail")
public class StockincnoteDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "base_id")
    private Integer baseId;
    @Column(name = "product_id")
    private String productId;
    @Column(name = "qty")
    private Integer qty;
    @Column(name="cnote_no")
    private String cNoteNo;
    
    @Column(name = "s_no")
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
