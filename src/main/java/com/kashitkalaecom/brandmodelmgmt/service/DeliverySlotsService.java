package com.kashitkalaecom.brandmodelmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.DeliverySlots;
import com.kashitkalaecom.brandmodelmgmt.repository.DeliverySlotsRepositroy;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class DeliverySlotsService {

	@Autowired
	DeliverySlotsRepositroy deliverySlotsRepositroy;

	public DeliverySlots getDeliverySlotsById(String id) {
		return deliverySlotsRepositroy.getById(id);
	}

	public DeliverySlots save(DeliverySlots req, String requestorId) {
		req.setCreatedBy(requestorId);
		return deliverySlotsRepositroy.save(req);
	}
	
	public DeliverySlots update(DeliverySlots req, String requestorId) {
		req.setModifiedBy(requestorId);
		return deliverySlotsRepositroy.save(req);
	}
	
	public DeliverySlots delete(DeliverySlots req, String requestorId) {
		req.setModifiedOn(CustomClock.timestamp());
		req.setModifiedBy(requestorId);
		req.setStatus(false);
		return deliverySlotsRepositroy.save(req);
	}

	
}
