package com.webapp.apis.masters.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webapp.apis.masters.domain.OperationMaster;

@Service
public interface OperationMasterService {
	
	List<OperationMaster> getAllOperations();
	
}
