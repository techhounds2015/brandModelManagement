package com.kashitkalaecom.brandmodelmgmt.businessvalidation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Category;
import com.kashitkalaecom.brandmodelmgmt.models.Discount;
import com.kashitkalaecom.brandmodelmgmt.service.DiscountService;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;
@Component
public class DiscountBV {
	
	@Autowired
	MasterDataService masterDataService;
	
	@Autowired
	DiscountService discountService;
	
	private static final Logger logger = LoggerFactory.getLogger(DiscountBV.class);
	
	 public APIResponse<Discount> bValidateCreate(String tenantCode, Discount discount, String locale) {
		 
		 APIResponse<Discount> apiResponse = new APIResponse<Discount>();
		 apiResponse.setProcessingSuccess(true);
		try {
			apiResponse = validateCreate(tenantCode, discount, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}
		return apiResponse;

}

	private APIResponse<Discount> validateCreate(String tenantCode, Discount discount, String locale) {
		
		APIResponse<Discount> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);       
        int dis = discountService.discountCodeCodeExists(discount.getCode());
        if (dis > 0 ) {
        	apiResponse.setResponseCode(StatusCodeEnum.DISCOUNT_CODE_DUPLICATE.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.DISCOUNT_CODE_DUPLICATE.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        return apiResponse;
	}

}
