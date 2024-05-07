package com.webapp.apis.masters.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webapp.apis.masters.model.AuthorMasterFormBean;
import com.webapp.apis.masters.model.SearchCriteriaFormBean;
import com.webapp.apis.masters.model.ServerSidePagination;

@Service
public interface AuthorMasterService {

	String saveAuthorMaster(AuthorMasterFormBean authorMasterFormBean);
	List<AuthorMasterFormBean> findByWhereCondition(SearchCriteriaFormBean searchCriteriaFormBean);
	List<AuthorMasterFormBean> getAllAuthorMasters(ServerSidePagination serverSidePagination);
	List<AuthorMasterFormBean> getAllAuthor();

}
