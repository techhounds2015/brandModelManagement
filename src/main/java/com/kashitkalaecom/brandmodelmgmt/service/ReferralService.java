package com.kashitkalaecom.brandmodelmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.Referral;
import com.kashitkalaecom.brandmodelmgmt.models.Webpages;
import com.kashitkalaecom.brandmodelmgmt.repository.ReferralRepository;
import com.kashitkalaecom.brandmodelmgmt.repository.WebpagesRepository;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class ReferralService {

	@Autowired
	ReferralRepository referralRepository;

	public Referral save(Referral req, String requestorId) {
		req.setCreatedOn(CustomClock.timestamp());
		req.setCreatedBy(requestorId);
		return referralRepository.save(req);
	}
	
	public Referral update(Referral req, String requestorId) {
		req.setModifiedOn(CustomClock.timestamp());
		req.setModifiedBy(requestorId);
		return referralRepository.save(req);
	}
	
	public Referral delete(String id, String requestorId) {
		Referral referral = referralRepository.getById(id);
		referral.setModifiedBy(requestorId);
		referral.setModifiedOn( CustomClock.timestamp());
		referral.setStatus(false);
		return referralRepository.save(referral);
	}

	public Referral getReferralById(String code) {
		return referralRepository.findByName(code);
	}
}
