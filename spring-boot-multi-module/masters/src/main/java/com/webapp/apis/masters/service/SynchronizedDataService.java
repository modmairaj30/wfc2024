package com.webapp.apis.masters.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.webapp.apis.masters.domain.SynchronizedData;
import com.webapp.apis.masters.model.SynchronizedDataFormBean;
;

@Service
public interface SynchronizedDataService {
	List<SynchronizedDataFormBean> getAllSynchronizedData();
	String saveSynchronizedData( SynchronizedDataFormBean synchronizedDataFormBean);
	Optional<SynchronizedData> getIdSynchronizedData(int n);
	String controlSlabs(SynchronizedDataFormBean synchronizedDataFormBean);
	
	String uploadTables(SynchronizedDataFormBean synchronizedDataFormBean);
	String downloadTables(SynchronizedDataFormBean synchronizedDataFormBean);

}
