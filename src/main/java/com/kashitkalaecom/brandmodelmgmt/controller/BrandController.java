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

import com.kashitkalaecom.brandmodelmgmt.FV.BrandFV;
import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Brand;
import com.kashitkalaecom.brandmodelmgmt.service.BrandService;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {

	@Autowired
	BrandService brandService;

	@Autowired
	BrandFV brandFV;

	/*
	 * @PostMapping("/create") public APIResponse brand(@RequestHeader String
	 * requestorId,
	 * 
	 * @RequestBody Brand brand) {
	 * 
	 * brand = brandService.save(brand,requestorId); APIResponse apiResponse = new
	 * APIResponse(); apiResponse.setResponseCode("200");
	 * apiResponse.setResponseMessage("success");
	 * apiResponse.setResponseObject(brand); return apiResponse; }
	 */

	@PostMapping("/create")
	public APIResponse brand(@RequestHeader String requestorId, @RequestBody Brand brand) {
		APIResponse apiResponse = new APIResponse();

		try {

			apiResponse = brandFV.fValidateCreate(null, brand, null);
			if (!apiResponse.getValidationSuccess()) {
				return apiResponse;
			}
			// Business Validation

			// Save
			brand = brandService.save(brand, requestorId);

			apiResponse.setResponseCode(StatusCodeEnum.BRAND_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.BRAND_CREATED.getDesc());
			apiResponse.setResponseObject(brand);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.BRAND_CREATION_FAILED.getDesc());
			apiResponse.setResponseObject(brand);
		}

		return apiResponse;
	}

	@GetMapping("/view/{brandId}")
	public APIResponse brand(@RequestHeader String requestorId, @PathVariable("brandId") String brandId) {

		Brand brand = brandService.getBrandById(brandId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(brand);
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse updatebrand(@RequestHeader String requestorId, @RequestBody Brand brand) {

		brand = brandService.update(brand, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(brand);
		return apiResponse;
	}

	@PutMapping("/delete/{id}")
	public APIResponse deletebrand(@RequestHeader String requestorId, @RequestParam String id) {

		Brand brand = brandService.delete(id, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(brand);
		return apiResponse;
	}
}
