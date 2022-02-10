package com.kashitkalaecom.brandmodelmgmt.fieldvalidation;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.models.Category;
import com.kashitkalaecom.brandmodelmgmt.validation.ValidationService;

@Component
public class CategoryFV {
	
	@Autowired
	ValidationService validationService;
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryFV.class);

	private static String module = Category.class.getSimpleName();

	public APIResponse<Category> fValidateCreate(String tenantCode, Category category, String locale) {
		APIResponse<Category> apiResponse = new APIResponse<>();
		
		apiResponse.setValidationSuccess(true);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try {
			hashMap = validateCreate(tenantCode, category, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage("FAILURE");
			apiResponse.setResponseCode("8000");
			apiResponse.setValidationSuccess(false);
			logger.error("error while validating request", e);
			return apiResponse;
		}
		if (hashMap.size() > 0) {
			apiResponse.setResponseMessage("FAILURE");
			apiResponse.setResponseCode("8000");
			apiResponse.setValidationSuccess(false);
			apiResponse.setErrors(hashMap);

		}
		return apiResponse;

	}

	private HashMap<String, String> validateCreate(String tenantCode, Category category, String locale) throws Exception {
		
		HashMap<String, String> hashMap = new HashMap<>();
		String errorString = null;

		errorString = validationService.validateField(tenantCode, module, category.getCategory(), "category", locale);
		if (errorString != null)
			hashMap.put("category", errorString);
		
		errorString = validationService.validateField(tenantCode, module, category.getParentCategory(), "parentCategory", locale);
		if (errorString != null)
			hashMap.put("parentCategory", errorString);
		
		errorString = validationService.validateField(tenantCode, module, category.getFriendlyName(), "friendlyName", locale);
		if (errorString != null)
			hashMap.put("friendlyName", errorString);
		
		errorString = validationService.validateField(tenantCode, module, category.getDescription(), "description", locale);
		if (errorString != null)
			hashMap.put("description", errorString);
		
		errorString = validationService.validateField(tenantCode, module, category.getName(), "name", locale);
		if (errorString != null)
			hashMap.put("name", errorString);
		
		errorString = validationService.validateField(tenantCode, module, category.getStatus(), "status", locale);
		if (errorString != null)
			hashMap.put("status", errorString);
		
		return hashMap;
	}

}
