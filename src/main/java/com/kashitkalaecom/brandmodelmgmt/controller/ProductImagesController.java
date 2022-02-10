package com.kashitkalaecom.brandmodelmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.models.Model;
import com.kashitkalaecom.brandmodelmgmt.models.ProductImages;
import com.kashitkalaecom.brandmodelmgmt.service.ModelService;
import com.kashitkalaecom.brandmodelmgmt.service.ProductImagesService;

@RestController
@RequestMapping("/api/v1/productImages")
public class ProductImagesController {
	
	@Autowired
	ProductImagesService productImagesService;
	
	@Autowired
	ModelService modelService;

	@PostMapping("/create")
	public APIResponse productImages(@RequestHeader String requestorId, @RequestBody ProductImages productImages) {

		productImages = productImagesService.save(productImages, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(productImages);
		return apiResponse;
	}

	@GetMapping("/view/{modelId}")
	public APIResponse model(@RequestHeader String requestorId, @PathVariable("modelId") String modelId) {

		Model model = modelService.getModelById(modelId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(model);
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse updatemodel(@RequestHeader String requestorId, @RequestBody Model model) {

		model = modelService.update(model, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(model);
		return apiResponse;
	}

	@PutMapping("/delete/{id}")
	public APIResponse deletemodel(@RequestHeader String requestorId, @PathVariable String id) {

		Model model = modelService.delete(id, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(model);
		return apiResponse;
	}

}
