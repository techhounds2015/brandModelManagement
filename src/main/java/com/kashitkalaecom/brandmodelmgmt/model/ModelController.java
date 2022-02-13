package com.kashitkalaecom.brandmodelmgmt.model;

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

@RestController
@RequestMapping("/api/v1/model")
public class ModelController {

	@Autowired
	ModelService modelService;

	@Autowired
	ModelFV modelFV;
	
	@Autowired
	ModelBV modelBV;

	@PostMapping("/create")
	public APIResponse<Model> model(@RequestHeader String requestorId, @RequestBody Model model) {
		APIResponse<Model> apiResponse = new APIResponse<>();

		try {

			apiResponse = modelFV.fValidateCreate(null, model, null);
			if (Boolean.FALSE.equals(apiResponse.getValidationSuccess())) {
				return apiResponse;
			}
			
			apiResponse = modelBV.bValidateCreate(null, model, null);
            if (Boolean.FALSE.equals(apiResponse.getProcessingSuccess())) {
                return apiResponse;
            }

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

	@GetMapping("/view/{modelId}")
	public APIResponse<Model> model(@RequestHeader String requestorId, @PathVariable("modelId") String modelId) {

		int modelCount = modelService.modelIdExists(modelId);
		APIResponse<Model> apiResponse = new APIResponse<>();
		
		if (modelCount == 0){
			apiResponse.setResponseCode(StatusCodeEnum.MODEL_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.MODEL_NOT_EXISTS.getDesc());
			return apiResponse;
		}
		
		Model model = modelService.getModelById(modelId);
			
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(model);
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse<Model> updatemodel(@RequestHeader String requestorId, @RequestBody Model model) {

		APIResponse<Model> apiResponse = new APIResponse<>();
		try {
			
			apiResponse = modelBV.bValidateUpdate(null, model, null);
            if (Boolean.FALSE.equals(apiResponse.getProcessingSuccess())) {
                return apiResponse;
            }
			
			model = modelService.update(model, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.MODEL_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.MODEL_UPDATED.getDesc());
			apiResponse.setResponseObject(model);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.MODEL_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.MODEL_UPDATION_FAILED.getDesc());
		}
		return apiResponse;

	}

	@PutMapping("/delete/{id}")
	public APIResponse<Model> deletemodel(@RequestHeader String requestorId, @PathVariable String id) {

		APIResponse<Model> apiResponse = new APIResponse<>();
		try {
			
			int modelCount = modelService.modelIdExists(id);
			
			if (modelCount == 0){
				apiResponse.setResponseCode(StatusCodeEnum.MODEL_NOT_EXISTS.getCode());
				apiResponse.setResponseMessage(StatusCodeEnum.MODEL_NOT_EXISTS.getDesc());
				return apiResponse;
			}
			
			Model model = modelService.delete(id, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.MODEL_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.MODEL_UPDATED.getDesc());
			apiResponse.setResponseObject(model);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.MODEL_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.MODEL_UPDATION_FAILED.getDesc());
		}
		return apiResponse;
}
}	
