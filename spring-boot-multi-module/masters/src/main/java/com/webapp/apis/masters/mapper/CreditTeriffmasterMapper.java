package com.webapp.apis.masters.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webapp.apis.masters.domain.CreditTeriffmaster;
import com.webapp.apis.masters.domain.CreditTeriffmasterDetail;
import com.webapp.apis.masters.model.CreditTeriffmasterDetailFormBean;
import com.webapp.apis.masters.model.CreditTeriffmasterFormBean;

/**
 * @author Mairaj
 */

public class CreditTeriffmasterMapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CreditTeriffmasterMapper.class);
	
	public static CreditTeriffmaster mapFormToDomain(CreditTeriffmasterFormBean creditTeriffmasterFormBean) {
		CreditTeriffmaster creditTeriffmaster = new CreditTeriffmaster();
		List<CreditTeriffmasterDetail> detailDomainList = new ArrayList<>();
		creditTeriffmaster.setDocDt(creditTeriffmasterFormBean.getDocDt());
		creditTeriffmaster.setDocTime(creditTeriffmasterFormBean.getDocTime());
		creditTeriffmaster.setFromWeight(creditTeriffmasterFormBean.getFromWeight());
		creditTeriffmaster.setLocationId(creditTeriffmasterFormBean.getLocationId());
		creditTeriffmaster.setModeConsign(creditTeriffmasterFormBean.getModeConsign());
		creditTeriffmaster.setPartyId(creditTeriffmasterFormBean.getPartyId());
		creditTeriffmaster.setPaymentType(creditTeriffmasterFormBean.getPaymentType());
		creditTeriffmaster.setToWeight(creditTeriffmasterFormBean.getToWeight());
		creditTeriffmaster.setType(creditTeriffmasterFormBean.getType());
		creditTeriffmaster.setUserId(creditTeriffmasterFormBean.getUserId());
		creditTeriffmaster.setWeight(creditTeriffmasterFormBean.getWeight());
		creditTeriffmaster.setState(creditTeriffmasterFormBean.getState());
		
		List<CreditTeriffmasterDetailFormBean> formdetailList=creditTeriffmasterFormBean.getCreditTeriffmasterDetailFormBeanList();
		for(CreditTeriffmasterDetailFormBean detailFormbeam:formdetailList) {
		CreditTeriffmasterDetail detailDomain = new CreditTeriffmasterDetail();
		detailDomain.setId(detailFormbeam.getId());
		detailDomain.setBaseId(detailFormbeam.getBaseId() != null? new CreditTeriffmaster(detailFormbeam.getBaseId()) : null);
		detailDomain.setAddqty(detailFormbeam.getAddqty());
		detailDomain.setAddrate(detailFormbeam.getAddrate());
		detailDomain.setLocationId(detailFormbeam.getLocationId());
		detailDomain.setRate(detailFormbeam.getRate());
		detailDomainList.add(detailDomain);
		}
		creditTeriffmaster.setCreditTeriffmasterDetailList(detailDomainList);
		return creditTeriffmaster;
	}

	public static CreditTeriffmasterFormBean mapDomainToForm(CreditTeriffmaster creditTeriffmaster) {
		CreditTeriffmasterFormBean creditTeriffmasterFormBean = new CreditTeriffmasterFormBean();
		List<CreditTeriffmasterDetailFormBean> detailFormList = new ArrayList<>();
		creditTeriffmasterFormBean.setDocDt(creditTeriffmaster.getDocDt());
		creditTeriffmasterFormBean.setDocTime(creditTeriffmaster.getDocTime());
		creditTeriffmasterFormBean.setFromWeight(creditTeriffmaster.getFromWeight());
		creditTeriffmasterFormBean.setLocationId(creditTeriffmaster.getLocationId());
		creditTeriffmasterFormBean.setModeConsign(creditTeriffmaster.getModeConsign());
		creditTeriffmasterFormBean.setPartyId(creditTeriffmaster.getPartyId());
		creditTeriffmasterFormBean.setPaymentType(creditTeriffmaster.getPaymentType());
		creditTeriffmasterFormBean.setToWeight(creditTeriffmaster.getToWeight());
		creditTeriffmasterFormBean.setType(creditTeriffmaster.getType());
		creditTeriffmasterFormBean.setUserId(creditTeriffmaster.getUserId());
		creditTeriffmasterFormBean.setWeight(creditTeriffmaster.getWeight());
		creditTeriffmasterFormBean.setState(creditTeriffmaster.getState());
		
		List<CreditTeriffmasterDetail> domainDetailList=creditTeriffmaster.getCreditTeriffmasterDetailList();
		for(CreditTeriffmasterDetail detailDomain:domainDetailList) {
			CreditTeriffmasterDetailFormBean detailForm = new CreditTeriffmasterDetailFormBean();
			detailForm.setId(detailDomain.getId());
			detailForm.setBaseId(detailDomain.getBaseId().getId());
			detailForm.setAddqty(detailDomain.getAddqty());
			detailForm.setAddrate(detailDomain.getAddrate());
			detailForm.setLocationId(detailDomain.getLocationId());
			detailForm.setRate(detailDomain.getRate());
			detailFormList.add(detailForm);
		}
		creditTeriffmasterFormBean.setCreditTeriffmasterDetailFormBeanList(detailFormList);
		return creditTeriffmasterFormBean;
		
	}

	public static List<CreditTeriffmasterFormBean> mapDomainListToFormList(List<CreditTeriffmaster> creditTeriffmasterList) {
		List<CreditTeriffmasterFormBean> CreditTeriffmasterFormBeanList = new ArrayList<>();
		for (CreditTeriffmaster creditTeriffmaster : creditTeriffmasterList) {
			CreditTeriffmasterFormBeanList.add(mapDomainToForm(creditTeriffmaster));
		}
		return CreditTeriffmasterFormBeanList;

	}
	
	public static List<CreditTeriffmaster> mapFormListToDomainList(List<CreditTeriffmasterFormBean> creditTeriffmasterFormBeanList) {
		List<CreditTeriffmaster> creditTeriffmasterList = new ArrayList<>();
		for (CreditTeriffmasterFormBean creditTeriffmasterFormBean : creditTeriffmasterFormBeanList) {
			creditTeriffmasterList.add(mapFormToDomain(creditTeriffmasterFormBean));
		}
		return creditTeriffmasterList;
	}

}
