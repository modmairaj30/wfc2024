package com.webapp.apis.masters.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.apis.exceptions.WebAppError;
import com.webapp.apis.exceptions.WebAppException;
import com.webapp.apis.masters.model.BranchMasterFormBean;
import com.webapp.apis.masters.model.DestinationFormBean;
import com.webapp.apis.masters.service.BranchMasterService;
import com.webapp.apis.utility.ResponseWrapper;


@RestController //ControllerAdvice
@RequestMapping(value = "/masters")
public class BranchMasterController {

	@Inject
	private BranchMasterService branchMasterService;

	// get all party's
	@RequestMapping(value = "/getAllBranches", method = RequestMethod.GET)
	public ResponseWrapper<List<BranchMasterFormBean>> getAllBranchMasters() {
		List<BranchMasterFormBean> forms = branchMasterService.getAllBranchMasters();
		return new ResponseWrapper<List<BranchMasterFormBean>>(null, HttpStatus.OK, "", forms);

	}

	@RequestMapping(value = "/saveBranchMaster", method = RequestMethod.POST)
	@ResponseBody
	public ResponseWrapper<BranchMasterFormBean> saveBranchMaster(@RequestBody BranchMasterFormBean branchMasterFormBean) {
		List<WebAppError> error = new ArrayList<>();
		WebAppException t= null;
		ResponseWrapper<BranchMasterFormBean>  r = new ResponseWrapper<>(t, HttpStatus.OK, "",branchMasterService.saveBranchMaster(branchMasterFormBean));
		return r;
	}

	// get all party's
	@RequestMapping(value = "/getAllDestinations", method = RequestMethod.GET)
	public ResponseWrapper<List<DestinationFormBean>> getAllDestinations() {
		List<DestinationFormBean> forms = branchMasterService.getAllDestination();
		return new ResponseWrapper<List<DestinationFormBean>>(null, HttpStatus.OK, "", forms);

	}

	@RequestMapping(value = "/saveDestinationMaster", method = RequestMethod.POST)
	public ResponseWrapper<String> saveDestinationMaster(@RequestBody DestinationFormBean destinationFormBean) {
		String flag = branchMasterService.saveDestination(destinationFormBean);
		return new ResponseWrapper<String>(null, HttpStatus.OK, "", flag);

	}

}
