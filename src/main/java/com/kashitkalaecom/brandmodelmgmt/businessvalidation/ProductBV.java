package com.kashitkalaecom.brandmodelmgmt.businessvalidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Product;
import com.kashitkalaecom.brandmodelmgmt.service.BrandService;
import com.kashitkalaecom.brandmodelmgmt.service.CategoryService;
import com.kashitkalaecom.brandmodelmgmt.service.ModelService;
import com.kashitkalaecom.brandmodelmgmt.service.ProductService;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;

@Component
public class ProductBV {

	@Autowired
	MasterDataService masterDataService;

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ModelService modelService;

	@Autowired
	BrandService brandService;

	private static final Logger logger = LoggerFactory.getLogger(ProductBV.class);

	public APIResponse<Product> bValidateCreate(String tenantCode, Product product, String locale) {

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
		apiResponse.setProcessingSuccess(true);

		int catCount = categoryService.categoryExists(product.getCategoryId());

		if (catCount == 0) {
			apiResponse.setResponseCode(StatusCodeEnum.CATEGORY_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.CATEGORY_NOT_EXISTS.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		// Brand Exists

		int brandCount = brandService.brandIdExists(product.getBrandId());

		if (brandCount == 0) {
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.BRAND_NOT_EXISTS.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		// Model Exists

		int modelCount = modelService.modelIdExists(product.getModelId());

		if (modelCount == 0) {
			apiResponse.setResponseCode(StatusCodeEnum.MODEL_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.MODEL_NOT_EXISTS.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}
		
		// Product title already exists for model, brand, category
		
		int count = productService.titleExists(product.getModelId(),product.getBrandId(),product.getCategoryId());

		if (count > 0) {
			apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_DUPLICATE.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PRODUCT_DUPLICATE.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		return apiResponse;
	}

	public APIResponse<Product> bValidateUpdate(String tenantCode, Product product, String locale) {
		APIResponse<Product> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
		try {
			apiResponse = validateUpdate(tenantCode, product, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}

		return apiResponse;
	}
	private APIResponse<Product> validateUpdate(String tenantCode, Product product, String locale) {

		APIResponse<Product> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
		
		
		//Product exists

		int productCount = productService.productIdExists(product.getId());
		if (productCount == 0) {
			apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PRODUCT_NOT_EXISTS.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}
		
		// cat exists

		int catCount = categoryService.categoryExists(product.getCategoryId());

		if (catCount == 0) {
			apiResponse.setResponseCode(StatusCodeEnum.CATEGORY_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.CATEGORY_NOT_EXISTS.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		// Brand Exists

		int brandCount = brandService.brandIdExists(product.getBrandId());

		if (brandCount == 0) {
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.BRAND_NOT_EXISTS.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		// Model Exists

		int modelCount = modelService.modelIdExists(product.getModelId());

		if (modelCount == 0) {
			apiResponse.setResponseCode(StatusCodeEnum.MODEL_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.MODEL_NOT_EXISTS.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		return apiResponse;
	}
}
