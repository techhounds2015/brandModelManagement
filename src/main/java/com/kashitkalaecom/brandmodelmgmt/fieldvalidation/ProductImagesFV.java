package com.kashitkalaecom.brandmodelmgmt.fieldvalidation;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.ProductImages;
import com.kashitkalaecom.brandmodelmgmt.validation.ValidationService;

@Component
public class ProductImagesFV {

	@Autowired
	ValidationService validationService;

	private static final Logger logger = LoggerFactory.getLogger(ProductImagesFV.class);

	private static String module = ProductImages.class.getSimpleName();

	public APIResponse<ProductImages> fValidateCreate(String tenantCode, ProductImages productImages, String locale) {

		APIResponse<ProductImages> apiResponse = new APIResponse<>();

		apiResponse.setValidationSuccess(true);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try {
			hashMap = validateCreate(tenantCode, productImages, locale);

		} catch (Exception e) {
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
