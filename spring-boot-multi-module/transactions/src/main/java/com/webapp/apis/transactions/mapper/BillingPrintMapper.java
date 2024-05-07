package com.webapp.apis.transactions.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.webapp.apis.transactions.domain.BillingPrint;
import com.webapp.apis.transactions.domain.BillingPrintDetail;
import com.webapp.apis.transactions.model.BillingPrintDetailFormBean;
import com.webapp.apis.transactions.model.BillingPrintFormBean;



public class BillingPrintMapper {
	
	
private static final Logger LOGGER = LoggerFactory.getLogger(BillingPrintMapper.class);
	
	public static BillingPrint mapFormToDomain(BillingPrintFormBean billingPrintFormBean) {
		BillingPrint  billingPrint = new BillingPrint();
		List<BillingPrintDetail> detailDomainList = new ArrayList<>();
		BeanUtils.copyProperties(billingPrint, billingPrintFormBean);
		
		return billingPrint;
	}

	public static BillingPrintFormBean mapDomainToForm(BillingPrint billingPrint) {
		BillingPrintFormBean billingPrintFormBean = new BillingPrintFormBean();
		List<BillingPrintDetailFormBean> detailFormList = new ArrayList<>();
		BeanUtils.copyProperties(billingPrintFormBean, billingPrint);
				return billingPrintFormBean;
		
	}

	public static List<BillingPrintFormBean> mapDomainListToFormList(List<BillingPrint> billingPrintList) {
		List<BillingPrintFormBean> billingPrintFormBeanList = new ArrayList<>();
		for (BillingPrint billingPrint : billingPrintList) {
			billingPrintFormBeanList.add(mapDomainToForm(billingPrint));
		}
		return billingPrintFormBeanList;

	}
	
	public static List<BillingPrint> mapFormListToDomainList(List<BillingPrintFormBean> billingPrintFormBeanList) {
		List<BillingPrint> billingPrintList = new ArrayList<>();
		for (BillingPrintFormBean billingPrintFormBean : billingPrintFormBeanList) {
			billingPrintList.add(mapFormToDomain(billingPrintFormBean));
		}
		return billingPrintList;
	}


}
