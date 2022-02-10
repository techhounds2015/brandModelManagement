package com.kashitkalaecom.brandmodelmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.ProductImages;
import com.kashitkalaecom.brandmodelmgmt.repository.ProductImagesRepository;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class ProductImagesService {

	@Autowired
	ProductImagesRepository productImagesRepository;

	public ProductImages save(ProductImages productImages, String requestorId) {
		productImages.setCreatedBy(requestorId);
		productImages.setCreatedOn(CustomClock.timestamp());
		return productImagesRepository.save(productImages);
	}

	public ProductImages getProductImagesById(String productImagesId) {
		return productImagesRepository.getById(productImagesId);
	}

	public ProductImages update(ProductImages productImages, String requestorId) {
		productImages.setModifiedBy(requestorId);
		productImages.setModifiedOn(CustomClock.timestamp());
		productImages.setStatus(false);
		return productImagesRepository.save(productImages);
	}

	public ProductImages delete(String id, String requestorId) {
		ProductImages productImages = productImagesRepository.getById(id);
		productImages.setModifiedBy(requestorId);
		productImages.setModifiedOn(CustomClock.timestamp());
		productImages.setStatus(false);
		return productImagesRepository.save(productImages);
	}

}
