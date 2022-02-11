package com.kashitkalaecom.brandmodelmgmt.businessvalidation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Category;
import com.kashitkalaecom.brandmodelmgmt.models.PickUpAndDeliveryConfiguration;
import com.kashitkalaecom.brandmodelmgmt.service.CategoryService;
import com.kashitkalaecom.brandmodelmgmt.service.PickUpAndDeliveryConfigurationService;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;
@Component
public class PickUpAndDeliveryConfigurationBV {

	@Autowired
	MasterDataService masterDataService;
	
	@Autowired
	 PickUpAndDeliveryConfigurationService  pickUpAndDeliveryConfigurationService;
	
	private static final Logger logger = LoggerFactory.getLogger(PickUpAndDeliveryConfigurationBV.class);
	
	public APIResponse bValidateCreate(String tenantCode, PickUpAndDeliveryConfiguration pickUpAndDeliveryConfiguration,
			String locale) {
		
		APIResponse<PickUpAndDeliveryConfiguration> apiResponse = new APIResponse<>();
		 apiResponse.setProcessingSuccess(true);
		try {
			apiResponse = validateCreate(tenantCode, pickUpAndDeliveryConfiguration, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}
	
		return apiResponse;
	}

	private APIResponse<PickUpAndDeliveryConfiguration> validateCreate(String tenantCode,
			PickUpAndDeliveryConfiguration pickUpAndDeliveryConfiguration, String locale) {
		
		APIResponse<PickUpAndDeliveryConfiguration> apiResponse = new APIResponse<>();
        List<String> pickUpAndDeliveryConfigurationList = masterDataService.getDataNameByType(tenantCode, "PickUpAndDeliveryConfiguration");
        
        if (pickUpAndDeliveryConfigurationList != null && !pickUpAndDeliveryConfigurationList.contains(pickUpAndDeliveryConfiguration.getOutletId())) {
        	apiResponse.setResponseCode(StatusCodeEnum.CATEGORY_INVALID.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.CATEGORY_INVALID.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        PickUpAndDeliveryConfiguration pickUpAndDeliveryConfiguration1 = pickUpAndDeliveryConfigurationService.getpickUpAndDeliveryConfigurationByName(pickUpAndDeliveryConfiguration.getDileveryCharges());
        
        if (pickUpAndDeliveryConfiguration1 != null) {
        	apiResponse.setResponseCode(StatusCodeEnum.OUTLET_NOT_EXISTS.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.OUTLET_NOT_EXISTS.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        return apiResponse;
	}

}
