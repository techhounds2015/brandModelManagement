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
import com.kashitkalaecom.brandmodelmgmt.businessvalidation.ProductImagesBV;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.fieldvalidation.ProductImagesFV;
import com.kashitkalaecom.brandmodelmgmt.models.ProductImages;
import com.kashitkalaecom.brandmodelmgmt.service.ProductImagesService;

@RestController
@RequestMapping("/api/v1/productImages")
public class ProductImagesController {

	@Autowired
	ProductImagesService productImagesService;

	@Autowired
	ProductImagesFV productImagesFV;
	
	@Autowired
	ProductImagesBV productImagesBV;

	@PostMapping("/create")
	public APIResponse category(@RequestHeader String requestorId, @RequestBody ProductImages productImages) {
		APIResponse apiResponse = new APIResponse();

		try {

			apiResponse = productImagesFV.fValidateCreate(null, productImages, null);
			if (!apiResponse.getValidationSuccess()) {
				return apiResponse;
			}
			
			apiResponse = productImagesBV.bValidateCreate(null, productImages, null);
            if (!apiResponse.getProcessingSuccess()) {
                return apiResponse;
            }
			productImages = productImagesService.save(productImages, requestorId);

			apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PRODUCT_CREATED.getDesc());
			apiResponse.setResponseObject(productImages);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PRODUCT_CREATION_FAILED.getDesc());
			apiResponse.setResponseObject(productImages);
		}

		return apiResponse;
	}

	@GetMapping("/view/{modelId}")
	public APIResponse productImages(@RequestHeader String requestorId,
			@PathVariable("productImagesId") String productImagesId) {

		ProductImages productImages = productImagesService.getProductImagesById(productImagesId);
		APIResponse apiResponse = new APIResponse();
		if (productImages == null) {
			apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_NOT_EXISTS.getCode());
			apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_NOT_EXISTS.getDesc());
			apiResponse.setResponseObject(productImages);
			return apiResponse;
		}
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(productImages);
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse updateproductImages(@RequestHeader String requestorId,
			@RequestBody ProductImages productImages) {

		APIResponse apiResponse = new APIResponse();
		try {
			productImages = productImagesService.update(productImages, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PRODUCT_UPDATED.getCode());
			apiResponse.setResponseObject(productImages);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PRODUCT_UPDATION_FAILED.getCode());
		}
		return apiResponse;
	}

	@PutMapping("/delete/{id}")
	public APIResponse deleteproductImages(@RequestHeader String requestorId, @PathVariable String id) {

		APIResponse apiResponse = new APIResponse();
		try {
			ProductImages productImages = productImagesService.delete(id, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PRODUCT_UPDATED.getCode());
			apiResponse.setResponseObject(productImages);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PRODUCT_UPDATION_FAILED.getCode());
		}
		return apiResponse;
	}

}
