package com.kashitkalaecom.brandmodelmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.Brand;
import com.kashitkalaecom.brandmodelmgmt.repository.BrandRepository;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class BrandService {

	@Autowired
	BrandRepository brandRepository;
	
	public Brand getBrandById(String brandId) {
		return brandRepository.getById(brandId);
	}

	public Brand save(Brand brand, String requestorId) {
		brand.setCreatedBy(requestorId);
		brand.setCreatedOn(CustomClock.timestamp());
		return brandRepository.save(brand);
	}
	
	public Brand update(Brand brand, String requestorId) {
		brand.setModifiedBy(requestorId);
		brand.setModifiedOn(CustomClock.timestamp());
		return brandRepository.save(brand);
	}

	public Brand delete(String id, String requestorId) {
		Brand brand = brandRepository.getById(id);
		brand.setModifiedBy(requestorId);
		brand.setModifiedOn(CustomClock.timestamp());
		brand.setStatus(false);
		return brandRepository.save(brand);
	}
	
}
