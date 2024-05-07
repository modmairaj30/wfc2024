package com.webapp.apis.transactions.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webapp.apis.transactions.domain.Invoice;
import com.webapp.apis.transactions.model.InvoiceFormBean;

public class InvoiceMapper {
	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceMapper.class);

	public static Invoice mapFormToDomain(InvoiceFormBean invoiceFormBean) {
		Invoice invoice = new Invoice();

		invoice.setId(invoiceFormBean.getId());
		invoice.setDocDt(invoiceFormBean.getDocDt());
		invoice.setDocNo(invoiceFormBean.getDocNo());
		// invoice.setLRno(invoiceFormBean.getlRno());
		// invoice.setLRdate(invoiceFormBean.getlRdate());
		invoice.setGrossAmt(invoiceFormBean.getGrossAmt());
		invoice.setNetAmt(invoiceFormBean.getNetAmt());
		invoice.setPartyId(invoiceFormBean.getPartyId());
		invoice.setAddress(invoiceFormBean.getAddress());
		invoice.setLocationId(invoiceFormBean.getLocationId());
		invoice.setLocationCode(invoiceFormBean.getLocationCode());
		//invoice.setDocketCharges(invoiceFormBean.getDocketCharges());
		invoice.setRefNo(invoiceFormBean.getRefNo());
		invoice.setTeriffLocation(invoiceFormBean.getTeriffLocation());
		invoice.setCgst(invoiceFormBean.getCgst());
		invoice.setOrginFld(invoiceFormBean.getOrginFld());
		invoice.setTariffId(invoiceFormBean.getTariffId());
		invoice.setConsignerMobNo(invoiceFormBean.getConsignerMobNo());
		invoice.setType(invoiceFormBean.getType());
		invoice.setNoOfPieces(invoiceFormBean.getNoOfPieces());
		invoice.setIgst(invoiceFormBean.getIgst());
		invoice.setConsignerEmailId(invoiceFormBean.getConsignerEmailId());
		invoice.setPrefix(invoiceFormBean.getPrefix());
		invoice.setWeight(invoiceFormBean.getWeight());
		invoice.setCgstAmt(invoiceFormBean.getCgstAmt());
		invoice.setConsigneeadd(invoiceFormBean.getConsigneeadd());
		invoice.setInvoiceNo(invoiceFormBean.getInvoiceNo());
		invoice.setSgstAmt(invoiceFormBean.getSgstAmt());
		invoice.setConsigneeMobNo(invoiceFormBean.getConsigneeMobNo());
		invoice.setDecVal(invoiceFormBean.getDecVal());
		invoice.setIgstAmt(invoiceFormBean.getIgstAmt());
		invoice.setStatus(invoiceFormBean.getStatus());
		invoice.setConsigneeDest(invoiceFormBean.getConsigneeDest());
		invoice.setDecamt(invoiceFormBean.getDecamt());
		invoice.setFuelTax(invoiceFormBean.getFuelTax());
		invoice.setUserId(invoiceFormBean.getUserId());
		invoice.setBranchForwarding(invoiceFormBean.getBranchForwarding());
		invoice.setOdCharges(invoiceFormBean.getOdCharges());
		invoice.setFuelAmt(invoiceFormBean.getFuelAmt());
		invoice.setDestBranch(invoiceFormBean.getDestBranch());
		invoice.setAmount(invoiceFormBean.getAmount());
		invoice.setRptNetamt(invoiceFormBean.getRptNetamt());
		invoice.setCardType(invoiceFormBean.getCardType());
		invoice.setDecPer(invoiceFormBean.getDecPer());
		invoice.setCustName(invoiceFormBean.getCustName());
		invoice.setConsigneename(invoiceFormBean.getConsigneename());
		invoice.setPartyLabel(invoiceFormBean.getPartyLabel());
		invoice.setCardType(invoiceFormBean.getCardType());
		invoice.setRefNo(invoiceFormBean.getRefNo());
		invoice.setUpload(invoiceFormBean.getUpload());
		invoice.setBatchNo(invoiceFormBean.getBatchNo());
		invoice.setBatchNo2(invoiceFormBean.getBatchNo2());
		invoice.setInvoiceNo(invoiceFormBean.getInvoiceNo());
	//	invoice.setUploadTime(invoiceFormBean.getUploadTime());
		invoice.setStockId(invoiceFormBean.getStockId());
		return invoice;

	}

	public static InvoiceFormBean mapDomainToForm(Invoice invoice) {
		InvoiceFormBean invoiceFormBean = new InvoiceFormBean();
		
		invoiceFormBean.setId(invoice.getId());
		invoiceFormBean.setDocDt(invoice.getDocDt());
		invoiceFormBean.setDocNo(invoice.getDocNo());
		// FormBean invoice.setLRno(invoice.getlRno());
		// FormBean invoice.setLRdate(invoice.getlRdate());
		invoiceFormBean.setGrossAmt(invoice.getGrossAmt());
		invoiceFormBean.setNetAmt(invoice.getNetAmt());
		invoiceFormBean.setPartyId(invoice.getPartyId());
		invoiceFormBean.setAddress(invoice.getAddress());
		invoiceFormBean.setLocationId(invoice.getLocationId());
		invoiceFormBean.setLocationCode(invoice.getLocationCode());
		invoiceFormBean.setDocketCharges(invoice.getDocketCharges());
		invoiceFormBean.setRefNo(invoice.getRefNo());
		invoiceFormBean.setTeriffLocation(invoice.getTeriffLocation());
		invoiceFormBean.setCgst(invoice.getCgst());
		invoiceFormBean.setOrginFld(invoice.getOrginFld());
		invoiceFormBean.setTariffId(invoice.getTariffId());
		invoiceFormBean.setConsignerMobNo(invoice.getConsignerMobNo());
		invoiceFormBean.setType(invoice.getType());
		invoiceFormBean.setNoOfPieces(invoice.getNoOfPieces());
		invoiceFormBean.setIgst(invoice.getIgst());
		invoiceFormBean.setConsignerEmailId(invoice.getConsignerEmailId());
		invoiceFormBean.setPrefix(invoice.getPrefix());
		invoiceFormBean.setWeight(invoice.getWeight());
		invoiceFormBean.setCgstAmt(invoice.getCgstAmt());
		invoiceFormBean.setConsigneeadd(invoice.getConsigneeadd());
		invoiceFormBean.setInvoiceNo(invoice.getInvoiceNo());
		invoiceFormBean.setSgstAmt(invoice.getSgstAmt());
		invoiceFormBean.setConsigneeMobNo(invoice.getConsigneeMobNo());
		invoiceFormBean.setDecVal(invoice.getDecVal());
		invoiceFormBean.setIgstAmt(invoice.getIgstAmt());
		invoiceFormBean.setStatus(invoice.getStatus());
		invoiceFormBean.setConsigneeDest(invoice.getConsigneeDest());
		invoiceFormBean.setDecamt(invoice.getDecamt());
		invoiceFormBean.setFuelTax(invoice.getFuelTax());
		invoiceFormBean.setUserId(invoice.getUserId());
		invoiceFormBean.setBranchForwarding(invoice.getBranchForwarding());
		invoiceFormBean.setOdCharges(invoice.getOdCharges());
		invoiceFormBean.setFuelAmt(invoice.getFuelAmt());
		invoiceFormBean.setDestBranch(invoice.getDestBranch());
		invoiceFormBean.setAmount(invoice.getAmount());
		invoiceFormBean.setRptNetamt(invoice.getRptNetamt());
		invoiceFormBean.setCardType(invoice.getCardType());
		invoiceFormBean.setDecPer(invoice.getDecPer());
		invoiceFormBean.setCustName(invoice.getCustName());
		invoiceFormBean.setConsigneename(invoice.getConsigneename());
		invoiceFormBean.setPartyLabel(invoice.getPartyLabel());
		invoiceFormBean.setCardType(invoice.getCardType());
		invoiceFormBean.setRefNo(invoice.getRefNo());
		invoiceFormBean.setUpload(invoice.getUpload());
		invoiceFormBean.setInvoiceNo(invoice.getInvoiceNo());
		//invoiceFormBean.setUploadTime(invoice.getUploadTime());
		invoiceFormBean.setStockId(invoice.getStockId());
		return invoiceFormBean;

	}

	public static List<InvoiceFormBean> mapDomainListToFormList(List<Invoice> InvoiceList) {
		List<InvoiceFormBean> InvoiceFormBeanList = new ArrayList<>();
		for (Invoice Invoice : InvoiceList) {
			InvoiceFormBeanList.add(mapDomainToForm(Invoice));
		}
		return InvoiceFormBeanList;

	}
}
