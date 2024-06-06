package com.webapp.apis.masters.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.webapp.apis.masters.domain.OperationMaster;
import com.webapp.apis.masters.repository.OperationMasterRepository;
import com.webapp.apis.masters.service.OperationMasterService;

@Service
public class OperationMasterServiceImpl implements  OperationMasterService{

	@Inject
	OperationMasterRepository operationMasterRepository;
	
	public List<OperationMaster> getAllOperations() {
		List<OperationMaster> OperationMasterlist=operationMasterRepository.findAllByOrderByIdAsc();
		return OperationMasterlist;
	}

}
