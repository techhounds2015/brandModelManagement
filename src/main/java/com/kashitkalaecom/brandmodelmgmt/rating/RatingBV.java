package com.kashitkalaecom.brandmodelmgmt.rating;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.service.CategoryService;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;

@Component
public class RatingBV {

	@Autowired
	MasterDataService masterDataService;

	@Autowired
	RatingService ratingService;

	@Autowired
	CategoryService categoryService;

	private static final Logger logger = LoggerFactory.getLogger(RatingBV.class);

	public APIResponse<Ratings> bValidateCreate(String tenantCode, Ratings rating, String locale) {
		APIResponse<Ratings> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
		try {
			apiResponse = validateCreate(tenantCode, rating, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}

		return apiResponse;
	}

	public APIResponse<Ratings> bValidateUpdate(String tenantCode, Ratings rating, String locale) {
		APIResponse<Ratings> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);

		try {
			apiResponse = validateUpdate(tenantCode, rating, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}

		return apiResponse;
	}

	private APIResponse<Ratings> validateCreate(String tenantCode, Ratings rating, String locale) {

		APIResponse<Ratings> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);

		int ratingCount = 0 ;// = ratingService.ratingExists(rating.getProductId());

		if (ratingCount > 0) {
			apiResponse.setResponseCode(StatusCodeEnum.RATING_DUPLICATE.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.RATING_DUPLICATE.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		return apiResponse;
	}

	private APIResponse<Ratings> validateUpdate(String tenantCode, Ratings rating, String locale) {

		APIResponse<Ratings> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
		int ratingCount = 0 ; //= ratingService.ratingExists(rating.getProductId());
		if (ratingCount > 0) {
			apiResponse.setResponseCode(StatusCodeEnum.RATING_DUPLICATE.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.RATING_DUPLICATE.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}
		return apiResponse;
	}

}
