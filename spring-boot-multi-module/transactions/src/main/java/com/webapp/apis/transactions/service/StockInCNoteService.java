package com.webapp.apis.transactions.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webapp.apis.transactions.model.SearchCriteriaFormBean;
import com.webapp.apis.transactions.model.ServerSidePagination;
import com.webapp.apis.transactions.model.StockVerificationFormBean;
import com.webapp.apis.transactions.model.StockincnoteFormBean;
import com.webapp.apis.transactions.model.StocktransfersFormBean;;

@Service
public interface StockInCNoteService {
	public List<StockincnoteFormBean> getAllStockInCNote();

	List<StockincnoteFormBean> getAllStockInCNote(ServerSidePagination serverSidePagination);
	
	public String saveStockInCNote(StockincnoteFormBean stockincnoteFormBean);
	
	
	
	

	

	

}
