package com.webapp.apis.transactions.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webapp.apis.transactions.model.BillingPrintFormBean;
import com.webapp.apis.transactions.model.ServerSidePagination;

@Service
public interface BillingPrintService {

	
	public String saveBillingPrint(BillingPrintFormBean billingPrintFormBean);

	List<BillingPrintFormBean> getAllBillingPrints(ServerSidePagination serverSidePagination);

}
