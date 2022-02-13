package com.kashitkalaecom.brandmodelmgmt.fieldvalidation;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Outlet;
import com.kashitkalaecom.brandmodelmgmt.validation.ValidationService;

@Component
public class OutletFV {

	@Autowired
	ValidationService validationService;

	private static final Logger logger = LoggerFactory.getLogger(OutletFV.class);

	private static String module = Outlet.class.getSimpleName();

	public APIResponse<Outlet> fValidateCreate(String tenantCode, Outlet outlet, String locale) {
		APIResponse<Outlet> apiResponse = new APIResponse<>();

		apiResponse.setValidationSuccess(true);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try {
			hashMap = validateCreate(tenantCode, outlet, locale);

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

	private HashMap<String, String> validateCreate(String tenantCode, Outlet outlet, String locale) throws Exception {

		HashMap<String, String> hashMap = new HashMap<>();
		String errorString = null;

		errorString = validationService.validateField(tenantCode, module, outlet.getGstNo(), "gstNo", locale);
		if (errorString != null)
			hashMap.put("gstNo", errorString);

		errorString = validationService.validateField(tenantCode, module, outlet.getLatitude(), "latitude", locale);
		if (errorString != null)
			hashMap.put("latitude", errorString);

		errorString = validationService.validateField(tenantCode, module, outlet.getLongitude(), "longitude", locale);
		if (errorString != null)
			hashMap.put("longitude", errorString);
		
		errorString = validationService.validateField(tenantCode, module, outlet.getName(), "name", locale);
		if (errorString != null)
			hashMap.put("name", errorString);
		
		errorString = validationService.validateField(tenantCode, module, outlet.getStatus(), "status", locale);
		if (errorString != null)
			hashMap.put("status", errorString);
		
		return hashMap;
	}
}
