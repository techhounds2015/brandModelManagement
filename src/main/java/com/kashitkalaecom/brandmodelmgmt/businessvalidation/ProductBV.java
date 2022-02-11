package com.kashitkalaecom.brandmodelmgmt.businessvalidation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Category;
import com.kashitkalaecom.brandmodelmgmt.models.Product;
import com.kashitkalaecom.brandmodelmgmt.service.CategoryService;
import com.kashitkalaecom.brandmodelmgmt.service.ProductService;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;
@Component
public class ProductBV {
	
	@Autowired
	MasterDataService masterDataService;
	
	@Autowired
	ProductService productService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductBV.class);
	
	public APIResponse bValidateCreate(String tenantCode, Product product, String locale) {
		
		APIResponse<Product> apiResponse = new APIResponse<>();
		 apiResponse.setProcessingSuccess(true);
		try {
			apiResponse = validateCreate(tenantCode, product, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}
	
		return apiResponse;
	}

	private APIResponse<Product> validateCreate(String tenantCode, Product product, String locale) {
		
		APIResponse<Product> apiResponse = new APIResponse<>();
        List<String> productList = masterDataService.getDataNameByType(tenantCode, "Product");
        
        if (productList != null && !productList.contains(product.getBrandId())) {
        	apiResponse.setResponseCode(StatusCodeEnum.BRAND_NOT_EXISTS.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.BRAND_NOT_EXISTS.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        Product product1 = productService.getproductByCategoryId(product.getCategoryId());
        
        if (product1 != null) {
        	apiResponse.setResponseCode(StatusCodeEnum.CATEGORY_DUPLICATE.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.CATEGORY_DUPLICATE.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        return apiResponse;
	}

}
