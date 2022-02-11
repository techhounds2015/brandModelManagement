package com.kashitkalaecom.brandmodelmgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.Category;
import com.kashitkalaecom.brandmodelmgmt.repository.CategoryRepository;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public Category getCategoryById(String categoryId) {
		return categoryRepository.getById(categoryId);
	}
	
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	public Category save(Category category, String requestorId) {
		category.setCreatedBy(requestorId);
		category.setCreatedOn(CustomClock.timestamp());
		return categoryRepository.save(category);
	}

	public Category update(Category category, String requestorId) {
		category.setModifiedBy(requestorId);
		category.setModifiedOn(CustomClock.timestamp());
		return categoryRepository.save(category);
	}

	public Category delete(String id, String requestorId) {
		Category category = categoryRepository.getById(id);
		category.setModifiedBy(requestorId);
		category.setModifiedOn(CustomClock.timestamp());
		category.setStatus(false);
		return categoryRepository.save(category);
	}

	public Category getCategoryByName(String name) {
		return categoryRepository.findByName(name);
	}
}
