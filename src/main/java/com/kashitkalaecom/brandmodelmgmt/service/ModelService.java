package com.kashitkalaecom.brandmodelmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.Model;
import com.kashitkalaecom.brandmodelmgmt.repository.ModelRepository;

@Service
public class ModelService {

	@Autowired
	ModelRepository modelRepository;

	public Model getModelById(String modelId) {
		return modelRepository.getById(modelId);
	}

	public Model save(Model model, String requestorId) {
		model.setCreatedBy(requestorId);
		return modelRepository.save(model);
	}
	
	public Model update(Model model, String requestorId) {
		model.setModifiedBy(requestorId);
		model.setStatus(false);
		return modelRepository.save(model);
	}
	
	public Model update1(Model model, String requestorId) {
		model.setModifiedBy(requestorId);
		model.setStatus(false);
		return modelRepository.save(model);
	}
}
