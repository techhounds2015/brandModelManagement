package com.kashitkalaecom.brandmodelmgmt.fieldvalidation;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Category;
import com.kashitkalaecom.brandmodelmgmt.models.PickUpAndDeliveryConfiguration;
import com.kashitkalaecom.brandmodelmgmt.validation.ValidationService;

@Component
public class PickUpAndDeliveryConfigurationFV {

	@Autowired
	ValidationService validationService;

	private static final Logger logger = LoggerFactory.getLogger(PickUpAndDeliveryConfigurationFV.class);

	private static String module = PickUpAndDeliveryConfiguration.class.getSimpleName();

	public APIResponse fValidateCreate(String tenantCode, PickUpAndDeliveryConfiguration pickUpAndDeliveryConfiguration,
			String locale) {

		APIResponse<PickUpAndDeliveryConfiguration> apiResponse = new APIResponse<>();
		apiResponse.setValidationSuccess(true);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try {
			hashMap = validateCreate(tenantCode, pickUpAndDeliveryConfiguration, locale);

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

	private HashMap<String, String> validateCreate(String tenantCode,
			PickUpAndDeliveryConfiguration pickUpAndDeliveryConfiguration, String locale) throws Exception {

		HashMap<String, String> hashMap = new HashMap<>();
		String errorString = null;

		errorString = validationService.validateField(tenantCode, module, pickUpAndDeliveryConfiguration.getOutletId(),
				"outletId", locale);
		if (errorString != null)
			hashMap.put("outletId", errorString);

		errorString = validationService.validateField(tenantCode, module, pickUpAndDeliveryConfiguration.getDistance(),
				"distance", locale);
		if (errorString != null)
			hashMap.put("distance", errorString);

		errorString = validationService.validateField(tenantCode, module,
				pickUpAndDeliveryConfiguration.getDileveryCharges(), "dileveryCharges", locale);
		if (errorString != null)
			hashMap.put("dileveryCharges", errorString);

		errorString = validationService.validateField(tenantCode, module,
				pickUpAndDeliveryConfiguration.getExtraPerKm(), "extraPerKm", locale);
		if (errorString != null)
			hashMap.put("extraPerKm", errorString);

		errorString = validationService.validateField(tenantCode, module,
				pickUpAndDeliveryConfiguration.getMinOrderAmountForNoDeliveryCharges(),
				"minOrderAmountForNoDeliveryCharges", locale);
		if (errorString != null)
			hashMap.put("minOrderAmountForNoDeliveryCharges", errorString);

		errorString = validationService.validateField(tenantCode, module,
				pickUpAndDeliveryConfiguration.getDeliveryChargesForBelowOrderAmount(),
				"deliveryChargesForBelowOrderAmount", locale);
		if (errorString != null)
			hashMap.put("deliveryChargesForBelowOrderAmount", errorString);

		errorString = validationService.validateField(tenantCode, module, pickUpAndDeliveryConfiguration.getStatus(),
				"status", locale);
		if (errorString != null)
			hashMap.put("status", errorString);

		return hashMap;
	}

}
