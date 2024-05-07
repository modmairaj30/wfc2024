package com.webapp.apis.transactions.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.apis.exceptions.WebAppError;
import com.webapp.apis.exceptions.WebAppException;
import com.webapp.apis.transaction.constants.TransactionConstants;
import com.webapp.apis.transactions.model.ServerSidePagination;
import com.webapp.apis.transactions.model.StockincnoteFormBean;
import com.webapp.apis.transactions.service.StockInCNoteService;
import com.webapp.apis.transactions.utility.ResponseWrapper;

@RestController
@RequestMapping(value = "/transaction")
public class StockInCNoteController {

	@Inject
	private StockInCNoteService stockInCNoteService;

	
	/*@Inject
	private StockInCNoteServiceImpl stockInCNoteServiceImpl;
	
	public StockInCNoteController(StockInCNoteService stockInCNoteService)
	{
		this.stockInCNoteService=stockInCNoteService;
	}
	
	@RequestMapping(value = "/check-number", method = RequestMethod.GET)
	public boolean checkNumberExists(@RequestParam("prefix") String prefix)
	{
		return stockInCNoteServiceImpl.checkNumberExists(prefix);
	}
	*/

		
	@RequestMapping(value = "/getAllStockInCNote", method = RequestMethod.GET)
	public ResponseWrapper<List<StockincnoteFormBean>> getStockInCNote(@RequestParam("page")Integer page, @RequestParam("size")Integer size,@RequestParam("location")String location) {
		ServerSidePagination serverSidePagination = new ServerSidePagination();
		serverSidePagination.setPage(page);
		serverSidePagination.setSize(size);
		serverSidePagination.setLocation(location);
		List<StockincnoteFormBean> forms = stockInCNoteService.getAllStockInCNote(serverSidePagination);
		return new ResponseWrapper<List<StockincnoteFormBean>>(null, HttpStatus.OK, "", forms);

	}
	@RequestMapping(value = "/getAllStockInCNote1", method = RequestMethod.GET)
	public ResponseWrapper<List<StockincnoteFormBean>> getStockInCNote1() {
		
		List<StockincnoteFormBean> forms = stockInCNoteService.getAllStockInCNote();
		return new ResponseWrapper<List<StockincnoteFormBean>>(null, HttpStatus.OK, "", forms);

	}
	
		
	@RequestMapping(value = "/saveStockInCNote", method = RequestMethod.POST)
	public ResponseWrapper<String> saveStockInCNote(
			@RequestBody StockincnoteFormBean stockincnoteFormBean) {
		String flag = stockInCNoteService.saveStockInCNote(stockincnoteFormBean);
		
		List<WebAppError> error = new ArrayList<>(); 
		WebAppException t = null;
		
		// Check if the save operation was successful
		if ("Success".equals(flag)) {
		    // If successful, return a ResponseWrapper with the result
		    return new ResponseWrapper<>(t, HttpStatus.OK, "", flag);
		} else {
		    // If not successful, log the error and throw a WebAppException
		    error.add(new WebAppError(TransactionConstants.SAVE, TransactionConstants.DB_ERROR, flag));
		    
		    throw new WebAppException(HttpStatus.BAD_REQUEST.toString(), TransactionConstants.SAVE_FAILURE, error);
		}

		
		
//		if (flag!=TransactionConstants.SAVE) {
//			error.add(new WebAppError(TransactionConstants.SAVE,TransactionConstants.DB_ERROR,flag));
//			throw new WebAppException(HttpStatus.BAD_REQUEST.toString(),TransactionConstants.SAVE_FAILURE,error);
//		} 
//		return new ResponseWrapper<String>(t, HttpStatus.OK, "", flag);
//			
		}
	
	

	
}
