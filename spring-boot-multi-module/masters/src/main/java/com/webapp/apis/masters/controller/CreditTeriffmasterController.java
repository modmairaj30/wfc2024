package com.webapp.apis.masters.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.apis.masters.domain.CreditTeriffmaster;
import com.webapp.apis.masters.model.CreditTeriffmasterFormBean;
import com.webapp.apis.masters.model.SearchCriteriaFormBean;
import com.webapp.apis.masters.model.ServerSidePagination;
import com.webapp.apis.masters.service.CreditTeriffmasterService;
import com.webapp.apis.utility.ResponseWrapper;

@RestController
@RequestMapping(value = "/masters")
public class CreditTeriffmasterController {

	@Inject
	private CreditTeriffmasterService creditTeriffmasterService;

	// get all getAllCreditTeriffmaster new throw
	@RequestMapping(value = "/getAllCreditTeriffmaster", method = RequestMethod.GET)
	public ResponseWrapper<List<CreditTeriffmasterFormBean>> getAllCreditTeriffmaster(@RequestParam("page")Integer page, @RequestParam("size")Integer size) {
		ServerSidePagination serverSidePagination = new ServerSidePagination();
		serverSidePagination.setPage(page);
		serverSidePagination.setSize(size);
		List<CreditTeriffmasterFormBean> forms = creditTeriffmasterService.getAllCreditTeriffmaster(serverSidePagination);
		return new ResponseWrapper<List<CreditTeriffmasterFormBean>>(null, HttpStatus.OK, "", forms);

	}

	@RequestMapping(value = "/saveCreditTeriffmaster", method = RequestMethod.POST)
	public ResponseWrapper<String> saveCreditTeriffmaster(
			@RequestBody CreditTeriffmasterFormBean creditTeriffmasterFormBean) {
		String status = creditTeriffmasterService.saveCreditTeriffmaster(creditTeriffmasterFormBean);
		return new ResponseWrapper<String>(null, HttpStatus.OK, "", status);

	}
	
	@RequestMapping(value = "/findCreditTrafficMaster", method = RequestMethod.POST)
	public ResponseWrapper<List<CreditTeriffmasterFormBean>> findCreditTrafficMaster(@RequestBody SearchCriteriaFormBean searchCriteriaFormBean) {
		List<CreditTeriffmasterFormBean> forms = creditTeriffmasterService.findByWhereCondition(searchCriteriaFormBean);
		return new ResponseWrapper<List<CreditTeriffmasterFormBean>>(null, HttpStatus.OK, "", forms);
		}
	
	@RequestMapping(value = "/conditionSlabs", method = RequestMethod.POST)
	public ResponseWrapper<String> conditionSlabs(
			@RequestBody CreditTeriffmasterFormBean creditTeriffmasterFormBean) {
		String status = creditTeriffmasterService.conditionSlabs(creditTeriffmasterFormBean);
		return new ResponseWrapper<String>(null, HttpStatus.OK, "", status);

	}
	
}
