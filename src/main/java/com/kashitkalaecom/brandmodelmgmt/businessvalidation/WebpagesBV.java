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
import com.kashitkalaecom.brandmodelmgmt.models.Webpages;
import com.kashitkalaecom.brandmodelmgmt.service.CategoryService;
import com.kashitkalaecom.brandmodelmgmt.service.WebpagesService;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;
@Component
public class WebpagesBV {
	
	@Autowired
	MasterDataService masterDataService;
	
	@Autowired
	WebpagesService webpagesService;
	
	private static final Logger logger = LoggerFactory.getLogger(WebpagesBV.class);
	
	 public APIResponse<Webpages> bValidateCreate(String tenantCode, Webpages webpages, String locale) {
		 
		 APIResponse<Webpages> apiResponse = new APIResponse<Webpages>();
		 apiResponse.setProcessingSuccess(true);
		 List<String> list = new ArrayList<String>();
		try {
			apiResponse = validateCreate(tenantCode, webpages, locale);

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

	private APIResponse<Webpages> validateCreate(String tenantCode, Webpages webpages, String locale) {
		APIResponse<Webpages> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
        List<String> webpagesList = masterDataService.getDataNameByType(tenantCode, "Webpages");
        
        if (webpagesList != null && !webpagesList.contains(webpages.getContent())) {
        	apiResponse.setResponseCode(StatusCodeEnum.WEBPAGES_CONFIG_INVALID.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.WEBPAGES_CONFIG_INVALID.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        Webpages web = webpagesService.getWebpagesById(webpages.getTitle());
        
        if (web != null) {
        	apiResponse.setResponseCode(StatusCodeEnum.WEBPAGES_CONFIG_DUPLICATE.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.WEBPAGES_CONFIG_DUPLICATE.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        return apiResponse;
	}

}
