package com.kashitkalaecom.brandmodelmgmt.complain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;

@Component
public class ComplainBV {

	@Autowired
	ComplainService complainService;

	private static final Logger logger = LoggerFactory.getLogger(ComplainBV.class);

	public APIResponse<Complain> bValidateCreate(String tenantCode, Complain complain, String locale) {
		APIResponse<Complain> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
		try {
			apiResponse = validateCreate(tenantCode, complain, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}

		return apiResponse;
	}

	private APIResponse<Complain> validateCreate(String tenantCode, Complain complain, String locale) {
		APIResponse<Complain> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);

		int complainCount = 0;// = ratingService.ratingExists(rating.getProductId());

		if (complainCount > 0) {
			apiResponse.setResponseCode(StatusCodeEnum.COMPLAIN_DUPLICATE.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.COMPLAIN_DUPLICATE.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		return apiResponse;
	}

}
