package com.kashitkalaecom.brandmodelmgmt.FV;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.models.Product;
import com.kashitkalaecom.brandmodelmgmt.validation.ValidationService;

@Component
public class ProductFV {

	@Autowired
	ValidationService validationService;

	private static final Logger logger = LoggerFactory.getLogger(ProductFV.class);

	private static String module = Product.class.getSimpleName();

	public APIResponse fValidateCreate(String tenantCode, Product product, String locale) {

		APIResponse<Product> apiResponse = new APIResponse<>();

		apiResponse.setValidationSuccess(true);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try {
			hashMap = validateCreate(tenantCode, product, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage("FAILURE");
			apiResponse.setResponseCode("8000");
			apiResponse.setValidationSuccess(false);
			logger.error("error while validating request", e);
			return apiResponse;
		}
		if (hashMap.size() > 0) {
			apiResponse.setResponseMessage("FAILURE");
			apiResponse.setResponseCode("8000");
			apiResponse.setValidationSuccess(false);
			apiResponse.setErrors(hashMap);

		}
		return apiResponse;
	}

	private HashMap<String, String> validateCreate(String tenantCode, Product product, String locale) throws Exception {

		HashMap<String, String> hashMap = new HashMap<>();
		String errorString = null;

		errorString = validationService.validateField(tenantCode, module, product.getCategoryId(), "categoryId",
				locale);
		if (errorString != null)
			hashMap.put("categoryId", errorString);

		errorString = validationService.validateField(tenantCode, module, product.getBrandId(), "brandId", locale);
		if (errorString != null)
			hashMap.put("brandId", errorString);

		errorString = validationService.validateField(tenantCode, module, product.getModelId(), "modelId", locale);
		if (errorString != null)
			hashMap.put("modelId", errorString);

		errorString = validationService.validateField(tenantCode, module, product.getTitle(), "title", locale);
		if (errorString != null)
			hashMap.put("title", errorString);

		errorString = validationService.validateField(tenantCode, module, product.getHsnCode(), "hsnCode", locale);
		if (errorString != null)
			hashMap.put("hsnCode", errorString);

		errorString = validationService.validateField(tenantCode, module, product.getVegNonvegStatus(),
				"vegNonvegStatus", locale);
		if (errorString != null)
			hashMap.put("vegNonvegStatus", errorString);

		errorString = validationService.validateField(tenantCode, module, product.getSizes(), "sizes", locale);
		if (errorString != null)
			hashMap.put("sizes", errorString);

		errorString = validationService.validateField(tenantCode, module, product.getAvaliableForSubscription(),
				"avaliableForSubscription", locale);
		if (errorString != null)
			hashMap.put("avaliableForSubscription", errorString);

		errorString = validationService.validateField(tenantCode, module, product.getMaxQualityPurchased(),
				"maxQualityPurchased", locale);
		if (errorString != null)
			hashMap.put("maxQualityPurchased", errorString);

		errorString = validationService.validateField(tenantCode, module, product.getSku(), "sku", locale);
		if (errorString != null)
			hashMap.put("sku", errorString);

		errorString = validationService.validateField(tenantCode, module, product.getMrp(), "mrp", locale);
		if (errorString != null)
			hashMap.put("mrp", errorString);

		errorString = validationService.validateField(tenantCode, module, product.getSellingPrice(), "sellingPrice",
				locale);
		if (errorString != null)
			hashMap.put("sellingPrice", errorString);

		errorString = validationService.validateField(tenantCode, module, product.getCgst(), "cgst", locale);
		if (errorString != null)
			hashMap.put("cgst", errorString);

		errorString = validationService.validateField(tenantCode, module, product.getSgst(), "sgst", locale);
		if (errorString != null)
			hashMap.put("sgst", errorString);

		errorString = validationService.validateField(tenantCode, module, product.getCesss(), "cesss", locale);
		if (errorString != null)
			hashMap.put("cesss", errorString);

		errorString = validationService.validateField(tenantCode, module, product.getOutletId(), "outletId", locale);
		if (errorString != null)
			hashMap.put("outletId", errorString);

		errorString = validationService.validateField(tenantCode, module, product.getMarkAsFeatured(), "markAsFeatured",
				locale);
		if (errorString != null)
			hashMap.put("markAsFeatured", errorString);

		errorString = validationService.validateField(tenantCode, module, product.getMarkAsBestProduct(),
				"markAsBestProduct", locale);
		if (errorString != null)
			hashMap.put("getMarkAsBestProduct", errorString);

		errorString = validationService.validateField(tenantCode, module, product.getDescription(), "description",
				locale);
		if (errorString != null)
			hashMap.put("markAsBestProduct", errorString);

		errorString = validationService.validateField(tenantCode, module, product.getStatus(), "status", locale);
		if (errorString != null)
			hashMap.put("status", errorString);

		return hashMap;
	}

}
