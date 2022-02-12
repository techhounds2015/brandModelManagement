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
import com.kashitkalaecom.brandmodelmgmt.service.CategoryService;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;

@Component
public class BrandBV {

	@Autowired
	MasterDataService masterDataService;
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	CategoryService categoryService;
	
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
       
		// Category Exists
		
		int catCount = categoryService.categoryExists(brand.getCategoryId());
		
		if (catCount == 0) {
			apiResponse.setResponseCode(StatusCodeEnum.CATEGORY_NOT_EXISTS.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.CATEGORY_NOT_EXISTS.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
		}
		
		// Brand Name Already exists
		
        int brandCount = brandService.brandExists(brand.getName());        
        
        if (brandCount > 0) {
        	apiResponse.setResponseCode(StatusCodeEnum.BRAND_DUPLICATE.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.BRAND_DUPLICATE.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        return apiResponse;
	}

	public APIResponse<Brand> bValidateUpdate(String tenantCode, Brand brand, String locale) {
		APIResponse<Brand> apiResponse = new APIResponse<>();
		 apiResponse.setProcessingSuccess(true);
		try {
			apiResponse = validateUpdate(tenantCode, brand, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}
	
		return apiResponse;
	}

	private APIResponse<Brand> validateUpdate(String tenantCode, Brand brand, String locale) {

		APIResponse<Brand> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
       
		// Category Exists
		
		int catCount = categoryService.categoryExists(brand.getCategoryId());
		
		if (catCount == 0) {
			apiResponse.setResponseCode(StatusCodeEnum.CATEGORY_NOT_EXISTS.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.CATEGORY_NOT_EXISTS.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
		}
		
		// Brand Name Already exists
		
        int brandCount = brandService.brandIdExists(brand.getId());        
        
        if (brandCount == 0) {
        	apiResponse.setResponseCode(StatusCodeEnum.BRAND_NOT_EXISTS.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.BRAND_NOT_EXISTS.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        return apiResponse;
	}

	}
