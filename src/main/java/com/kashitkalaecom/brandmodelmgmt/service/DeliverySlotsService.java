package com.kashitkalaecom.brandmodelmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.Category;
import com.kashitkalaecom.brandmodelmgmt.models.DeliverySlots;
import com.kashitkalaecom.brandmodelmgmt.models.Discount;
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
		req.setModifiedOn(CustomClock.timestamp());
		req.setModifiedBy(requestorId);
		return deliverySlotsRepositroy.save(req);
	}
	
	public DeliverySlots delete(String id, String requestorId) {
		DeliverySlots req = deliverySlotsRepositroy.getById(id);
		req.setModifiedBy(requestorId);
		req.setModifiedOn( CustomClock.timestamp());
		req.setStatus(false);
		return deliverySlotsRepositroy.save(req);
	}
	
	public DeliverySlots getDeliverySlotsByName(String name) {
		return deliverySlotsRepositroy.findByName(name);
	}

	
}
