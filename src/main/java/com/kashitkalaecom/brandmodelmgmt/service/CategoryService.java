package com.kashitkalaecom.brandmodelmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.Category;
import com.kashitkalaecom.brandmodelmgmt.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public Category getCategoryById(String categoryId) {
		return categoryRepository.getById(categoryId);
	}

	public Category save(Category category, String requestorId) {
		category.setCreatedBy(requestorId);
		return categoryRepository.save(category);
	}

	public Category update(Category category, String requestorId) {
		category.setModifiedBy(requestorId);
		return categoryRepository.save(category);
	}

	public Category update1(Category category, String requestorId) {
		category.setModifiedBy(requestorId);
		category.setStatus(false);
		return categoryRepository.save(category);
	}
}
