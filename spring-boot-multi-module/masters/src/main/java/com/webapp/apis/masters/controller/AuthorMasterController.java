package com.webapp.apis.masters.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.apis.exceptions.WebAppError;
import com.webapp.apis.exceptions.WebAppException;
import com.webapp.apis.masters.constants.MastersConstants;
import com.webapp.apis.masters.model.AuthorMasterFormBean;
import com.webapp.apis.masters.model.SearchCriteriaFormBean;
import com.webapp.apis.masters.service.AuthorMasterService;
import com.webapp.apis.utility.ResponseWrapper;

@RestController
@RequestMapping(value = "/masters")

public class AuthorMasterController {

	@Inject
	AuthorMasterService authorMasterService;

	@RequestMapping(value = "/saveAuthorMaster", method = RequestMethod.POST)
	public ResponseWrapper<String> saveAuthorMaster( @RequestBody AuthorMasterFormBean authorMasterFormBean) {
		String flag = authorMasterService.saveAuthorMaster(authorMasterFormBean);
		List<WebAppError> error = new ArrayList<>();
		WebAppException t = null;
		if (flag != MastersConstants.SAVE) {
			error.add(new WebAppError(MastersConstants.SAVE,MastersConstants.DB_ERROR,MastersConstants.SAVE_FAILURE));
			throw new WebAppException(HttpStatus.BAD_REQUEST.toString(),MastersConstants.SAVE_FAILURE,error);
		}
		return new ResponseWrapper<String>(t, HttpStatus.OK, "", flag);
	}

	@RequestMapping(value = "/getAllAuthor", method = RequestMethod.GET)
	public ResponseWrapper<List<AuthorMasterFormBean>> getAllAuthor() {
		List<AuthorMasterFormBean> forms = authorMasterService.getAllAuthor();
		return new ResponseWrapper<List<AuthorMasterFormBean>>(null, HttpStatus.OK, "", forms);

	}
	
	


@RequestMapping(value = "/findAuthorMaster", method = RequestMethod.POST)
public ResponseWrapper<List<AuthorMasterFormBean>> findPartyMaster(@RequestBody SearchCriteriaFormBean searchCriteriaFormBean) {
	List<AuthorMasterFormBean> forms = authorMasterService.findByWhereCondition(searchCriteriaFormBean);
	return new ResponseWrapper<List<AuthorMasterFormBean>>(null, HttpStatus.OK, "", forms);
	}
}