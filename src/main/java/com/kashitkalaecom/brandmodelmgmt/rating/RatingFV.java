package com.kashitkalaecom.brandmodelmgmt.rating;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.validation.ValidationService;

@Component
public class RatingFV {

	@Autowired
	ValidationService validationService;

	private static final Logger logger = LoggerFactory.getLogger(RatingFV.class);
	private static String module = Ratings.class.getSimpleName();

	public APIResponse<Ratings> fValidateCreate(String tenantCode, Ratings rating, String locale) {
		APIResponse<Ratings> apiResponse = new APIResponse<>();

		apiResponse.setValidationSuccess(true);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try {
			hashMap = validateCreate(tenantCode, rating, locale);

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

	private HashMap<String, String> validateCreate(String tenantCode, Ratings rating, String locale) throws Exception {
		HashMap<String, String> hashMap = new HashMap<>();
		String errorString = null;

		errorString = validationService.validateField(tenantCode, module, rating.getProductId(), "productId", locale);
		if (errorString != null)
			hashMap.put("productId", errorString);

		errorString = validationService.validateField(tenantCode, module, rating.getUserId(), "userId", locale);
		if (errorString != null)
			hashMap.put("userId", errorString);

		errorString = validationService.validateField(tenantCode, module, rating.getRating(), "rating", locale);
		if (errorString != null)
			hashMap.put("rating", errorString);

		errorString = validationService.validateField(tenantCode, module, rating.getComments(), "comments", locale);
		if (errorString != null)
			hashMap.put("comments", errorString);

		return hashMap;
	}
}
