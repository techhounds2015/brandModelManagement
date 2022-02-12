package com.kashitkalaecom.brandmodelmgmt.fieldvalidation;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Brand;
import com.kashitkalaecom.brandmodelmgmt.models.DeliverySlots;
import com.kashitkalaecom.brandmodelmgmt.validation.ValidationService;

@Component
public class DeliverySlotsFV {

	@Autowired
	ValidationService validationService;

	private static final Logger logger = LoggerFactory.getLogger(DeliverySlotsFV.class);

	private static String module = DeliverySlots.class.getSimpleName();

	public APIResponse<DeliverySlots> fValidateCreate(String tenantCode, DeliverySlots deliverySlots, String locale) {
		APIResponse<DeliverySlots> apiResponse = new APIResponse<>();

		apiResponse.setValidationSuccess(true);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try {
			hashMap = validateCreate(tenantCode, deliverySlots, locale);

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

	private HashMap<String, String> validateCreate(String tenantCode, DeliverySlots deliverySlots, String locale) throws Exception {

		HashMap<String, String> hashMap = new HashMap<>();
		String errorString = null;

		errorString = validationService.validateField(tenantCode, module, deliverySlots.getAllDays(), "allDays", locale);
		if (errorString != null)
			hashMap.put("allDays", errorString);

		errorString = validationService.validateField(tenantCode, module, deliverySlots.getFriday(), "friday", locale);
		if (errorString != null)
			hashMap.put("friday", errorString);

		errorString = validationService.validateField(tenantCode, module, deliverySlots.getMonday(), "monday",
				locale);
		if (errorString != null)
			hashMap.put("monday", errorString);

		errorString = validationService.validateField(tenantCode, module, deliverySlots.getOutletId(), "outletId", locale);
		if (errorString != null)
			hashMap.put("outletId", errorString);

		errorString = validationService.validateField(tenantCode, module, deliverySlots.getSaturday(), "saturday", locale);
		if (errorString != null)
			hashMap.put("saturday", errorString);

		errorString = validationService.validateField(tenantCode, module, deliverySlots.getStartHour(), "startHour", locale);
		if (errorString != null)
			hashMap.put("startHour", errorString);

		errorString = validationService.validateField(tenantCode, module, deliverySlots.getSunday(), "sunday", locale);
		if (errorString != null)
			hashMap.put("sunday", errorString);

		errorString = validationService.validateField(tenantCode, module, deliverySlots.getThursday(), "thursday", locale);
		if (errorString != null)
			hashMap.put("thursday", errorString);

		errorString = validationService.validateField(tenantCode, module, deliverySlots.getTuesday(), "tuesday", locale);
		if (errorString != null)
			hashMap.put("tuesday", errorString);

		errorString = validationService.validateField(tenantCode, module, deliverySlots.getWednesday(), "wednesday", locale);
		if (errorString != null)
			hashMap.put("wednesday", errorString);

		errorString = validationService.validateField(tenantCode, module, deliverySlots.getEndHour(), "endHour", locale);
		if (errorString != null)
			hashMap.put("endHour", errorString);

		errorString = validationService.validateField(tenantCode, module, deliverySlots.getMaxOrders(), "maxOrder", locale);
		if (errorString != null)
			hashMap.put("maxOrder", errorString);

		errorString = validationService.validateField(tenantCode, module, deliverySlots.getStatus(), "status", locale);
		if (errorString != null)
			hashMap.put("status", errorString);
	
		return hashMap;
	}
}
