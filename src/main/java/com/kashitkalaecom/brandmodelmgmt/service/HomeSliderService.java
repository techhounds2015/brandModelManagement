package com.kashitkalaecom.brandmodelmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.HomeSlider;
import com.kashitkalaecom.brandmodelmgmt.repository.HomeSliderRepository;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class HomeSliderService {

	
	@Autowired
	HomeSliderRepository homeSliderRepository;

	public HomeSlider getHomeSliderById(String id) {
		return homeSliderRepository.findByName(id);
	}

	public HomeSlider save(HomeSlider req, String requestorId) {
		req.setCreatedOn(CustomClock.timestamp());
		req.setCreatedBy(requestorId);
		return homeSliderRepository.save(req);
	}
	
	public HomeSlider getById(String id) {
		return homeSliderRepository.getById(id);
	}
	
	
	public HomeSlider update(HomeSlider req, String requestorId ) {
		
		req.setModifiedOn(CustomClock.timestamp());
		req.setModifiedBy(requestorId);
		return homeSliderRepository.save(req);
	}
	
	public HomeSlider delete(String id, String requestorId) {
		HomeSlider homeSlider = homeSliderRepository.getById(id);
		homeSlider.setModifiedBy(requestorId);
		homeSlider.setModifiedOn( CustomClock.timestamp());
		homeSlider.setStatus(false);
		return homeSliderRepository.save(homeSlider);
	}

}
