package com.kashitkalaecom.brandmodelmgmt.businessvalidation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Category;
import com.kashitkalaecom.brandmodelmgmt.models.ProductImages;
import com.kashitkalaecom.brandmodelmgmt.service.ProductImagesService;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;
@Component
public class ProductImagesBV {
	
	@Autowired
	MasterDataService masterDataService;
	
	@Autowired
	ProductImagesService productImagesService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductImagesBV.class);
		 
	public APIResponse bValidateCreate(String tenantCode, ProductImages productImages, String locale) {
		
		APIResponse<ProductImages> apiResponse = new APIResponse<>();
		 apiResponse.setProcessingSuccess(true);
		try {
			apiResponse = validateCreate(tenantCode, productImages, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}
	
		return apiResponse;
	}

	private APIResponse<ProductImages> validateCreate(String tenantCode, ProductImages productImages, String locale) {
		
		APIResponse<ProductImages> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
        List<String> productImagesList = masterDataService.getDataNameByType(tenantCode, "ProductImages");
        
        if (productImagesList != null && !productImagesList.contains(productImages.getProductId())) {
        	apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_NOT_EXISTS.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.PRODUCT_NOT_EXISTS.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        ProductImages productImages1 = productImagesService.getProductImagesByName(productImages.getImageName());
        
        if (productImages1 != null) {
        	apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_NOT_EXISTS.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.PRODUCT_NOT_EXISTS.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        return apiResponse;
		
	}

}
