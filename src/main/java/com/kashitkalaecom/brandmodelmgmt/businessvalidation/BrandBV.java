package com.kashitkalaecom.brandmodelmgmt.businessvalidation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Brand;
import com.kashitkalaecom.brandmodelmgmt.service.BrandService;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;

@Component
public class BrandBV {

	@Autowired
	MasterDataService masterDataService;
	
	@Autowired
	BrandService brandService;
	
	private static final Logger logger = LoggerFactory.getLogger(BrandBV.class);

	public APIResponse<Brand> bValidateCreate(String tenantCode, Brand brand, String locale) {

		APIResponse<Brand> apiResponse = new APIResponse<>();
		 apiResponse.setProcessingSuccess(true);
		try {
			apiResponse = validateCreate(tenantCode, brand, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}
	
		return apiResponse;
	}

	private APIResponse<Brand> validateCreate(String tenantCode, Brand brand, String locale) {

		APIResponse<Brand> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
        List<String> brandList = masterDataService.getDataNameByType(tenantCode, "Brand");
        
        if (brandList != null && !brandList.contains(brand.getCategoryId())) {
        	apiResponse.setResponseCode(StatusCodeEnum.CATEGORY_INVALID.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.CATEGORY_INVALID.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        Brand brand1 = brandService.getBrandByName(brand.getName());
        
        if (brand1 != null) {
        	apiResponse.setResponseCode(StatusCodeEnum.CATEGORY_DUPLICATE.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.CATEGORY_DUPLICATE.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        return apiResponse;
	}

	}
