package com.webapp.apis.transactions.model;
	import java.io.Serializable;
	import java.math.BigDecimal;
	
	public class BillingPrintDetailFormBean implements Serializable {
	    private static final long serialVersionUID = 1L;
	    private Integer id;
	    private String docDt;
	    private BigDecimal netAmt;
	    private String batchNo2;
	    private String consigneeDest;
	    private String sourceStn;
	    private String tariffId;
	    private BigDecimal decVal;
	    private BigDecimal decamt;
	    private BigDecimal weight;
	    private BigDecimal amount;
	    private Integer baseId;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getDocDt() {
			return docDt;
		}
		public void setDocDt(String docDt) {
			this.docDt = docDt;
		}
		public BigDecimal getNetAmt() {
			return netAmt;
		}
		public void setNetAmt(BigDecimal netAmt) {
			this.netAmt = netAmt;
		}
		public String getBatchNo2() {
			return batchNo2;
		}
		public void setBatchNo2(String batchNo2) {
			this.batchNo2 = batchNo2;
		}
		public String getConsigneeDest() {
			return consigneeDest;
		}
		public void setConsigneeDest(String consigneeDest) {
			this.consigneeDest = consigneeDest;
		}
		public String getSourceStn() {
			return sourceStn;
		}
		public void setSourceStn(String sourceStn) {
			this.sourceStn = sourceStn;
		}
		public String getTariffId() {
			return tariffId;
		}
		public void setTariffId(String tariffId) {
			this.tariffId = tariffId;
		}
		public BigDecimal getDecVal() {
			return decVal;
		}
		public void setDecVal(BigDecimal decVal) {
			this.decVal = decVal;
		}
		public BigDecimal getDecamt() {
			return decamt;
		}
		public void setDecamt(BigDecimal decamt) {
			this.decamt = decamt;
		}
		public BigDecimal getWeight() {
			return weight;
		}
		public void setWeight(BigDecimal weight) {
			this.weight = weight;
		}
		public BigDecimal getAmount() {
			return amount;
		}
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
		public Integer getBaseId() {
			return baseId;
		}
		public void setBaseId(Integer baseId) {
			this.baseId = baseId;
		}
	    
	    


}
