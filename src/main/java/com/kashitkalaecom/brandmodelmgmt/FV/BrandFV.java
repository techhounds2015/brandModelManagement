package com.kashitkalaecom.brandmodelmgmt.FV;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.models.Brand;
import com.kashitkalaecom.brandmodelmgmt.validation.ValidationService;

@Component
public class BrandFV {

	@Autowired
	ValidationService validationService;

	private static final Logger logger = LoggerFactory.getLogger(BrandFV.class);

	private static String module = Brand.class.getSimpleName();

	public APIResponse<Brand> fValidateCreate(String tenantCode, Brand brand, String locale) {
		APIResponse<Brand> apiResponse = new APIResponse<>();

		apiResponse.setValidationSuccess(true);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try {
			hashMap = validateCreate(tenantCode, brand, locale);

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

	private HashMap<String, String> validateCreate(String tenantCode, Brand brand, String locale) throws Exception {

		HashMap<String, String> hashMap = new HashMap<>();
		String errorString = null;

		errorString = validationService.validateField(tenantCode, module, brand.getCategoryId(), "categoryId", locale);
		if (errorString != null)
			hashMap.put("category", errorString);

		errorString = validationService.validateField(tenantCode, module, brand.getLogo(), "logo", locale);
		if (errorString != null)
			hashMap.put("category", errorString);

		errorString = validationService.validateField(tenantCode, module, brand.getDescription(), "description",
				locale);
		if (errorString != null)
			hashMap.put("description", errorString);

		errorString = validationService.validateField(tenantCode, module, brand.getName(), "name", locale);
		if (errorString != null)
			hashMap.put("name", errorString);

		errorString = validationService.validateField(tenantCode, module, brand.getStatus(), "status", locale);
		if (errorString != null)
			hashMap.put("status", errorString);

		return hashMap;
	}
}
