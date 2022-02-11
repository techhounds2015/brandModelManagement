package com.kashitkalaecom.brandmodelmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.Product;
import com.kashitkalaecom.brandmodelmgmt.repository.ProductRepository;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public Product save(Product product, String requestorId) {
		product.setCreatedBy(requestorId);
		product.setCreatedOn(CustomClock.timestamp());
		return productRepository.save(product);
	}

	public Product getProductById(String productId) {
		return productRepository.getById(productId);
	}

	public Product update(Product product, String requestorId) {
		product.setModifiedBy(requestorId);
		product.setModifiedOn(CustomClock.timestamp());
		product.setStatus(false);
		return productRepository.save(product);
	}

	public Product delete(String id, String requestorId) {
		Product product = productRepository.getById(id);
		product.setModifiedBy(requestorId);
		product.setModifiedOn(CustomClock.timestamp());
		product.setStatus(false);
		return productRepository.save(product);
	}

	public Product getproductByCategoryId(String categoryId) {
		return productRepository.findByName(categoryId);
	}

}
