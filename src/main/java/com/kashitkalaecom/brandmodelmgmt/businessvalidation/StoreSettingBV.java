package com.kashitkalaecom.brandmodelmgmt.businessvalidation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Category;
import com.kashitkalaecom.brandmodelmgmt.models.StoreSetting;
import com.kashitkalaecom.brandmodelmgmt.service.CategoryService;
import com.kashitkalaecom.brandmodelmgmt.service.StoreSettingService;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;

@Component
public class StoreSettingBV {
	
	@Autowired
	MasterDataService masterDataService;
	
	@Autowired
	StoreSettingService storeSettingService;
	
	private static final Logger logger = LoggerFactory.getLogger(StoreSettingBV.class);
	
	public APIResponse bValidateCreate(String tenantCode, StoreSetting storeSetting, String locale) {
		
		APIResponse<StoreSetting> apiResponse = new APIResponse<>();
		 apiResponse.setProcessingSuccess(true);
		try {
			apiResponse = validateCreate(tenantCode, storeSetting, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}
	
		return apiResponse;
	}

	private APIResponse<StoreSetting> validateCreate(String tenantCode, StoreSetting storeSetting, String locale) {
		
		APIResponse<StoreSetting> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
       
        
        StoreSetting storeSetting1 = storeSettingService.getStoreSettingByName(storeSetting.getCompanyName());
        
        if (storeSetting1 != null) {
        	apiResponse.setResponseCode(StatusCodeEnum.STORE_NOT_EXISTS.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.STORE_NOT_EXISTS.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        return apiResponse;
	}

}
