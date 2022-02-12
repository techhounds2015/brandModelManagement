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
import com.kashitkalaecom.brandmodelmgmt.models.HomeSlider;
import com.kashitkalaecom.brandmodelmgmt.models.Webpages;
import com.kashitkalaecom.brandmodelmgmt.service.HomeSliderService;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;
@Component
public class HomeSliderBV {
	
	@Autowired
	MasterDataService masterDataService;
	
	@Autowired
	HomeSliderService homeSliderService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeSliderBV.class);
	
	 public APIResponse<HomeSlider> bValidateCreate(String tenantCode, HomeSlider homeSlider, String locale) {
		 
		 APIResponse<HomeSlider> apiResponse = new APIResponse<HomeSlider>();
		 apiResponse.setProcessingSuccess(true);
		 List<String> list = new ArrayList<String>();
		try {
			apiResponse = validateCreate(tenantCode, homeSlider, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}
		if (list.size() > 0) {
			apiResponse.setResponseMessage("FAILURE");
			apiResponse.setResponseCode("9000");
			apiResponse.setProcessingErrors(list);
			apiResponse.setProcessingSuccess(false);

		}
		return apiResponse;

}

	private APIResponse<HomeSlider> validateCreate(String tenantCode, HomeSlider homeSlider, String locale) {
		
		APIResponse<HomeSlider> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
        List<String> homeSliderList = masterDataService.getDataNameByType(tenantCode, "HomeSlider");
        
        if (homeSliderList != null && !homeSliderList.contains(homeSlider.getImage())) {
        	apiResponse.setResponseCode(StatusCodeEnum.HOMESLIDER_CONFIG_INVALID.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.HOMESLIDER_CONFIG_INVALID.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        HomeSlider hslider = homeSliderService.getHomeSliderById(homeSlider.getImage());
        
        if (hslider != null) {
        	apiResponse.setResponseCode(StatusCodeEnum.HOMESLIDER_CONFIG_DUPLICATE.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.HOMESLIDER_CONFIG_DUPLICATE.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        return apiResponse;
	}

}
