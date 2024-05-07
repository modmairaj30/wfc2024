package com.webapp.apis.masters.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author mohammed.mirajuddin
 */

public class CreditTeriffmasterDetailFormBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private  Integer baseId;
    private String locationId;
    private BigDecimal rate;
    private BigDecimal addrate;
    private BigDecimal addqty;
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
	public Integer getBaseId() {
		return baseId;
	}
	public void setBaseId(Integer baseId) {
		this.baseId = baseId;
	}
    
    
        
}
