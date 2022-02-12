package com.kashitkalaecom.brandmodelmgmt.fieldvalidation;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Brand;
import com.kashitkalaecom.brandmodelmgmt.models.Referral;
import com.kashitkalaecom.brandmodelmgmt.validation.ValidationService;

@Component
public class ReferralFV {

	@Autowired
	ValidationService validationService;

	private static final Logger logger = LoggerFactory.getLogger(ReferralFV.class);

	private static String module = Referral.class.getSimpleName();

	public APIResponse<Referral> fValidateCreate(String tenantCode, Referral referral, String locale) {
		APIResponse<Referral> apiResponse = new APIResponse<>();

		apiResponse.setValidationSuccess(true);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try {
			hashMap = validateCreate(tenantCode, referral, locale);

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

	private HashMap<String, String> validateCreate(String tenantCode, Referral referral, String locale) throws Exception {

		HashMap<String, String> hashMap = new HashMap<>();
		String errorString = null;

		errorString = validationService.validateField(tenantCode, module, referral.getCode(), "code", locale);
		if (errorString != null)
			hashMap.put("code", errorString);

		errorString = validationService.validateField(tenantCode, module, referral.getDiscountForSender(), "discountForSender", locale);
		if (errorString != null)
			hashMap.put("discountForSender", errorString);

		errorString = validationService.validateField(tenantCode, module, referral.getDiscountForUser(), "discountForUser",
				locale);
		if (errorString != null)
			hashMap.put("discountForUser", errorString);

		errorString = validationService.validateField(tenantCode, module, referral.getDiscountType(), "discountType", locale);
		if (errorString != null)
			hashMap.put("discountType", errorString);

		errorString = validationService.validateField(tenantCode, module, referral.getMaxDiscount(), "maxDiscount", locale);
		if (errorString != null)
			hashMap.put("maxDiscount", errorString);

		errorString = validationService.validateField(tenantCode, module, referral.getTitle(), "title", locale);
		if (errorString != null)
			hashMap.put("title", errorString);
		
		errorString = validationService.validateField(tenantCode, module, referral.getExpiryDate(), "expiryDate", locale);
		if (errorString != null)
			hashMap.put("expiryDate", errorString);
		
		errorString = validationService.validateField(tenantCode, module, referral.getStartingDate(), "startingDate", locale);
		if (errorString != null)
			hashMap.put("startingDate", errorString);
		
		errorString = validationService.validateField(tenantCode, module, referral.getStatus(), "status", locale);
		if (errorString != null)
			hashMap.put("status", errorString);
		return hashMap;
	}
}
