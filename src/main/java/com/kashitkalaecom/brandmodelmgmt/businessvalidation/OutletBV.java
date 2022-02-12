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
import com.kashitkalaecom.brandmodelmgmt.models.Outlet;
import com.kashitkalaecom.brandmodelmgmt.models.Webpages;
import com.kashitkalaecom.brandmodelmgmt.service.OutletService;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;
@Component
public class OutletBV {
	
	@Autowired
	MasterDataService masterDataService;
	
	@Autowired
	OutletService outletService;
	
	private static final Logger logger = LoggerFactory.getLogger(OutletBV.class);
	
	 public APIResponse<Outlet> bValidateCreate(String tenantCode, Outlet outlet, String locale) {
		 
		 APIResponse<Outlet> apiResponse = new APIResponse<Outlet>();
		 apiResponse.setProcessingSuccess(true);
		 List<String> list = new ArrayList<String>();
		try {
			apiResponse = validateCreate(tenantCode, outlet, locale);

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

	private APIResponse<Outlet> validateCreate(String tenantCode, Outlet outlet, String locale) {
		
		APIResponse<Outlet> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
        List<String> outletList = masterDataService.getDataNameByType(tenantCode, "Outlet");
        
        if (outletList != null && !outletList.contains(outlet.getGstNo())) {
        	apiResponse.setResponseCode(StatusCodeEnum.OUTLET_INVALID.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.OUTLET_INVALID.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        Outlet out = outletService.getOutletById(outlet.getGstNo());
        
        if (out != null) {
        	apiResponse.setResponseCode(StatusCodeEnum.OUTLET_DUPLICATE.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.OUTLET_DUPLICATE.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        return apiResponse;
	}

}
