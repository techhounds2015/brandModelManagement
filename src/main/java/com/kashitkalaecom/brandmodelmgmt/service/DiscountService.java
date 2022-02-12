package com.kashitkalaecom.brandmodelmgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.Discount;
import com.kashitkalaecom.brandmodelmgmt.models.Webpages;
import com.kashitkalaecom.brandmodelmgmt.repository.DiscountRepository;
import com.kashitkalaecom.brandmodelmgmt.repository.WebpagesRepository;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class DiscountService {

	@Autowired
	DiscountRepository discountRepository;

	public Discount getDiscountById(String id) {
		return discountRepository.getById(id);
	}

	public Discount save(Discount req, String requestorId) {
		req.setCreatedOn(CustomClock.timestamp());
		req.setCreatedBy(requestorId);
		return discountRepository.save(req);
	}
	
	public Discount update(Discount req, String requestorId ) {
		
		req.setModifiedOn(CustomClock.timestamp());
		req.setModifiedBy(requestorId);
		return discountRepository.save(req);
	}
	
	public Discount delete(String id, String requestorId) {
		Discount discount = discountRepository.getById(id);
		discount.setModifiedBy(requestorId);
		discount.setModifiedOn( CustomClock.timestamp());
		discount.setStatus(false);
		return discountRepository.save(discount);
	}

	public Discount getpagesById(String discount) {
		return discountRepository.findByName(discount);
	}
}
