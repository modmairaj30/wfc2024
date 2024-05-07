package com.webapp.apis.masters.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.apis.masters.model.CardTypeFormBean;
import com.webapp.apis.masters.model.LocationFormBean;
import com.webapp.apis.masters.model.ModeFormBean;
import com.webapp.apis.masters.model.StatusFormBean;
import com.webapp.apis.masters.model.TariffTypeMasterFormBean;
import com.webapp.apis.masters.service.MastersService;
import com.webapp.apis.utility.ResponseWrapper;

@RestController
@RequestMapping(value = "/masters")
public class MastersController {

	@Inject
	private MastersService mastersService;

	@RequestMapping(value = "/getAllMode", method = RequestMethod.GET)
	public ResponseWrapper<List<ModeFormBean>> getAllMode() {
		List<ModeFormBean> forms = mastersService.getAllModes();
		return new ResponseWrapper<List<ModeFormBean>>(null, HttpStatus.OK, "", forms);

	}

	@RequestMapping(value = "/saveMode", method = RequestMethod.POST)
	public ResponseWrapper<String> saveMode(@RequestBody ModeFormBean modeFormBean) {
		String flag = mastersService.saveMode(modeFormBean);
		return new ResponseWrapper<String>(null, HttpStatus.OK, "", flag);

	}

	@RequestMapping(value = "/getAllStatus", method = RequestMethod.GET)
	public ResponseWrapper<List<StatusFormBean>> getAllStatus() {
		List<StatusFormBean> forms = mastersService.getAllStatus();
		return new ResponseWrapper<List<StatusFormBean>>(null, HttpStatus.OK, "", forms);

	}

	@RequestMapping(value = "/saveStatus", method = RequestMethod.POST)
	public ResponseWrapper<String> saveStatus(@RequestBody StatusFormBean statusFormBean) {
		String flag = mastersService.saveStatus(statusFormBean);
		return new ResponseWrapper<String>(null, HttpStatus.OK, "", flag);

	}

	@RequestMapping(value = "/getAllTariffType", method = RequestMethod.GET)
	public ResponseWrapper<List<TariffTypeMasterFormBean>> getAllTariffType() {
		List<TariffTypeMasterFormBean> forms = mastersService.getAllTariffTypes();
		return new ResponseWrapper<List<TariffTypeMasterFormBean>>(null, HttpStatus.OK, "", forms);

	}

	@RequestMapping(value = "/saveTariffType", method = RequestMethod.POST)
	public ResponseWrapper<String> saveMode(@RequestBody TariffTypeMasterFormBean tariffTypeMasterFormBean) {
		String flag = mastersService.saveTariffType(tariffTypeMasterFormBean);
		return new ResponseWrapper<String>(null, HttpStatus.OK, "", flag);

	}

	@RequestMapping(value = "/getAllLocation", method = RequestMethod.GET)
	public ResponseWrapper<List<LocationFormBean>> getAllLocation() {
		List<LocationFormBean> forms = mastersService.getAllLocation();
		return new ResponseWrapper<List<LocationFormBean>>(null, HttpStatus.OK, "", forms);

	}

	@RequestMapping(value = "/saveLocation", method = RequestMethod.POST)
	public ResponseWrapper<String> saveStatus(@RequestBody LocationFormBean locationFormBean) {
		String flag = mastersService.saveLocation(locationFormBean);
		return new ResponseWrapper<String>(null, HttpStatus.OK, "", flag);

	}
	
	@RequestMapping(value = "/getAllCardType", method = RequestMethod.GET)
	public ResponseWrapper<List<CardTypeFormBean>> getAllCardType() {
		List<CardTypeFormBean> forms = mastersService.getAllCardType();
		return new ResponseWrapper<List<CardTypeFormBean>>(null, HttpStatus.OK, "", forms);

	}

	@RequestMapping(value = "/saveCardType", method = RequestMethod.POST)
	public ResponseWrapper<String> saveCardType(@RequestBody CardTypeFormBean cardTypeFormBean) {
		String flag = mastersService.saveCardType(cardTypeFormBean);
		return new ResponseWrapper<String>(null, HttpStatus.OK, "", flag);

	}


}
