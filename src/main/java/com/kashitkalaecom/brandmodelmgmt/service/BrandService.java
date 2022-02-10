package com.kashitkalaecom.brandmodelmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.Brand;
import com.kashitkalaecom.brandmodelmgmt.repository.BrandRepository;

@Service
public class BrandService {

	@Autowired
	BrandRepository brandRepository;

	public Brand getBrandById(String brandId) {
		return brandRepository.getById(brandId);
	}

	public Brand save(Brand brand, String requestorId) {
		brand.setCreatedBy(requestorId);
		return brandRepository.save(brand);
	}
	
	public Brand update(Brand brand, String requestorId) {
		brand.setModifiedBy(requestorId);
		return brandRepository.save(brand);
	}
	
	public Brand update1(Brand brand, String requestorId) {
		brand.setModifiedBy(requestorId);
		brand.setStatus(false);
		return brandRepository.save(brand);
	}
	
}
