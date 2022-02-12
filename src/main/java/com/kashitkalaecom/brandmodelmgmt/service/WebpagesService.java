package com.kashitkalaecom.brandmodelmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.Category;
import com.kashitkalaecom.brandmodelmgmt.models.Webpages;
import com.kashitkalaecom.brandmodelmgmt.repository.WebpagesRepository;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class WebpagesService {


	@Autowired
	WebpagesRepository webpagesRepositroy;

	

	public Webpages save(Webpages req, String requestorId) {
		req.setCreatedOn(CustomClock.timestamp());
		req.setCreatedBy(requestorId);
		return webpagesRepositroy.save(req);
	}
	
	public Webpages update(Webpages req, String requestorId ) {
		
		req.setModifiedOn(CustomClock.timestamp());
		req.setModifiedBy(requestorId);
		return webpagesRepositroy.save(req);
	}
	
	public Webpages delete(String id, String requestorId) {
		Webpages webpages = webpagesRepositroy.getById(id);
		webpages.setModifiedBy(requestorId);
		webpages.setModifiedOn( CustomClock.timestamp());
		webpages.setStatus(false);
		return webpagesRepositroy.save(webpages);
	}
	
	public Webpages getWebpagesById(String title) {
		return webpagesRepositroy.findByName(title);
	}

}
