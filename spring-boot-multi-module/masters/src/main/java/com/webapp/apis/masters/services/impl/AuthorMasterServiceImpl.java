package com.webapp.apis.masters.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.apis.masters.constants.MastersConstants;
import com.webapp.apis.masters.domain.AuthorMaster;
import com.webapp.apis.masters.model.AuthorMasterFormBean;
import com.webapp.apis.masters.model.SearchCriteriaFormBean;
import com.webapp.apis.masters.model.ServerSidePagination;
import com.webapp.apis.masters.repository.AuthorMasterRepository;
import com.webapp.apis.masters.repository.specs.AuthorMasterSpecification;
import com.webapp.apis.masters.repository.specs.SearchCriteria;
import com.webapp.apis.masters.repository.specs.SearchOperation;
import com.webapp.apis.masters.service.AuthorMasterService;
import com.webapp.apis.utility.SessionUtility;

@Service
public class AuthorMasterServiceImpl implements AuthorMasterService {
	@Inject
	AuthorMasterRepository authorMasterRepository;

	@Inject
	private SessionUtility sessionUtility;

	@Transactional
	@Override
	public String saveAuthorMaster(AuthorMasterFormBean authorMasterFormBean) {
		AuthorMaster branchMaster = new AuthorMaster();

		BeanUtils.copyProperties(authorMasterFormBean, branchMaster);
		branchMaster.setAuthorName(authorMasterFormBean.getAuthorName().toUpperCase());
		
		if (authorMasterFormBean.getId() == null) {
			branchMaster.setCreatedBy(sessionUtility.getUserSession().getUsername());
			branchMaster.setCreatedDate(new Date());
		} else {
			branchMaster.setUpdatedBy(sessionUtility.getUserSession().getUsername());
			branchMaster.setUpdatedDate(new Date());
		}
		branchMaster = authorMasterRepository.save(branchMaster);
		if (branchMaster != null) {
			return MastersConstants.SAVE;
		}
		return MastersConstants.FAIL;
	}

	@Override
	public List<AuthorMasterFormBean> getAllAuthor() {
		List<AuthorMaster> listauthorMasters = authorMasterRepository.findAllByOrderByIdDesc();
		List<AuthorMasterFormBean> listauthorMaster = new ArrayList<AuthorMasterFormBean>();
		for (AuthorMaster authorMaster : listauthorMasters) {
			AuthorMasterFormBean authorMasterFormBean = new AuthorMasterFormBean();
			BeanUtils.copyProperties(authorMaster, authorMasterFormBean);
			listauthorMaster.add(authorMasterFormBean);
		}

		return listauthorMaster;

	}

	@Override
	public List<AuthorMasterFormBean> findByWhereCondition(SearchCriteriaFormBean searchCriteriaFormBean) {
		
		AuthorMasterSpecification msTitleRating = new AuthorMasterSpecification();
        msTitleRating.add(new SearchCriteria(searchCriteriaFormBean.getKey(),searchCriteriaFormBean.getValue(), SearchOperation.valueOf(searchCriteriaFormBean.getOperation())));
       // msTitleRating.add(new SearchCriteria("rating", 7, SearchOperation.GREATER_THAN));
        List<AuthorMaster> listauthorMasters = authorMasterRepository.findAll(msTitleRating);
    	List<AuthorMasterFormBean> listauthorMaster = new ArrayList<AuthorMasterFormBean>();
        for (AuthorMaster authorMaster : listauthorMasters) {
			AuthorMasterFormBean authorMasterFormBean = new AuthorMasterFormBean();
			BeanUtils.copyProperties(authorMaster, authorMasterFormBean);
			listauthorMaster.add(authorMasterFormBean);
		}
	
	

        return listauthorMaster;

	}

	@Override
	public List<AuthorMasterFormBean> getAllAuthorMasters(ServerSidePagination serverSidePagination) {
		// TODO Auto-generated method stub
		return null;
	}

}
