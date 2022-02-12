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
import com.kashitkalaecom.brandmodelmgmt.businessvalidation.WebpagesBV;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.fieldvalidation.WebpagesFV;
import com.kashitkalaecom.brandmodelmgmt.models.Webpages;
import com.kashitkalaecom.brandmodelmgmt.service.WebpagesService;

@RestController
@RequestMapping("/api/v1/webpages")
public class WebpagesController {

	@Autowired
	WebpagesService webpagesService;

	@Autowired
	WebpagesFV webpagesFV;
	
	@Autowired
	WebpagesBV webpagesBV;
	
	@PostMapping("/create")
	public APIResponse<Webpages> webPages(@RequestHeader String requestorId, @RequestBody Webpages req) {
		APIResponse<Webpages> apiResponse = new APIResponse<Webpages>();
		try {
			apiResponse = webpagesFV.fValidateCreate(null, req, null);
			if (!apiResponse.getValidationSuccess()) {
				return apiResponse;
			}

			apiResponse = webpagesBV.bValidateCreate(null, req, null);
			if (!apiResponse.getProcessingSuccess()) {
				return apiResponse;
			}
 
			req = webpagesService.save(req, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.WEBPAGES_CONFIG_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.WEBPAGES_CONFIG_CREATED.getDesc());
			apiResponse.setResponseObject(req);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.WEBPAGES_CONFIG_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.WEBPAGES_CONFIG_CREATION_FAILED.getDesc());
			apiResponse.setResponseObject(req);
		}
		return apiResponse;
	}

	@GetMapping("/view/{id}")
	public APIResponse<Webpages> webPages(@RequestHeader String requestorId, @PathVariable("id") String id) {
		APIResponse<Webpages> apiResponse = new APIResponse<Webpages>();
		Webpages webpages = webpagesService.getWebpagesById(id);

		if (webpages == null) {
			apiResponse.setResponseCode(StatusCodeEnum.WEBPAGES_CONFIG_NOT_EXISTS.getCode());
			apiResponse.setResponseCode(StatusCodeEnum.WEBPAGES_CONFIG_NOT_EXISTS.getDesc());
			apiResponse.setResponseObject(webpages);
			return apiResponse;
		}
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("Success");
		apiResponse.setResponseObject(webpages);
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse<Webpages> updateWebpages(@RequestHeader String requestorId, @RequestBody Webpages req) {

		APIResponse<Webpages> apiResponse = new APIResponse<Webpages>();
		try {
			req = webpagesService.update(req, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.WEBPAGES_CONFIG_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.WEBPAGES_CONFIG_UPDATED.getCode());
			apiResponse.setResponseObject(req);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.WEBPAGES_CONFIG_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.WEBPAGES_CONFIG_UPDATION_FAILED.getCode());
		}
		return apiResponse;
	}

	@PutMapping("/delete/{id}")
	public APIResponse<Webpages> deleteWebpages(@RequestHeader String requestorId, @PathVariable String id) {

		APIResponse<Webpages> apiResponse = new APIResponse<Webpages>();
		try {
			Webpages webpages = webpagesService.delete(id, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.WEBPAGES_CONFIG_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.WEBPAGES_CONFIG_UPDATED.getCode());
			apiResponse.setResponseObject(webpages);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.WEBPAGES_CONFIG_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.WEBPAGES_CONFIG_UPDATION_FAILED.getCode());
		}
		return apiResponse;
	}

}
