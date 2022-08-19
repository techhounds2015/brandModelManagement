package com.kashitkalaecom.brandmodelmgmt.complain;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.validation.ValidationService;

@Component
public class ComplainFV {

	@Autowired
	ValidationService validationService;

	private static final Logger logger = LoggerFactory.getLogger(ComplainFV.class);
	private static String module = Complain.class.getSimpleName();

	public APIResponse<Complain> fValidateCreate(String tenantCode, Complain complain, String locale) {
		APIResponse<Complain> apiResponse = new APIResponse<>();

		apiResponse.setValidationSuccess(true);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try {
			hashMap = validateCreate(tenantCode, complain, locale);

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

	private HashMap<String, String> validateCreate(String tenantCode, Complain complain, String locale)
			throws Exception {
		HashMap<String, String> hashMap = new HashMap<>();
		String errorString = null;

		errorString = validationService.validateField(tenantCode, module, complain.getTitle(), "title", locale);
		if (errorString != null)
			hashMap.put("title", errorString);

		errorString = validationService.validateField(tenantCode, module, complain.getComplainNumber(), "complainNumber",
				locale);
		if (errorString != null)
			hashMap.put("complainNumber", errorString);

		errorString = validationService.validateField(tenantCode, module, complain.getCustomerId(), "customerId",
				locale);
		if (errorString != null)
			hashMap.put("customerId", errorString);

		errorString = validationService.validateField(tenantCode, module, complain.getComments(), "comments", locale);
		if (errorString != null)
			hashMap.put("comments", errorString);

		return hashMap;
	}

}
