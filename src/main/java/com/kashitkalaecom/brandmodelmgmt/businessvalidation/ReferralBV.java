package com.kashitkalaecom.brandmodelmgmt.businessvalidation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Referral;
import com.kashitkalaecom.brandmodelmgmt.models.Webpages;
import com.kashitkalaecom.brandmodelmgmt.service.ReferralService;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;
@Component
public class ReferralBV {
	
	@Autowired
	MasterDataService masterDataService;
	
	@Autowired
	ReferralService referralService;
	
	private static final Logger logger = LoggerFactory.getLogger(ReferralBV.class);
	
	 public APIResponse<Referral> bValidateCreate(String tenantCode, Referral referral, String locale) {
		 
		 APIResponse<Referral> apiResponse = new APIResponse<Referral>();
		 apiResponse.setProcessingSuccess(true);
		 List<String> list = new ArrayList<String>();
		try {
			apiResponse = validateCreate(tenantCode, referral, locale);

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

	private APIResponse<Referral> validateCreate(String tenantCode, Referral referral, String locale) {
		APIResponse<Referral> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
        List<String> referralList = masterDataService.getDataNameByType(tenantCode, "Referral");
        
        if (referralList != null && !referralList.contains(referral.getCode())) {
        	apiResponse.setResponseCode(StatusCodeEnum.REFERRAL_CODE_INVALID.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.REFERRAL_CODE_INVALID.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        Referral ref = referralService.getReferralById(referral.getCode());
        
        if (ref != null) {
        	apiResponse.setResponseCode(StatusCodeEnum.REFERRAL_CODE_DUPLICATE.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.REFERRAL_CODE_DUPLICATE.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        return apiResponse;
	}

}
