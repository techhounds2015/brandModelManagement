package com.kashitkalaecom.brandmodelmgmt.fieldvalidation;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.User;
import com.kashitkalaecom.brandmodelmgmt.validation.ValidationService;

@Component
public class UserFV {

	@Autowired
	ValidationService validationService;

	private static final Logger logger = LoggerFactory.getLogger(UserFV.class);

	private static String module = User.class.getSimpleName();

	public APIResponse fValidateCreate(String tenantCode, User user, String locale) {
		APIResponse<User> apiResponse = new APIResponse<>();

		apiResponse.setValidationSuccess(true);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try {
			hashMap = validateCreate(tenantCode, user, locale);

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

	private HashMap<String, String> validateCreate(String tenantCode, User user, String locale) throws Exception {
		HashMap<String, String> hashMap = new HashMap<>();
		String errorString = null;

		errorString = validationService.validateField(tenantCode, module, user.getEmail(), "email", locale);
		if (errorString != null)
			hashMap.put("email", errorString);

		errorString = validationService.validateField(tenantCode, module, user.getMobile(), "mobile", locale);
		if (errorString != null)
			hashMap.put("mobile", errorString);

		errorString = validationService.validateField(tenantCode, module, user.getUserId(), "userId", locale);
		if (errorString != null)
			hashMap.put("userId", errorString);

		errorString = validationService.validateField(tenantCode, module, user.getWalletId(), "walletId", locale);
		if (errorString != null)
			hashMap.put("walletId", errorString);

		errorString = validationService.validateField(tenantCode, module, user.getRoleId(), "roleId", locale);
		if (errorString != null)
			hashMap.put("roleId", errorString);

		errorString = validationService.validateField(tenantCode, module, user.getSignUpDate(), "signUpDate", locale);
		if (errorString != null)
			hashMap.put("signUpDate", errorString);

		errorString = validationService.validateField(tenantCode, module, user.getStatus(), "status", locale);
		if (errorString != null)
			hashMap.put("status", errorString);

		return hashMap;
	}

}
