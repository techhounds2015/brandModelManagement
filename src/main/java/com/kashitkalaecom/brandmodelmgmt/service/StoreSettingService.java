package com.kashitkalaecom.brandmodelmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.StoreSetting;
import com.kashitkalaecom.brandmodelmgmt.repository.StoreSettingRepository;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class StoreSettingService {

	@Autowired
	StoreSettingRepository storeSettingRepository;

	public StoreSetting save(StoreSetting storeSetting, String requestorId) {
		storeSetting.setCreatedBy(requestorId);
		storeSetting.setCreatedOn(CustomClock.timestamp());
		return storeSettingRepository.save(storeSetting);
	}

	public StoreSetting getStoreSettingById(String storeSettingId) {
		return storeSettingRepository.getById(storeSettingId);
	}

	public StoreSetting update(StoreSetting storeSetting, String requestorId) {
		storeSetting.setModifiedBy(requestorId);
		storeSetting.setModifiedOn(CustomClock.timestamp());
		return storeSettingRepository.save(storeSetting);
	}

	public StoreSetting delete(String id, String requestorId) {
		StoreSetting storeSetting = storeSettingRepository.getById(id);
		storeSetting.setModifiedBy(requestorId);
		storeSetting.setModifiedOn(CustomClock.timestamp());
		storeSetting.setStatus(false);
		return storeSettingRepository.save(storeSetting);
	}

	public StoreSetting getStoreSettingByName(String companyName) {
		return storeSettingRepository.findByName(companyName);
	}

}
