package com.webapp.apis.masters.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.apis.masters.domain.CardType;
import com.webapp.apis.masters.domain.Location;
import com.webapp.apis.masters.domain.Mode;
import com.webapp.apis.masters.domain.Status;
import com.webapp.apis.masters.domain.TariffTypeMaster;
import com.webapp.apis.masters.model.CardTypeFormBean;
import com.webapp.apis.masters.model.LocationFormBean;
import com.webapp.apis.masters.model.ModeFormBean;
import com.webapp.apis.masters.model.StatusFormBean;
import com.webapp.apis.masters.model.TariffTypeMasterFormBean;
import com.webapp.apis.masters.repository.CardTypeRepository;
import com.webapp.apis.masters.repository.LocationRepository;
import com.webapp.apis.masters.repository.ModeRepository;
import com.webapp.apis.masters.repository.StatusRepository;
import com.webapp.apis.masters.repository.TariffTypeMasterRepository;
import com.webapp.apis.masters.service.MastersService;

@Service
public class MastersServiceImpl implements MastersService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MastersServiceImpl.class);

	@Inject
	private ModeRepository modeRepository;

	@Inject
	private LocationRepository locationRepository;

	@Inject
	private StatusRepository statusRepository;

	@Inject
	private TariffTypeMasterRepository tariffTypeRepository;
	
	@Inject
	private CardTypeRepository cardTypeRepository;

	@Override
	public List<StatusFormBean> getAllStatus() {
		List<Status> listStatus = statusRepository.findAllByOrderByIdDesc();
		List<StatusFormBean> listStatusFormbean = new ArrayList<StatusFormBean>();
		for (Status status : listStatus) {
			StatusFormBean statusFormBean = new StatusFormBean();
			BeanUtils.copyProperties(status, statusFormBean);
			listStatusFormbean.add(statusFormBean);
		}

		return listStatusFormbean;
	}

	@Transactional
	@Override
	public String saveStatus(StatusFormBean statusFormBean) {
		Status status = new Status();
		BeanUtils.copyProperties(statusFormBean, status);
		status = statusRepository.save(status);
		return "Saved Successfully";
	}

	@Override
	public List<ModeFormBean> getAllModes() {
		List<Mode> listmode = modeRepository.findAllByOrderByIdDesc();
		List<ModeFormBean> listModeFormbean = new ArrayList<ModeFormBean>();
		for (Mode mode : listmode) {
			ModeFormBean modeFormBean = new ModeFormBean();
			BeanUtils.copyProperties(mode, modeFormBean);
			listModeFormbean.add(modeFormBean);
		}

		return listModeFormbean;
	}

	@Transactional
	@Override
	public String saveMode(ModeFormBean modeFormBean) {
		Mode mode = new Mode();
		BeanUtils.copyProperties(modeFormBean, mode);
		mode = modeRepository.save(mode);
		return "Saved Successfully";
	}

	@Override
	public List<TariffTypeMasterFormBean> getAllTariffTypes() {
		List<TariffTypeMaster> listTariffType = tariffTypeRepository.findAllByOrderByIdDesc();
		List<TariffTypeMasterFormBean> listTariffTypeFormbean = new ArrayList<TariffTypeMasterFormBean>();
		for (TariffTypeMaster tariffTypeMaster : listTariffType) {
			TariffTypeMasterFormBean tariffTypeMasterFormBean = new TariffTypeMasterFormBean();
			BeanUtils.copyProperties(tariffTypeMaster, tariffTypeMasterFormBean);
			listTariffTypeFormbean.add(tariffTypeMasterFormBean);
		}

		return listTariffTypeFormbean;
	}

	@Transactional
	@Override
	public String saveTariffType(TariffTypeMasterFormBean tariffTypeMasterFormBean) {
		TariffTypeMaster tariffTypeMaster = new TariffTypeMaster();
		BeanUtils.copyProperties(tariffTypeMasterFormBean, tariffTypeMaster);
		tariffTypeMaster = tariffTypeRepository.save(tariffTypeMaster);
		return "Saved Successfully";
	}

	@Override
	public List<LocationFormBean> getAllLocation() {
		List<Location> listLocation = locationRepository.findAllByOrderByIdDesc();
		List<LocationFormBean> listLocationFormbean = new ArrayList<LocationFormBean>();
		for (Location location : listLocation) {
			LocationFormBean locationFormBean = new LocationFormBean();
			BeanUtils.copyProperties(location, locationFormBean);
			listLocationFormbean.add(locationFormBean);
		}

		return listLocationFormbean;
	}

	@Transactional
	@Override
	public String saveLocation(LocationFormBean locationFormBean) {
		Location location = new Location();
		BeanUtils.copyProperties(locationFormBean, location);
		location = locationRepository.save(location);
		return "Saved Successfully";
	}
	
	@Override
	public List<CardTypeFormBean> getAllCardType() {
		List<CardType> listCardType = cardTypeRepository.findAllByOrderByIdDesc();
		List<CardTypeFormBean> listCardTypeFormbean = new ArrayList<CardTypeFormBean>();
		for (CardType cardType : listCardType) {
			CardTypeFormBean cardTypeFormBean = new CardTypeFormBean();
			BeanUtils.copyProperties(cardType, cardTypeFormBean);
			listCardTypeFormbean.add(cardTypeFormBean);
		}

		return listCardTypeFormbean;
	}

	@Transactional
	@Override
	public String saveCardType(CardTypeFormBean cardTypeFormBean) {
		CardType cardType = new CardType();
		BeanUtils.copyProperties(cardTypeFormBean, cardType);
		cardType = cardTypeRepository.save(cardType);
		return "Saved Successfully";
	}


}
