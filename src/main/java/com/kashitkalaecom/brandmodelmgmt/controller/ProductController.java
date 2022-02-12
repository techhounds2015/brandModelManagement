package com.kashitkalaecom.brandmodelmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.businessvalidation.ProductBV;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.fieldvalidation.ProductFV;
import com.kashitkalaecom.brandmodelmgmt.models.Product;
import com.kashitkalaecom.brandmodelmgmt.service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	ProductFV prodeuctFV;

	@Autowired
	ProductBV productBV;

	@PostMapping("/create")
	public APIResponse<Product> category(@RequestHeader String requestorId, @RequestBody Product product) {
		APIResponse<Product> apiResponse = new APIResponse<>();

		try {

			apiResponse = prodeuctFV.fValidateCreate(null, product, null);
			if (Boolean.FALSE.equals(apiResponse.getValidationSuccess())) {
				return apiResponse;
			}

			apiResponse = productBV.bValidateCreate(null, product, null);
			if (Boolean.FALSE.equals(apiResponse.getProcessingSuccess())) {
				return apiResponse;
			}
			product = productService.save(product, requestorId);

			apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PRODUCT_CREATED.getDesc());
			apiResponse.setResponseObject(product);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PRODUCT_CREATION_FAILED.getDesc());
			apiResponse.setResponseObject(product);
		}

		return apiResponse;
	}

	@GetMapping("/view/{productId}")
	public APIResponse<Product> product(@RequestHeader String requestorId,
			@PathVariable("productId") String productId) {
		
		APIResponse<Product> apiResponse = new APIResponse<>();
		
		// Product exists
		int productCount = productService.productIdExists(productId);
		
		if (productCount == 0) {
			apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PRODUCT_NOT_EXISTS.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}
		Product product = productService.getProductById(productId);

		if (product == null) {
			apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PRODUCT_NOT_EXISTS.getDesc());
			return apiResponse;
		}

		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(product);
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse<Product> updateproduct(@RequestHeader String requestorId, @RequestBody Product product) {

		APIResponse<Product> apiResponse = new APIResponse<>();

		try {

			apiResponse = productBV.bValidateUpdate(null, product, null);
			if (Boolean.FALSE.equals(apiResponse.getProcessingSuccess())) {
				return apiResponse;
			}

			product = productService.update(product, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PRODUCT_UPDATED.getDesc());
			apiResponse.setResponseObject(product);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PRODUCT_UPDATION_FAILED.getDesc());
		}
		return apiResponse;
	}

	@PutMapping("/delete/{id}")
	public APIResponse<Product> deleteproduct(@RequestHeader String requestorId, @PathVariable String id) {

		APIResponse<Product> apiResponse = new APIResponse<>();
		try {
			
			// Product exists
			int productCount = productService.productIdExists(id);
			
			if (productCount == 0) {
				apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_NOT_EXISTS.getCode());
				apiResponse.setResponseMessage(StatusCodeEnum.PRODUCT_NOT_EXISTS.getDesc());
				apiResponse.setProcessingSuccess(false);
				return apiResponse;
			}
			
			Product product = productService.delete(id, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PRODUCT_UPDATED.getDesc());
			apiResponse.setResponseObject(product);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PRODUCT_UPDATION_FAILED.getDesc());
		}
		return apiResponse;
	}

}
