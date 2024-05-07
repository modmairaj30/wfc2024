package com.webapp.apis.transactions.domain;

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
@Table(name = "billing_print_detail")
public class BillingPrintDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "doc_dt")
    private String docDt;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "net_amt")
    private BigDecimal netAmt;
    @Column(name = "batch_no2")
    private String batchNo2;
    @Column(name = "consignee_dest")
    private String consigneeDest;
    @Column(name = "source_stn")
    private String sourceStn;
    @Column(name = "tariff_id")
    private String tariffId;
    @Column(name = "dec_val")
    private BigDecimal decVal;
    @Column(name = "Dec_amt")
    private BigDecimal decamt;
    @Column(name = "weight")
    private BigDecimal weight;
    @Column(name = "amount")
    private BigDecimal amount;
    @JoinColumn(name = "base_id", referencedColumnName = "id")
    @ManyToOne
    private BillingPrint baseId;

    public BillingPrintDetail() {
    }

    public BillingPrintDetail(Integer id) {
        this.id = id;
    }

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

    public BillingPrint getBaseId() {
        return baseId;
    }

    public void setBaseId(BillingPrint baseId) {
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
        if (!(object instanceof BillingPrintDetail)) {
            return false;
        }
        BillingPrintDetail other = (BillingPrintDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.javaapplication1.BillingPrintDetail[ id=" + id + " ]";
    }
    
}
