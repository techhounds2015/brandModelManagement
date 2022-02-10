package com.kashitkalaecom.brandmodelmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Brand;
import com.kashitkalaecom.brandmodelmgmt.service.BrandService;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {

	@Autowired
	BrandService brandService;

	@PostMapping("/create")
	public APIResponse createBrand(@RequestHeader String requestorId, @RequestBody Brand brand) {

		APIResponse apiResponse = new APIResponse();
		try {
			brand = brandService.save(brand, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.BRAND_CREATED.getCode());
			apiResponse.setResponseObject(brand);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.BRAND_CREATION_FAILED.getCode());
		}
		return apiResponse;
	}

	@GetMapping("/view/{brandId}")
	public APIResponse brand(@RequestHeader String requestorId, @PathVariable("brandId") String brandId) {

		APIResponse apiResponse = new APIResponse();
		Brand brand = brandService.getBrandById(brandId);

		if (brand == null) {
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_NOT_EXISTS.getCode());
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_NOT_EXISTS.getDesc());
			return apiResponse;
		}
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse updateBrand(@RequestHeader String requestorId, @RequestBody Brand brand) {

		APIResponse apiResponse = new APIResponse();
		try {
			brand = brandService.update(brand, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.BRAND_UPDATED.getCode());
			apiResponse.setResponseObject(brand);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.BRAND_UPDATION_FAILED.getCode());
		}
		return apiResponse;
	}

	@PutMapping("/delete/{id}")
	public APIResponse deletebrand(@RequestHeader String requestorId, @RequestParam String id) {

		APIResponse apiResponse = new APIResponse();
		try {
			Brand brand = brandService.delete(id, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.BRAND_UPDATED.getCode());
			apiResponse.setResponseObject(brand);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.BRAND_UPDATION_FAILED.getCode());
		}
		return apiResponse;
	}
}
