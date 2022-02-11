package com.kashitkalaecom.brandmodelmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.PickUpAndDeliveryConfiguration;
import com.kashitkalaecom.brandmodelmgmt.repository.PickUpAndDeliveryConfigurationRepository;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class PickUpAndDeliveryConfigurationService {

	@Autowired
	PickUpAndDeliveryConfigurationRepository pickUpAndDeliveryConfigurationRepository;

	public PickUpAndDeliveryConfiguration save(PickUpAndDeliveryConfiguration pickUpAndDeliveryConfiguration,
			String requestorId) {
		pickUpAndDeliveryConfiguration.setCreatedBy(requestorId);
		pickUpAndDeliveryConfiguration.setCreatedOn(CustomClock.timestamp());
		return pickUpAndDeliveryConfigurationRepository.save(pickUpAndDeliveryConfiguration);
	}

	public PickUpAndDeliveryConfiguration getpickUpAndDeliveryConfigurationById(
			String pickUpAndDeliveryConfigurationId) {
		return pickUpAndDeliveryConfigurationRepository.getById(pickUpAndDeliveryConfigurationId);
	}

	public PickUpAndDeliveryConfiguration update(PickUpAndDeliveryConfiguration pickUpAndDeliveryConfiguration,
			String requestorId) {
		pickUpAndDeliveryConfiguration.setModifiedBy(requestorId);
		pickUpAndDeliveryConfiguration.setModifiedOn(CustomClock.timestamp());
		return pickUpAndDeliveryConfigurationRepository.save(pickUpAndDeliveryConfiguration);
	}

	public PickUpAndDeliveryConfiguration delete(String id, String requestorId) {
		PickUpAndDeliveryConfiguration pickUpAndDeliveryConfiguration = pickUpAndDeliveryConfigurationRepository
				.getById(id);
		pickUpAndDeliveryConfiguration.setModifiedBy(requestorId);
		pickUpAndDeliveryConfiguration.setModifiedOn(CustomClock.timestamp());
		pickUpAndDeliveryConfiguration.setStatus(false);
		return pickUpAndDeliveryConfigurationRepository.save(pickUpAndDeliveryConfiguration);
	}

	public PickUpAndDeliveryConfiguration getpickUpAndDeliveryConfigurationByName(String dileveryCharges) {
		return pickUpAndDeliveryConfigurationRepository.findByDileveryCharges(dileveryCharges);
	}

}
