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
import com.kashitkalaecom.brandmodelmgmt.businessvalidation.HomeSliderBV;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.fieldvalidation.HomeSliderFV;
import com.kashitkalaecom.brandmodelmgmt.models.Discount;
import com.kashitkalaecom.brandmodelmgmt.models.HomeSlider;
import com.kashitkalaecom.brandmodelmgmt.models.Webpages;
import com.kashitkalaecom.brandmodelmgmt.service.HomeSliderService;
import com.kashitkalaecom.brandmodelmgmt.service.WebpagesService;

@RestController
@RequestMapping("/api/v1/homeSlider")
public class HomeSliderController {

	@Autowired
	HomeSliderService homeSliderService;
	
	@Autowired
	HomeSliderFV homeSliderFV;
	
	@Autowired 
	HomeSliderBV homeSliderBV;

	@PostMapping("/create")
	public APIResponse<HomeSlider> homeSlider(@RequestHeader String requestorId, @RequestBody HomeSlider req) {

		APIResponse<HomeSlider> apiResponse = new APIResponse<HomeSlider>();
		try {
			apiResponse = homeSliderFV.fValidateCreate(null, req, null);
			if (!apiResponse.getValidationSuccess()) {
				return apiResponse;
			}
			apiResponse = homeSliderBV.bValidateCreate(null, req, null);
            if (!apiResponse.getProcessingSuccess()) {
                return apiResponse;
            }
			
			req = homeSliderService.save(req, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.HOMESLIDER_CONFIG_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.HOMESLIDER_CONFIG_CREATED.getDesc());
			apiResponse.setResponseObject(req);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.HOMESLIDER_CONFIG_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.HOMESLIDER_CONFIG_CREATION_FAILED.getDesc());
			apiResponse.setResponseObject(req);
		}
		return apiResponse;
	}

	@GetMapping("/view/{id}")
	public APIResponse<HomeSlider> homeSlider(@RequestHeader String requestorId, @PathVariable("id") String id) {

		APIResponse<HomeSlider> apiResponse = new APIResponse<HomeSlider>();
		HomeSlider homeSlider = homeSliderService.getHomeSliderById(id);

		if (homeSlider == null) {
			apiResponse.setResponseCode(StatusCodeEnum.HOMESLIDER_CONFIG_NOT_EXISTS.getCode());
			apiResponse.setResponseCode(StatusCodeEnum.HOMESLIDER_CONFIG_NOT_EXISTS.getDesc());
			apiResponse.setResponseObject(homeSlider);
			return apiResponse;
		}
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("Success");
		apiResponse.setResponseObject(homeSlider);
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse<HomeSlider> updateHomeSlider(@RequestHeader String requestorId, @RequestBody HomeSlider req) {

		APIResponse<HomeSlider> apiResponse = new APIResponse<HomeSlider>();
		try {
			req = homeSliderService.update(req, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.HOMESLIDER_CONFIG_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.HOMESLIDER_CONFIG_UPDATED.getCode());
			apiResponse.setResponseObject(req);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.HOMESLIDER_CONFIG_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.HOMESLIDER_CONFIG_UPDATION_FAILED.getCode());
		}
		return apiResponse;
	}

	@PutMapping("/delete/{id}")
	public APIResponse<HomeSlider> deleteHomeSlider(@RequestHeader String requestorId, @PathVariable String id) {

		APIResponse<HomeSlider> apiResponse = new APIResponse<HomeSlider>();
		try {
			HomeSlider homeSlider = homeSliderService.delete(id, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.HOMESLIDER_CONFIG_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.HOMESLIDER_CONFIG_UPDATED.getCode());
			apiResponse.setResponseObject(homeSlider);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.HOMESLIDER_CONFIG_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.HOMESLIDER_CONFIG_UPDATION_FAILED.getCode());
		}
		return apiResponse;
	}

}
