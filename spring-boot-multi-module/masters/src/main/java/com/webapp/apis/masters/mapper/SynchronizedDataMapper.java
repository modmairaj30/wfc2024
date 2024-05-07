package com.webapp.apis.masters.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webapp.apis.masters.domain.RolesMaster;
import com.webapp.apis.masters.domain.SynchronizedData;
import com.webapp.apis.masters.model.RolesMasterFormBean;
import com.webapp.apis.masters.model.SynchronizedDataFormBean;

public class SynchronizedDataMapper {

	
private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizedDataMapper.class);
	
	public static SynchronizedData mapFormToDomain(SynchronizedDataFormBean synchronizedDataFormBean) {
		SynchronizedData  synchronizedData  = new SynchronizedData();
		
		synchronizedData.setId(synchronizedDataFormBean.getId());
		synchronizedData.setName(synchronizedDataFormBean.getName());
		synchronizedData.setCreatedBy(synchronizedDataFormBean.getCreatedBy());
		synchronizedData.setUpdatedBy(synchronizedDataFormBean.getUpdatedBy());
		synchronizedData.setCreatedDate(synchronizedDataFormBean.getCreatedDate());
		synchronizedData.setUpdatedDate(synchronizedDataFormBean.getUpdatedDate());
		synchronizedData.setUpload(synchronizedDataFormBean.getUpload());
		synchronizedData.setDownload(synchronizedDataFormBean.getDownload());
		synchronizedData.setUploadFlag(synchronizedDataFormBean.getUploadFlag());
		synchronizedData.setTblUploadDownload(synchronizedDataFormBean.getTblUploadDownload());
		return synchronizedData;
	}
	public static SynchronizedDataFormBean mapDomainToForm(SynchronizedData synchronizedData) {
		SynchronizedDataFormBean synchronizedDataFormBean = new SynchronizedDataFormBean();
				
		synchronizedDataFormBean.setId(synchronizedData.getId());
		synchronizedDataFormBean.setName(synchronizedData.getName());
		synchronizedDataFormBean.setCreatedBy(synchronizedData.getCreatedBy());
		synchronizedDataFormBean.setUpdatedBy(synchronizedData.getUpdatedBy());
		synchronizedDataFormBean.setCreatedDate(synchronizedData.getCreatedDate());
		synchronizedDataFormBean.setUpdatedDate(synchronizedData.getUpdatedDate());
		synchronizedDataFormBean.setUpload(synchronizedData.getUpload());
		synchronizedDataFormBean.setDownload(synchronizedData.getDownload());
		synchronizedDataFormBean.setUploadFlag(synchronizedData.getUploadFlag());
		synchronizedDataFormBean.setTblUploadDownload(synchronizedData.getTblUploadDownload());
	
		return synchronizedDataFormBean ;
	
	}
}
