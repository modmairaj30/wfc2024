package com.webapp.apis.masters.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.apis.masters.domain.BranchMaster;
import com.webapp.apis.masters.domain.Destination;
import com.webapp.apis.exceptions.WebAppError;
import com.webapp.apis.exceptions.WebAppException;
import com.webapp.apis.masters.model.BranchMasterFormBean;
import com.webapp.apis.masters.model.DestinationFormBean;
import com.webapp.apis.masters.repository.BranchMasterRepository;
import com.webapp.apis.masters.repository.DestinationRepository;
import com.webapp.apis.masters.service.BranchMasterService;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static com.webapp.apis.masters.constants.MastersConstants.MASTERS;
import static com.webapp.apis.masters.constants.MastersConstants.DB_ERROR;
import static com.webapp.apis.masters.constants.MastersConstants.SAVE_FAILURE;

@Service
public class BranchMasterServiceImpl implements BranchMasterService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BranchMasterServiceImpl.class);

	@Inject
	private DestinationRepository destinationRepository;

	@Inject
	private BranchMasterRepository branchMasterRepository;

	@Override
	public List<BranchMasterFormBean> getAllBranchMasters() {
		List<BranchMaster> listbranchMaster = branchMasterRepository.findAllByOrderByIdDesc();
		List<BranchMasterFormBean> listBranchMaster = new ArrayList<BranchMasterFormBean>();
		for (BranchMaster branchMaster : listbranchMaster) {
			BranchMasterFormBean branchMasterFormBean = new BranchMasterFormBean();
			BeanUtils.copyProperties(branchMaster, branchMasterFormBean);
			listBranchMaster.add(branchMasterFormBean);
		}

		return listBranchMaster;
	}

	@Transactional
	@Override
	public BranchMasterFormBean saveBranchMaster(BranchMasterFormBean branchMasterFormBean) {
		List<WebAppError> webAppError = new ArrayList<>();
		
		try {
			BranchMaster branchMaster = new BranchMaster();
		//	webAppError.add(new WebAppError(MASTERS, DB_ERROR,SAVE_FAILURE));
			BeanUtils.copyProperties(branchMasterFormBean, branchMaster);
			branchMaster = branchMasterRepository.save(branchMaster);
			//throw new WebAppException(INTERNAL_SERVER_ERROR.toString(), DB_ERROR,webAppError);
		} catch (Exception e) {
			
			webAppError.add(new WebAppError(MASTERS, DB_ERROR,SAVE_FAILURE));
			LOGGER.error(MASTERS+DB_ERROR+SAVE_FAILURE, e);
			if (!webAppError.isEmpty()) {
				throw new WebAppException(INTERNAL_SERVER_ERROR.toString(), DB_ERROR,webAppError);
			}
		}
		
		
		
		return branchMasterFormBean;
	}

	@Override
	public List<DestinationFormBean> getAllDestination() {

		List<Destination> listDestination = destinationRepository.findAllByOrderByIdDesc();
		List<DestinationFormBean> listDestinationForm = new ArrayList<DestinationFormBean>();
		for (Destination destination : listDestination) {
			DestinationFormBean destinationFormBean = new DestinationFormBean();
			BeanUtils.copyProperties(destination, destinationFormBean);
			listDestinationForm.add(destinationFormBean);
		}

		return listDestinationForm;
	}

	@Override
	public String saveDestination(DestinationFormBean destinationFormBean) {

		Destination destination = new Destination();
		BeanUtils.copyProperties(destinationFormBean, destination);
		destination = destinationRepository.save(destination);
		return "Saved Successfully";
	}

}
