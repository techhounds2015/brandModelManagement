package com.kashitkalaecom.brandmodelmgmt.fieldvalidation;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Product;
import com.kashitkalaecom.brandmodelmgmt.models.StoreSetting;
import com.kashitkalaecom.brandmodelmgmt.models.User;
import com.kashitkalaecom.brandmodelmgmt.validation.ValidationService;
@Component
public class StoreSettingFV {
	
	@Autowired
	ValidationService validationService;
	
	private static final Logger logger = LoggerFactory.getLogger(StoreSettingFV.class);

	private static String module = User.class.getSimpleName();
	public APIResponse fValidateCreate(String tenantCode, StoreSetting storeSetting, String locale) {
		
		APIResponse<StoreSetting> apiResponse = new APIResponse<>();

		apiResponse.setValidationSuccess(true);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try {
			hashMap = validateCreate(tenantCode, storeSetting, locale);

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
	private HashMap<String, String> validateCreate(String tenantCode, StoreSetting storeSetting, String locale) throws Exception {
		
		HashMap<String, String> hashMap = new HashMap<>();
		String errorString = null;

		errorString = validationService.validateField(tenantCode, module, storeSetting.getStoreName(), "storeName",
				locale);
		if (errorString != null)
			hashMap.put("storeName", errorString);

		errorString = validationService.validateField(tenantCode, module, storeSetting.getAddress(), "address", locale);
		if (errorString != null)
			hashMap.put("address", errorString);

		errorString = validationService.validateField(tenantCode, module, storeSetting.getCity(), "city", locale);
		if (errorString != null)
			hashMap.put("city", errorString);

		errorString = validationService.validateField(tenantCode, module, storeSetting.getState(), "state", locale);
		if (errorString != null)
			hashMap.put("state", errorString);

		errorString = validationService.validateField(tenantCode, module, storeSetting.getPinCode(), "pinCode", locale);
		if (errorString != null)
			hashMap.put("pinCode", errorString);

		errorString = validationService.validateField(tenantCode, module, storeSetting.getCountry(),
				"country", locale);
		if (errorString != null)
			hashMap.put("country", errorString);

		errorString = validationService.validateField(tenantCode, module, storeSetting.getSupportEmail(), "supportEmail", locale);
		if (errorString != null)
			hashMap.put("supportEmail", errorString);

		errorString = validationService.validateField(tenantCode, module, storeSetting.getSupportPhoneNumber(),
				"supportPhoneNumber", locale);
		if (errorString != null)
			hashMap.put("supportPhoneNumber", errorString);

		errorString = validationService.validateField(tenantCode, module, storeSetting.getCompanyName(),
				"companyName", locale);
		if (errorString != null)
			hashMap.put("companyName", errorString);

		errorString = validationService.validateField(tenantCode, module, storeSetting.getGstNumber(), "gstNumber", locale);
		if (errorString != null)
			hashMap.put("gstNumber", errorString);

		errorString = validationService.validateField(tenantCode, module, storeSetting.getPlayStoreLink(), "playStoreLink", locale);
		if (errorString != null)
			hashMap.put("playStoreLink", errorString);

		errorString = validationService.validateField(tenantCode, module, storeSetting.getInstagramLink(), "instagramLink",
				locale);
		if (errorString != null)
			hashMap.put("instagramLink", errorString);

		errorString = validationService.validateField(tenantCode, module, storeSetting.getFacebookLink(), "facebookLink", locale);
		if (errorString != null)
			hashMap.put("facebookLink", errorString);

		errorString = validationService.validateField(tenantCode, module, storeSetting.getAppStoreLink(), "appStoreLink", locale);
		if (errorString != null)
			hashMap.put("appStoreLink", errorString);

		errorString = validationService.validateField(tenantCode, module, storeSetting.getTwitterLink(), "twitterLink", locale);
		if (errorString != null)
			hashMap.put("twitterLink", errorString);

		errorString = validationService.validateField(tenantCode, module, storeSetting.getPhoneNumberOnInvoice(), "phoneNumberOnInvoice", locale);
		if (errorString != null)
			hashMap.put("phoneNumberOnInvoice", errorString);

		errorString = validationService.validateField(tenantCode, module, storeSetting.getWhatsAppNumber(), "whatsAppNumber",
				locale);
		if (errorString != null)
			hashMap.put("whatsAppNumber", errorString);

		errorString = validationService.validateField(tenantCode, module, storeSetting.getLogo(),
				"logo", locale);
		if (errorString != null)
			hashMap.put("logo", errorString);

		errorString = validationService.validateField(tenantCode, module, storeSetting.getFavicon(), "favicon",
				locale);
		if (errorString != null)
			hashMap.put("favicon", errorString);

		errorString = validationService.validateField(tenantCode, module, storeSetting.getStatus(), "status", locale);
		if (errorString != null)
			hashMap.put("status", errorString);

		return hashMap;
	}

}
