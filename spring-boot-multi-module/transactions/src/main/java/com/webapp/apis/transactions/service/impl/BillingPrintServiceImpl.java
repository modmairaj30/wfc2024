package com.webapp.apis.transactions.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.apis.transactions.domain.BillingPrint;
import com.webapp.apis.transactions.mapper.BillingPrintMapper;
import com.webapp.apis.transactions.model.BillingPrintFormBean;
import com.webapp.apis.transactions.model.ServerSidePagination;
import com.webapp.apis.transactions.repository.BillingPrintRepository;
import com.webapp.apis.transactions.service.BillingPrintService;

@Service
public class BillingPrintServiceImpl implements BillingPrintService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BillingPrintServiceImpl.class);

	@Inject
	private BillingPrintRepository BillingPrintRepository;

	@Override
	public List<BillingPrintFormBean> getAllBillingPrints(ServerSidePagination serverSidePagination) {
		// TODO Auto-generated method stub
		Page<BillingPrint> BillingPrints = null;
	//	Pageable requestedPage = PageRequest.of(serverSidePagination.getPage(), serverSidePagination.getSize(), Sort.by("id").descending());
		Pageable requestedPage = PageRequest.of(serverSidePagination.getPage(), serverSidePagination.getSize());
		BillingPrints = BillingPrintRepository.findAllByOrderByIdDesc(requestedPage);
		//List<BillingPrintFormBean> listBillingPrint = new ArrayList<BillingPrintFormBean>();
		/*
		for (BillingPrint BillingPrint : BillingPrints.getContent()) {
			BillingPrintFormBean BillingPrintFormBean = new BillingPrintFormBean();
			//BeanUtils.copyProperties(BillingPrint, BillingPrintFormBean);
			BillingPrintFormBean=	BillingPrintMapper.mapDomainToForm(BillingPrint);
			listBillingPrint.add(BillingPrintFormBean);
		}
    */
		return BillingPrintMapper.mapDomainListToFormList(BillingPrints.getContent());
	}

	
	
	@Transactional
	@Override
	public String saveBillingPrint(BillingPrintFormBean BillingPrintFormBean) {
		BillingPrint obj = BillingPrintRepository.save(BillingPrintMapper.mapFormToDomain(BillingPrintFormBean));
		return "saved";
	}

	
	

}
