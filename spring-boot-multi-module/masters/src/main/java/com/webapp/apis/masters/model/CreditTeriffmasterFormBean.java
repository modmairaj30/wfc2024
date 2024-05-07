package com.webapp.apis.masters.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mohammed.mirajuddin
 */
public class CreditTeriffmasterFormBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private BigDecimal fromWeight;
	private BigDecimal toWeight;
	private Integer partyId;
	private Integer paymentType;
	private Integer modeConsign;
	private Date docDt;
	private Date docTime;
	private String weight;
	private String type;
	private String locationId;
	private String userId;
	private List<String> locationIds;
	private String state;
	private List<CreditTeriffmasterDetailFormBean> creditTeriffmasterDetailFormBeanList;

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

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
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

	public List<CreditTeriffmasterDetailFormBean> getCreditTeriffmasterDetailFormBeanList() {
		return creditTeriffmasterDetailFormBeanList;
	}

	public void setCreditTeriffmasterDetailFormBeanList(
			List<CreditTeriffmasterDetailFormBean> creditTeriffmasterDetailFormBeanList) {
		this.creditTeriffmasterDetailFormBeanList = creditTeriffmasterDetailFormBeanList;
	}

	public List<String> getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(List<String> locationIds) {
		this.locationIds = locationIds;
	}

}
