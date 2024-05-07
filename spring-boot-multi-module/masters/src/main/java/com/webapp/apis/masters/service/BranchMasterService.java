package com.webapp.apis.masters.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webapp.apis.masters.model.BranchMasterFormBean;
import com.webapp.apis.masters.model.DestinationFormBean;

@Service
public interface BranchMasterService {

	public List<BranchMasterFormBean> getAllBranchMasters();

	public BranchMasterFormBean saveBranchMaster(BranchMasterFormBean brachMasterFormBean);

	public List<DestinationFormBean> getAllDestination();

	public String saveDestination(DestinationFormBean destinationFormBean);

}
