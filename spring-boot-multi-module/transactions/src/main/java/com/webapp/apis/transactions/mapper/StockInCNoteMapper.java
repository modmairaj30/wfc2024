package com.webapp.apis.transactions.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webapp.apis.transactions.domain.Invoice;
import com.webapp.apis.transactions.domain.Stockincnote;
import com.webapp.apis.transactions.domain.StockincnoteDetail;
import com.webapp.apis.transactions.domain.Stocktransfers;
import com.webapp.apis.transactions.domain.StocktransfersDetail;
import com.webapp.apis.transactions.model.StockincnoteDetailFormBean;
import com.webapp.apis.transactions.model.StockincnoteFormBean;
import com.webapp.apis.transactions.model.StocktransfersDetailFormBean;
import com.webapp.apis.transactions.model.StocktransfersFormBean;

public class StockInCNoteMapper {
	private static final Logger LOGGER = LoggerFactory.getLogger(StockInCNoteMapper.class);

	public static Stockincnote mapFormToDomain(StockincnoteFormBean stockInCnoteFormBean) {
		Stockincnote stockincnote = new Stockincnote();
		List<StockincnoteDetail> StockInCNoteDetailList = new ArrayList<>();
		
		stockincnote.setId(stockInCnoteFormBean.getId());
		stockincnote.setLocationId(stockInCnoteFormBean.getLocationId());
		stockincnote.setDocDt(stockInCnoteFormBean.getDocDt());
		stockincnote.setDocNo(stockInCnoteFormBean.getDocNo());
		
		stockincnote.setUid(stockInCnoteFormBean.getUid());
		stockincnote.setCPrefix(stockInCnoteFormBean.getcPrefix());
		stockincnote.setFromNo(stockInCnoteFormBean.getFromNo());
		stockincnote.setToNo(stockInCnoteFormBean.getToNo());
		stockincnote.setCreatedBy(stockInCnoteFormBean.getCreatedBy());
		stockincnote.setCreatedDate(stockInCnoteFormBean.getCreatedDate());
		stockincnote.setUpdatedBy(stockInCnoteFormBean.getUpdatedBy());
		stockincnote.setUpdatedDate(stockInCnoteFormBean.getUpdatedDate());
		
		
		
		List<StockincnoteDetailFormBean> formdetailList = stockInCnoteFormBean.getStockincnoteDetailList();
		int fromNo= Integer.parseInt(stockincnote.getFromNo());
		int toNo= Integer.parseInt(stockincnote.getToNo());
		for (int i=fromNo; i<=toNo;i++) {
			StockincnoteDetail detailDomain = new StockincnoteDetail();

			//detailDomain.setId(detailFormbeam.getId());
		 	detailDomain.setQty(1);
			//detailDomain.setBaseId(detailFormbeam.getBaseId());
			detailDomain.setProductId(stockincnote.getCPrefix()+i);
			detailDomain.setcNoteNo(stockincnote.getCPrefix()+i);
			detailDomain.setsNo(+i);
			
			
			
			

			// BeanUtils.copyProperties(detailFormbeam, detailDomain);
			StockInCNoteDetailList.add(detailDomain);
			
		}
		
		stockincnote.setStockincnoteDetailList(StockInCNoteDetailList);
		return stockincnote;
	}
	
	
	public static StockincnoteFormBean mapDomainToForm(Stockincnote stockInCNote) {
		StockincnoteFormBean stockInCNoteFormBean = new StockincnoteFormBean();
		List<StockincnoteDetailFormBean> stockInCNoteDetailList = new ArrayList<>();

		stockInCNoteFormBean.setId(stockInCNote.getId());
		stockInCNoteFormBean.setLocationId(stockInCNote.getLocationId());
		stockInCNoteFormBean.setDocDt(stockInCNote.getDocDt());
		stockInCNoteFormBean.setDocNo(stockInCNote.getDocNo());
		
		stockInCNoteFormBean.setUid(stockInCNote.getUid());
		stockInCNoteFormBean.setcPrefix(stockInCNote.getCPrefix());
		stockInCNoteFormBean.setFromNo(stockInCNote.getFromNo());
		stockInCNoteFormBean.setToNo(stockInCNote.getToNo());
		
		stockInCNoteFormBean.setCreatedBy(stockInCNote.getCreatedBy());
		stockInCNoteFormBean.setCreatedDate(stockInCNote.getCreatedDate());
		stockInCNoteFormBean.setUpdatedBy(stockInCNote.getUpdatedBy());
		stockInCNoteFormBean.setUpdatedDate(stockInCNote.getUpdatedDate());
		
		
		
		
		List<StockincnoteDetail> formdetailList = stockInCNote.getStockincnoteDetailList();
		for (StockincnoteDetail detailDomain : formdetailList) {
			StockincnoteDetailFormBean detailFormbean = new StockincnoteDetailFormBean();

			
			stockInCNoteDetailList.add(detailFormbean);
			
				
		}
		
		stockInCNoteFormBean.setStockincnoteDetailList(stockInCNoteDetailList);;
		return stockInCNoteFormBean;
	}
	
	public static List<StockincnoteFormBean> mapDomainListToFormList(List<Stockincnote> StockInCNoteList) {
		List<StockincnoteFormBean> StockInCNoteListFormBeanList = new ArrayList<>();
		for (Stockincnote StockInCNote : StockInCNoteList) {
			StockInCNoteListFormBeanList.add(mapDomainToForm(StockInCNote));
		}
		return StockInCNoteListFormBeanList;

	}
}
