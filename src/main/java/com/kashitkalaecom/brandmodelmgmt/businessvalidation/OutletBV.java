package com.kashitkalaecom.brandmodelmgmt.businessvalidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Outlet;
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
		try {
			apiResponse = validateCreate(tenantCode, outlet, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;

		}

		return apiResponse;

	}

	private APIResponse<Outlet> validateCreate(String tenantCode, Outlet outlet, String locale) {

		APIResponse<Outlet> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);

		int outGst = outletService.outletGstExists(outlet.getGstNo());

		if (outGst > 0) {
			apiResponse.setResponseCode(StatusCodeEnum.OUTLET_DUPLICATE.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.OUTLET_DUPLICATE.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		int outName = outletService.outletNameExists(outlet.getName());

		if (outName > 0) {
			apiResponse.setResponseCode(StatusCodeEnum.OUTLET_DUPLICATE.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.OUTLET_DUPLICATE.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		return apiResponse;
	}

}
