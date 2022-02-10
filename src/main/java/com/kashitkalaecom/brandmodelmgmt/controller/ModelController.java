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
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.fieldvalidation.ModelFV;
import com.kashitkalaecom.brandmodelmgmt.models.Model;
import com.kashitkalaecom.brandmodelmgmt.service.ModelService;

@RestController
@RequestMapping("/api/v1/model")
public class ModelController {

	@Autowired
	ModelService modelService;

	@Autowired
	ModelFV modelFV;

	@PostMapping("/create")
	public APIResponse model(@RequestHeader String requestorId, @RequestBody Model model) {
		APIResponse apiResponse = new APIResponse();

		try {

			apiResponse = modelFV.fValidateCreate(null, model, null);
			if (!apiResponse.getValidationSuccess()) {
				return apiResponse;
			}
			// Business Validation

			// Save
			model = modelService.save(model, requestorId);

			apiResponse.setResponseCode(StatusCodeEnum.MODEL_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.MODEL_CREATED.getDesc());
			apiResponse.setResponseObject(model);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.MODEL_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.MODEL_CREATION_FAILED.getDesc());
			apiResponse.setResponseObject(model);
		}

		return apiResponse;
	}

	/*
	 * @PostMapping("/create") public APIResponse model(@RequestHeader String
	 * requestorId, @RequestBody Model model) {
	 * 
	 * model = modelService.save(model, requestorId); APIResponse apiResponse = new
	 * APIResponse(); apiResponse.setResponseCode("200");
	 * apiResponse.setResponseMessage("success");
	 * apiResponse.setResponseObject(model); return apiResponse; }
	 */

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
