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
import com.kashitkalaecom.brandmodelmgmt.businessvalidation.DiscountBV;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.fieldvalidation.DiscountFV;
import com.kashitkalaecom.brandmodelmgmt.models.Discount;
import com.kashitkalaecom.brandmodelmgmt.service.DiscountService;

@RestController
@RequestMapping("/api/v1/discount")
public class DiscountController {

	@Autowired
	DiscountService discountService;

	@Autowired
	DiscountFV discountFV;
	
	@Autowired
	DiscountBV discountBV;
	
	
	@PostMapping("/create")
	public APIResponse<Discount> createDiscount(@RequestHeader String requestorId, @RequestBody Discount req) {
		APIResponse<Discount> apiResponse = new APIResponse<>();
		try {
			apiResponse = discountFV.fValidateCreate(null, req, null);
			if (Boolean.FALSE.equals(apiResponse.getValidationSuccess())) {
				return apiResponse;
			}
			apiResponse = discountBV.bValidateCreate(null, req, null);
            if (Boolean.FALSE.equals(apiResponse.getProcessingSuccess())) {
                return apiResponse;
            }
			req = discountService.save(req, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.DISCOUNT_CODE_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.DISCOUNT_CODE_CREATED.getDesc());
			apiResponse.setResponseObject(req);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.DISCOUNT_CODE_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.DISCOUNT_CODE_CREATION_FAILED.getDesc());
			apiResponse.setResponseObject(req);
		}
		return apiResponse;
	}


	@GetMapping("/view/{id}")
	public APIResponse<Discount> getDiscountById(@RequestHeader String requestorId, @PathVariable("id") String id) {

		APIResponse<Discount> apiResponse = new APIResponse<>();
		int discountCount = discountService.discountCodeExists(id);

		if (discountCount == 0) {
			apiResponse.setResponseCode(StatusCodeEnum.DISCOUNT_CODE_NOT_EXISTS.getCode());
			apiResponse.setResponseCode(StatusCodeEnum.DISCOUNT_CODE_NOT_EXISTS.getDesc());
			return apiResponse;
		}
		
		Discount discount = discountService.getDiscountById(id);
		
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("Success");
		apiResponse.setResponseObject(discount);
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse<Discount> updateDiscount(@RequestHeader String requestorId, @RequestBody Discount req) {
		APIResponse<Discount> apiResponse = new APIResponse<>();
		try {
			
			int discountCount = discountService.discountCodeExists(req.getId());

			if (discountCount == 0) {
				apiResponse.setResponseCode(StatusCodeEnum.DISCOUNT_CODE_NOT_EXISTS.getCode());
				apiResponse.setResponseCode(StatusCodeEnum.DISCOUNT_CODE_NOT_EXISTS.getDesc());
				return apiResponse;
			}
			
			req = discountService.update(req, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.DISCOUNT_CODE_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.DISCOUNT_CODE_UPDATED.getCode());
			apiResponse.setResponseObject(req);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.DISCOUNT_CODE_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.DISCOUNT_CODE_UPDATION_FAILED.getCode());
		}
		return apiResponse;

	}

	@PutMapping("/delete/{id}")
	public APIResponse<Discount> deleteDiscount(@RequestHeader String requestorId, @PathVariable String id) {

		APIResponse<Discount> apiResponse = new APIResponse<>();
		try {
			
			int discountCount = discountService.discountCodeExists(id);

			if (discountCount == 0) {
				apiResponse.setResponseCode(StatusCodeEnum.DISCOUNT_CODE_NOT_EXISTS.getCode());
				apiResponse.setResponseCode(StatusCodeEnum.DISCOUNT_CODE_NOT_EXISTS.getDesc());
				return apiResponse;
			}
			
			Discount discount = discountService.delete(id, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.DISCOUNT_CODE_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.DISCOUNT_CODE_UPDATED.getCode());
			apiResponse.setResponseObject(discount);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.DISCOUNT_CODE_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.DISCOUNT_CODE_UPDATION_FAILED.getCode());
		}
		return apiResponse;

	}

}
