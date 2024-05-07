package com.webapp.apis.transactions.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.apis.domian.TblAutoNumber;
import com.webapp.apis.repository.TblAutoNumberRepository;
import com.webapp.apis.transaction.constants.TransactionConstants;
import com.webapp.apis.transactions.domain.Invoice;
import com.webapp.apis.transactions.domain.StockInfo;
import com.webapp.apis.transactions.mapper.InvoiceMapper;
import com.webapp.apis.transactions.model.InvoiceFormBean;
import com.webapp.apis.transactions.model.SearchCriteriaFormBean;
import com.webapp.apis.transactions.model.ServerSidePagination;
import com.webapp.apis.transactions.repository.InvoiceRepository;
import com.webapp.apis.transactions.repository.StockInfoRepository;
import com.webapp.apis.transactions.repository.specs.InvoiceSpecification;
import com.webapp.apis.transactions.repository.specs.SearchCriteria;
import com.webapp.apis.transactions.repository.specs.SearchOperation;
import com.webapp.apis.transactions.service.InvoiceService;
import com.webapp.apis.transactions.utility.StockUpdate;
import com.webapp.apis.utility.SessionUtility;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceServiceImpl.class);
	
	@Inject
	private StockInfoRepository stockInfoRepository;
	@Inject
	private TblAutoNumberRepository tblAutoNumberRepository;
	
	@Inject
	private InvoiceRepository invoiceRepository;
	
	@Inject
	private SessionUtility sessionUtility;
	
	@Inject
	private StockUpdate stockUpdate;
	
	@Override
	public List<InvoiceFormBean> getAllInvoice(){
		List<Invoice> listCreditTeriffmaster = invoiceRepository.findAllByOrderByIdAsc();
		return InvoiceMapper.mapDomainListToFormList(listCreditTeriffmaster);

	}

	@Transactional
	@Override
	public String saveInvoice(InvoiceFormBean invoiceFormBean) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		if (invoiceFormBean.getId() == null) {
			TblAutoNumber autoNumber=	 tblAutoNumberRepository.findByTxnId(18);
			invoiceFormBean.setDocNo(invoiceFormBean.getLocationCode()+"/"+autoNumber.getNumber()+autoNumber.getSuffix());
			//invoiceFormBean.setCreatedBy(sessionUtility.getUserSession().getUsername());
			//invoiceFormBean.setCreatedDate(new Date());
			invoiceFormBean.setUploadTime(new Date());
			invoiceFormBean.setUpload("N");
			tblAutoNumberRepository.updateNumber(18);
		} else {
			//invoiceFormBean.setUpdatedBy(sessionUtility.getUserSession().getUsername());
		//	invoiceFormBean.setUpdatedDate(new Date());
		}

		Invoice obj = invoiceRepository
				.save(InvoiceMapper.mapFormToDomain(invoiceFormBean));
		if (obj != null) {
			
			List<StockInfo> stockInfoList = new ArrayList<>();
			
			StockInfo stockInfo1 = stockInfoRepository.find1ById(obj.getStockId());
			stockInfo1.setLastRow("FALSE");
			stockInfo1.setCumQty(new BigDecimal("0"));
			stockInfoList.add(stockInfo1);
				StockInfo stockInfo = new StockInfo();
				stockInfo.setBaseId(obj.getId());
				//stockInfo.setDetailId(sd.getId());
				stockInfo.setTxnId(18);
				stockInfo.setDocDt(obj.getDocDt());
				stockInfo.setDocNo(obj.getDocNo());
				stockInfo.setTxnFunction("MINUS");
				stockInfo.setLastRow("FALSE");
				stockInfo.setUserId(sessionUtility.getUserSession().getId());
				stockInfo.setLocationId(obj.getLocationId());
				stockInfo.setProductId(obj.getProductId());
				//stockInfo.setScreenId(15);
				stockInfo.setTxnId(18);
				stockInfo.setDocDt(obj.getDocDt());
				stockInfo.setDocNo(obj.getDocNo());
				stockInfo.setBatchNo(obj.getBatchNo());
				stockInfo.setUserId(sessionUtility.getUserSession().getId());
				stockInfo.setProductId(4);
				//stockInfo.setPrefix(obj.getcNoteNo());
				stockInfo.setQty(new BigDecimal("1"));
				stockInfo.setCumQty(new BigDecimal("0"));
				stockInfoList.add(stockInfo);
		    	stockInfoRepository.saveAll(stockInfoList);
		}
		

		if (obj != null) {
			return TransactionConstants.SAVE;
		}
		return TransactionConstants.FAIL;
	}

	@Override
	public List<InvoiceFormBean> getAllInvoice(ServerSidePagination serverSidePagination) {
		// TODO Auto-generated method stub
		Page<Invoice> invoiceList = null;
		//invoiceRepository.findAllByOrderByIdAsc();
		Pageable requestedPage = PageRequest.of(serverSidePagination.getPage(), serverSidePagination.getSize(),
				Sort.by("id").descending());
		int[] locationids = Arrays.stream(sessionUtility.getUserSession().getLocation().split(",")).mapToInt(Integer::parseInt)
				.toArray();
		List<Integer> list = Arrays.stream(locationids).boxed().collect(Collectors.toList());
		
		invoiceList = invoiceRepository.findByLocationAll(list,requestedPage);
		
		return InvoiceMapper.mapDomainListToFormList(invoiceList.toList());
	}

	@Override
	public List<InvoiceFormBean> findByWhereCondition(SearchCriteriaFormBean searchCriteriaFormBean) {
		InvoiceSpecification msTitleRating = new InvoiceSpecification();
         msTitleRating.add(new SearchCriteria(searchCriteriaFormBean.getKey(),searchCriteriaFormBean.getValue(), SearchOperation.valueOf(searchCriteriaFormBean.getOperation())));
        // msTitleRating.add(new SearchCriteria("rating", 7, SearchOperation.GREATER_THAN));
         List<Invoice> invoiceList = invoiceRepository.findAll(msTitleRating);
		//List<StockVerification> stockVerificationList = stockVerificationRepository.findByWhereCondition(where);
		return InvoiceMapper.mapDomainListToFormList(invoiceList);
	}

	

	
	
	
	

	
	

}
