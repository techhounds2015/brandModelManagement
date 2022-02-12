package com.kashitkalaecom.brandmodelmgmt.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.kashitkalaecom.brandmodelmgmt.businessvalidation.BrandBV;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.fieldvalidation.BrandFV;
import com.kashitkalaecom.brandmodelmgmt.models.Brand;
import com.kashitkalaecom.brandmodelmgmt.service.BrandService;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {

	@Autowired
	BrandService brandService;

	@Autowired
	BrandFV brandFV;
	
	@Autowired
	BrandBV brandBV;

	@PostMapping("/create")
	public APIResponse<Brand> createBrand(@RequestHeader String requestorId, @RequestBody Brand brand) {
		APIResponse<Brand> apiResponse = new APIResponse<>();

		try {

			apiResponse = brandFV.fValidateCreate(null, brand, null);
			if (Boolean.FALSE.equals(apiResponse.getValidationSuccess())) {
				return apiResponse;
			}
			
			apiResponse = brandBV.bValidateCreate(null, brand, null);
            if (Boolean.FALSE.equals(apiResponse.getProcessingSuccess())) {
                return apiResponse;
            }
            
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
	public APIResponse<Brand> brand(@RequestHeader String requestorId, @PathVariable("brandId") String brandId) {

		APIResponse<Brand> apiResponse = new APIResponse<>();
		int brandCount = brandService.brandIdExists(brandId);

		if (brandCount == 0) {
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.BRAND_NOT_EXISTS.getDesc());
			return apiResponse;
		}
		
		Brand brand = brandService.getBrandById(brandId);
		apiResponse.setResponseMessage("Success");
		apiResponse.setResponseObject(brand);
		return apiResponse;
	}
	
	@GetMapping("/viewAll")
	public APIResponse<List<Brand>> allBrands(@RequestHeader String requestorId, @PathVariable("categoryId") String categoryId) {

		List<Brand> brandList = new ArrayList<>();
		APIResponse<List<Brand>> apiResponse = new APIResponse<>();

		try {
			brandList = brandService.getAllBrands();
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.ERROR_WHILE_RETREVING_DATA.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ERROR_WHILE_RETREVING_DATA.getDesc());
			return apiResponse;
		}
		if (brandList.isEmpty()) {
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.BRAND_NOT_EXISTS.getDesc());
			return apiResponse;
		}

		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("Success");
		apiResponse.setResponseObject(brandList);
		return apiResponse;
	}
	
	
	

	@PutMapping("/update")
	public APIResponse<Brand> updatebrand(@RequestHeader String requestorId, @RequestBody Brand brand) {
		APIResponse<Brand> apiResponse = new APIResponse<>();
		try {
			
			apiResponse = brandBV.bValidateUpdate(null, brand, null);
			
            if (Boolean.FALSE.equals(apiResponse.getProcessingSuccess())) {
                return apiResponse;
            }
			
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
	public APIResponse<Brand> deletebrand(@RequestHeader String requestorId, @PathVariable String id) {

		APIResponse<Brand> apiResponse = new APIResponse<>();
		try {
			
			int brandCount = brandService.brandIdExists(id);
			if (brandCount == 0) {
				apiResponse.setResponseCode(StatusCodeEnum.BRAND_NOT_EXISTS.getCode());
				apiResponse.setResponseMessage(StatusCodeEnum.BRAND_NOT_EXISTS.getDesc());
				return apiResponse;
			}
			
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
