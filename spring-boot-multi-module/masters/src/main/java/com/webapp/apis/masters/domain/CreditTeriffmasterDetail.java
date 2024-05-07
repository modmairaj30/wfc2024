package com.webapp.apis.masters.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author mohammed.mirajuddin
 */
@Entity
@Table(name = "credit_teriffmaster_detail")
public class CreditTeriffmasterDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "location_id")
	private String locationId;
	@Column(name = "rate")
	private BigDecimal rate;
	@Column(name = "Add_rate")
	private BigDecimal addrate;
	@Column(name = "Add_qty")
	private BigDecimal addqty;
	@JoinColumn(name = "base_id", referencedColumnName = "id")
	@ManyToOne
	private CreditTeriffmaster baseId;

	public CreditTeriffmasterDetail() {
	}

	public CreditTeriffmasterDetail(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getAddrate() {
		return addrate;
	}

	public void setAddrate(BigDecimal addrate) {
		this.addrate = addrate;
	}

	public BigDecimal getAddqty() {
		return addqty;
	}

	public void setAddqty(BigDecimal addqty) {
		this.addqty = addqty;
	}

	public CreditTeriffmaster getBaseId() {
		return baseId;
	}

	public void setBaseId(CreditTeriffmaster baseId) {
		this.baseId = baseId;
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
		if (!(object instanceof CreditTeriffmasterDetail)) {
			return false;
		}
		CreditTeriffmasterDetail other = (CreditTeriffmasterDetail) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CreditTeriffmasterDetail[ id=" + id + " ]";
	}

}
