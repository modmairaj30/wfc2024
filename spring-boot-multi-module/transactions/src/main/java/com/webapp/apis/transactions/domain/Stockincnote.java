package com.webapp.apis.transactions.domain;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mohammed.mirajuddin
 */
@Entity
@Table(name = "stockincnote")
public class Stockincnote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "doc_dt")
    @Temporal(TemporalType.DATE)
    private Date docDt;
    @Column(name = "doc_no")
    private String docNo;
    @Column(name = "uid")
    private String uid;
    @Column(name = "location_id")
    private Integer locationId;
    @Column(name = "c_prefix")
    private String cPrefix;
    @Column(name = "From_No")
    private String fromNo;
    @Column(name = "To_No")
    private String toNo;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_by")
	private String updatedBy;
    @Column(name = "created_date")
	private Date createdDate;
    @Column(name = "updated_date")
	private Date updatedDate;
   
    @OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "base_id", referencedColumnName = "id")
    private List<StockincnoteDetail> stockincnoteDetailList;

    public Stockincnote() {
    }

    public Stockincnote(Integer id) {
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

    public String getCPrefix() {
        return cPrefix;
    }

    public void setCPrefix(String cPrefix) {
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

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stockincnote)) {
            return false;
        }
        Stockincnote other = (Stockincnote) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Stockincnote[ id=" + id + " ]";
    }
    

	public void setStockincnoteDetailList(List<StockincnoteDetail> stockInCNoteDetailList) {
		this.stockincnoteDetailList = stockInCNoteDetailList;
		
	}

	public List<StockincnoteDetail> getStockincnoteDetailList() {
		
		return stockincnoteDetailList;
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
