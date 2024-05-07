package com.webapp.apis.masters.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webapp.apis.masters.domain.TariffTypeMaster;
import com.webapp.apis.masters.model.CardTypeFormBean;
import com.webapp.apis.masters.model.LocationFormBean;
import com.webapp.apis.masters.model.ModeFormBean;
import com.webapp.apis.masters.model.StatusFormBean;
import com.webapp.apis.masters.model.TariffTypeMasterFormBean;

@Service
public interface MastersService {

	public List<StatusFormBean> getAllStatus();

	public String saveStatus(StatusFormBean statusFormBean);

	public List<ModeFormBean> getAllModes();

	public String saveMode(ModeFormBean modeFormBean);
	
	public List<TariffTypeMasterFormBean> getAllTariffTypes();

	public String saveTariffType(TariffTypeMasterFormBean tariffTypeMasterFormBean);

	public List<LocationFormBean> getAllLocation();

	public String saveLocation(LocationFormBean locationFormBean);
	
	public List<CardTypeFormBean> getAllCardType();

	public String saveCardType(CardTypeFormBean cardTypeFormBean);



}
