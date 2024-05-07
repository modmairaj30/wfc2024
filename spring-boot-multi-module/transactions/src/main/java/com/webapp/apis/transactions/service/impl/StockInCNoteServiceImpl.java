package com.webapp.apis.transactions.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

import com.webapp.apis.domian.TblAutonumberLocation;
import com.webapp.apis.repository.TblAutoNumberLocationRepository;
import com.webapp.apis.transaction.constants.TransactionConstants;
import com.webapp.apis.transactions.domain.StockInfo;
import com.webapp.apis.transactions.domain.Stockincnote;
import com.webapp.apis.transactions.domain.StockincnoteDetail;
import com.webapp.apis.transactions.mapper.StockInCNoteMapper;
import com.webapp.apis.transactions.model.ServerSidePagination;
import com.webapp.apis.transactions.model.StockincnoteFormBean;
import com.webapp.apis.transactions.repository.StockInCNoteRepository;
import com.webapp.apis.transactions.repository.StockInfoRepository;
import com.webapp.apis.transactions.service.StockInCNoteService;
import com.webapp.apis.transactions.utility.StockUpdate;
import com.webapp.apis.utility.SessionUtility;

@Service
public class StockInCNoteServiceImpl implements StockInCNoteService {
	private static final Logger LOGGER = LoggerFactory.getLogger(StockInCNoteServiceImpl.class);
	@Inject
	private StockInCNoteRepository stockInCNoteRepository;
	@Inject
	private TblAutoNumberLocationRepository tblAutoNumberLocationRepository;
	

	@Inject
	private StockInfoRepository stockInfoRepository;
	
	@Inject
	private SessionUtility sessionUtility;
	@Inject
	private StockUpdate stockUpdate;
	
	/*private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void StockInCNoteService(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate=jdbcTemplate;
	}
	
	public boolean checkNumberExists(String enteredNumber)
	{
		String query="Select COUNT(*) FROM tbl_stock_info WHERE prefix=?";
		
		int count=jdbcTemplate.queryForObject(query, Integer.class, enteredNumber);
		
		return count>0;
	}
	*/

	@Override
	public List<StockincnoteFormBean> getAllStockInCNote(ServerSidePagination serverSidePagination) {
		Page<Stockincnote> stockincnoteList = null;
		stockInCNoteRepository.findAllByOrderByIdAsc();
		Pageable requestedPage = PageRequest.of(serverSidePagination.getPage(), serverSidePagination.getSize(),
				Sort.by("id").descending());
		int[] locationids = Arrays.stream(sessionUtility.getUserSession().getLocation().split(",")).mapToInt(Integer::parseInt)
				.toArray();
		List<Integer> list = Arrays.stream(locationids).boxed().collect(Collectors.toList());
		stockincnoteList = stockInCNoteRepository.findByLocationAll(list, requestedPage);
		return StockInCNoteMapper.mapDomainListToFormList(stockincnoteList.toList());

	}

	@Override
	public List<StockincnoteFormBean> getAllStockInCNote() {
		List<Stockincnote> ls = stockInCNoteRepository.findAll();
		return StockInCNoteMapper.mapDomainListToFormList(ls);
	}

	@Transactional
	@Override
	public String saveStockInCNote(StockincnoteFormBean stockincnoteFormBean) {
		
		
		if (stockincnoteFormBean.getId() == null) {
			TblAutonumberLocation autoNumber=	 tblAutoNumberLocationRepository.findByTxnIdAndLocationId(1095,1);
			stockincnoteFormBean.setDocNo(autoNumber.getPrefix()+autoNumber.getNumber()+autoNumber.getSuffix());
			stockincnoteFormBean.setCreatedBy(sessionUtility.getUserSession().getUsername());
			stockincnoteFormBean.setCreatedDate(new Date());
			//tblAutoNumberLocationRepository.updateNumber(1095,new Integer(0));
		} else {
			stockincnoteFormBean.setUpdatedBy(sessionUtility.getUserSession().getUsername());
			stockincnoteFormBean.setUpdatedDate(new Date());
		}
		
		
		int count= stockInCNoteRepository.findByPrefixAndFromNoAndToNo1(stockincnoteFormBean.getcPrefix(),stockincnoteFormBean.getFromNo(),stockincnoteFormBean.getToNo());
		
		if(count>0) {
			return "Duplicate Cnote";
		}

		
		
	Stockincnote sc= stockInCNoteRepository.save(StockInCNoteMapper.mapFormToDomain(stockincnoteFormBean));
		List<StockInfo> stockInfoList = new ArrayList<>();
	    
		for (StockincnoteDetail sd : sc.getStockincnoteDetailList()) {
			StockInfo stockInfo = new StockInfo();
			stockInfo.setBaseId(sc.getId());
			stockInfo.setDetailId(sd.getId());
			//stockInfo.setScreenId(16);
			stockInfo.setTxnId(1095);
			stockInfo.setDocDt(sc.getDocDt());
			stockInfo.setDocNo(sc.getDocNo());
			stockInfo.setBatchNo(sd.getcNoteNo());
			stockInfo.setTxnFunction("PLUS");
			stockInfo.setLastRow("TRUE");
			stockInfo.setUserId(sessionUtility.getUserSession().getId());
			stockInfo.setLocationId(sc.getLocationId());
			stockInfo.setProductId(4);
			//stockInfo.setPrefix(sd.getcNoteNo());
			stockInfo.setProductCode(sd.getcNoteNo());
			stockInfo.setQty(new BigDecimal("1"));
			stockInfo.setCumQty(new BigDecimal("1"));
			stockInfoList.add(stockInfo);
		    }
		

			if (sc != null) {
				stockInfoRepository.saveAll(stockInfoList);
				
			}
			
			
		
		

		return "Success";
	

	}	
		
	}

	


