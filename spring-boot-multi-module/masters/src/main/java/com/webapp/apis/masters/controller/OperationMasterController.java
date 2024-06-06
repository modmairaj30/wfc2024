package com.webapp.apis.masters.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.apis.masters.domain.OperationMaster;
import com.webapp.apis.masters.service.OperationMasterService;
import com.webapp.apis.utility.ResponseWrapper;

@RestController
@RequestMapping("/master")
public class OperationMasterController {
	
	@Inject
	OperationMasterService operationMasterService;
	
	@RequestMapping(value = "/getAllOperations", method = RequestMethod.GET)
	public ResponseWrapper<List<OperationMaster>> getAllOperation() {
		List<OperationMaster> operations = operationMasterService.getAllOperations();
		return new ResponseWrapper<List<OperationMaster>>(null, HttpStatus.OK, "", operations);
	}

}
