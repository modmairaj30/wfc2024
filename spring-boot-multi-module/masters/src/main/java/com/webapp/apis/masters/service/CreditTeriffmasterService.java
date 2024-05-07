package com.webapp.apis.masters.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webapp.apis.masters.domain.CreditTeriffmaster;
import com.webapp.apis.masters.model.CreditTeriffmasterFormBean;
import com.webapp.apis.masters.model.SearchCriteriaFormBean;
import com.webapp.apis.masters.model.ServerSidePagination;

@Service
public interface CreditTeriffmasterService {

	//public List<CreditTeriffmasterFormBean> getAllCreditTeriffmaster();

	public String saveCreditTeriffmaster(CreditTeriffmasterFormBean creditTeriffmasterFormBean);
	List<CreditTeriffmasterFormBean> getAllCreditTeriffmaster(ServerSidePagination serverSidePagination);
	List<CreditTeriffmasterFormBean> findByWhereCondition(SearchCriteriaFormBean searchCriteriaFormBean);
	String conditionSlabs(CreditTeriffmasterFormBean creditTeriffmasterFormBean);

}
