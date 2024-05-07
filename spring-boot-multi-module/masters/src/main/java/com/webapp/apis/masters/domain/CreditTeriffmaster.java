package com.webapp.apis.masters.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
@Table(name = "credit_teriffmaster")
public class CreditTeriffmaster implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "from_weight")
    private BigDecimal fromWeight;
    @Column(name = "to_weight")
    private BigDecimal toWeight;
    @Column(name = "party_id")
    private Integer partyId;
    @Column(name = "payment_type")
    private Integer paymentType;
    @Column(name = "mode_consign")
    private Integer modeConsign;
    @Column(name = "doc_dt")
    @Temporal(TemporalType.DATE)
    private Date docDt;
    @Column(name = "doc_time")
    @Temporal(TemporalType.TIME)
    private Date docTime;

    
    @Column(name = "weight")
    private String weight;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "location_id")
    private String locationId;
    
    @Column(name = "user_id")
    private String userId;
    
    @Column(name = "state")
    private String state;
    
    

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "base_id", referencedColumnName = "id")
	private List<CreditTeriffmasterDetail> creditTeriffmasterDetailList;
    
    public CreditTeriffmaster() {
    }

    public CreditTeriffmaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public BigDecimal getFromWeight() {
        return fromWeight;
    }

    public void setFromWeight(BigDecimal fromWeight) {
        this.fromWeight = fromWeight;
    }

    public BigDecimal getToWeight() {
        return toWeight;
    }

    public void setToWeight(BigDecimal toWeight) {
        this.toWeight = toWeight;
    }

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getModeConsign() {
        return modeConsign;
    }

    public void setModeConsign(Integer modeConsign) {
        this.modeConsign = modeConsign;
    }

    public Date getDocDt() {
        return docDt;
    }

    public void setDocDt(Date docDt) {
        this.docDt = docDt;
    }

    public Date getDocTime() {
        return docTime;
    }

    public void setDocTime(Date docTime) {
        this.docTime = docTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
        if (!(object instanceof CreditTeriffmaster)) {
            return false;
        }
        CreditTeriffmaster other = (CreditTeriffmaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CreditTeriffmaster[ id=" + id + " ]";
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

	public List<CreditTeriffmasterDetail> getCreditTeriffmasterDetailList() {
		return creditTeriffmasterDetailList;
	}

	public void setCreditTeriffmasterDetailList(List<CreditTeriffmasterDetail> creditTeriffmasterDetailList) {
		this.creditTeriffmasterDetailList = creditTeriffmasterDetailList;
	}

	        
}
