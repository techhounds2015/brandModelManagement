package com.kashitkalaecom.brandmodelmgmt.FV;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.models.Product;
import com.kashitkalaecom.brandmodelmgmt.models.ProductImages;
import com.kashitkalaecom.brandmodelmgmt.validation.ValidationService;

@Component
public class ProductImagesFV {

	@Autowired
	ValidationService validationService;

	private static final Logger logger = LoggerFactory.getLogger(ProductImagesFV.class);

	private static String module = ProductImages.class.getSimpleName();

	public APIResponse fValidateCreate(String tenantCode, ProductImages productImages, String locale) {

		APIResponse<Product> apiResponse = new APIResponse<>();

		apiResponse.setValidationSuccess(true);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try {
			hashMap = validateCreate(tenantCode, productImages, locale);

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

	private HashMap<String, String> validateCreate(String tenantCode, ProductImages productImages, String locale)
			throws Exception {

		HashMap<String, String> hashMap = new HashMap<>();
		String errorString = null;

		errorString = validationService.validateField(tenantCode, module, productImages.getProductId(), "productId",
				locale);
		if (errorString != null)
			hashMap.put("productId", errorString);

		errorString = validationService.validateField(tenantCode, module, productImages.getPath(), "path", locale);
		if (errorString != null)
			hashMap.put("path", errorString);

		errorString = validationService.validateField(tenantCode, module, productImages.getImageName(), "imageName",
				locale);
		if (errorString != null)
			hashMap.put("imageName", errorString);

		errorString = validationService.validateField(tenantCode, module, productImages.getSize(), "size", locale);
		if (errorString != null)
			hashMap.put("size", errorString);

		errorString = validationService.validateField(tenantCode, module, productImages.getStatus(), "status", locale);
		if (errorString != null)
			hashMap.put("status", errorString);

		return hashMap;
	}

}
