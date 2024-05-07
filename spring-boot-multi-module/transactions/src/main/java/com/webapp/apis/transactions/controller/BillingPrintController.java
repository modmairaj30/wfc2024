package com.webapp.apis.transactions.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.apis.transactions.model.BillingPrintFormBean;
import com.webapp.apis.transactions.model.ServerSidePagination;
import com.webapp.apis.transactions.service.BillingPrintService;
import com.webapp.apis.transactions.utility.ResponseWrapper;

@RestController
@RequestMapping(value = "/transaction")
public class BillingPrintController {

	@Inject
	private BillingPrintService BillingPrintService;

	// get all party's
	@RequestMapping(value = "/getAllBillingPrint", method = RequestMethod.GET)
	public ResponseWrapper<List<BillingPrintFormBean>> getAllBillingPrints(@RequestParam("page")Integer page, @RequestParam("size")Integer size) {
		ServerSidePagination serverSidePagination = new ServerSidePagination();
		serverSidePagination.setPage(page);
		serverSidePagination.setSize(size);
		List<BillingPrintFormBean> forms = BillingPrintService.getAllBillingPrints(serverSidePagination);
		return new ResponseWrapper<List<BillingPrintFormBean>>(null, HttpStatus.OK, "", forms);

	}

	@RequestMapping(value = "/saveBillingPrint", method = RequestMethod.POST)
	public ResponseWrapper<String> saveBillingPrint(@RequestBody BillingPrintFormBean billingPrintFormBean) {
		String flag = BillingPrintService.saveBillingPrint(billingPrintFormBean);
		return new ResponseWrapper<String>(null, HttpStatus.OK, "", flag);

	}

}
