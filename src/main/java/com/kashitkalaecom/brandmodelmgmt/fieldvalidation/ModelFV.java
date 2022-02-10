package com.kashitkalaecom.brandmodelmgmt.fieldvalidation;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Model;
import com.kashitkalaecom.brandmodelmgmt.validation.ValidationService;
@Component
public class ModelFV {

	@Autowired
	ValidationService validationService;
	
	private static final Logger logger = LoggerFactory.getLogger(ModelFV.class);

	private static String module = Model.class.getSimpleName();

	public APIResponse<Model> fValidateCreate(String tenantCode, Model model, String locale) {
		APIResponse<Model> apiResponse = new APIResponse<>();
		
		apiResponse.setValidationSuccess(true);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try {
			hashMap = validateCreate(tenantCode, model, locale);

		}  catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.ERROR_ON_VALIDATING_REQUEST.getCode());
			apiResponse.setResponseCode(StatusCodeEnum.ERROR_ON_VALIDATING_REQUEST.getDesc());
			apiResponse.setValidationSuccess(false);
			logger.error("error while validating request", e);
			return apiResponse;
		}
		if (hashMap.size() > 0) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setValidationSuccess(false);
			apiResponse.setErrors(hashMap);

		}
		return apiResponse;

	}

	private HashMap<String, String> validateCreate(String tenantCode, Model model, String locale) throws Exception {
		
		HashMap<String, String> hashMap = new HashMap<>();
		String errorString = null;

		errorString = validationService.validateField(tenantCode, module, model.getCategoryId(), "categoryId", locale);
		if (errorString != null)
			hashMap.put("categoryId", errorString);
		
		errorString = validationService.validateField(tenantCode, module, model.getBrandId(), "brandId", locale);
		if (errorString != null)
			hashMap.put("brandId", errorString);
		
		errorString = validationService.validateField(tenantCode, module, model.getDescription(), "description", locale);
		if (errorString != null)
			hashMap.put("description", errorString);
		
		errorString = validationService.validateField(tenantCode, module, model.getName(), "name", locale);
		if (errorString != null)
			hashMap.put("name", errorString);
		
		errorString = validationService.validateField(tenantCode, module, model.getStatus(), "status", locale);
		if (errorString != null)
			hashMap.put("status", errorString);
		
		return hashMap;
	}
}
