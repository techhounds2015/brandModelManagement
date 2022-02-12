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
import com.kashitkalaecom.brandmodelmgmt.businessvalidation.ReferralBV;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.fieldvalidation.ReferralFV;
import com.kashitkalaecom.brandmodelmgmt.models.Brand;
import com.kashitkalaecom.brandmodelmgmt.models.Referral;
import com.kashitkalaecom.brandmodelmgmt.models.Webpages;
import com.kashitkalaecom.brandmodelmgmt.service.ReferralService;
import com.kashitkalaecom.brandmodelmgmt.service.WebpagesService;

@RestController
@RequestMapping("/api/v1/referral")
public class ReferralController {

	@Autowired
	ReferralService referralService;

	@Autowired
	ReferralFV referralFV;
	
	@Autowired
	ReferralBV referralBV;
	
	@PostMapping("/create")
	public APIResponse<Referral> referral(@RequestHeader String requestorId, @RequestBody Referral req) {
		APIResponse<Referral> apiResponse = new APIResponse<Referral>();
		try {
			apiResponse = referralFV.fValidateCreate(null, req, null);
			if (!apiResponse.getValidationSuccess()) {
				return apiResponse;
			}
			apiResponse = referralBV.bValidateCreate(null, req, null);
            if (!apiResponse.getProcessingSuccess()) {
                return apiResponse;
            }

			req = referralService.save(req, requestorId);

			apiResponse.setResponseCode(StatusCodeEnum.REFERRAL_CODE_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.REFERRAL_CODE_CREATED.getDesc());
			apiResponse.setResponseObject(req);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.REFERRAL_CODE_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.REFERRAL_CODE_CREATION_FAILED.getDesc());
			apiResponse.setResponseObject(req);
		}
		return apiResponse;
	}

	@GetMapping("/view/{id}")
	public APIResponse<Referral> referralById(@RequestHeader String requestorId, @PathVariable("id") String id) {
		APIResponse<Referral> apiResponse = new APIResponse<Referral>();
		Referral referral = referralService.getReferralById(id);

		if (referral == null) {
			apiResponse.setResponseCode(StatusCodeEnum.REFERRAL_CODE_NOT_EXISTS.getCode());
			apiResponse.setResponseCode(StatusCodeEnum.REFERRAL_CODE_NOT_EXISTS.getDesc());
			apiResponse.setResponseObject(referral);
			return apiResponse;
		}
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("Success");
		apiResponse.setResponseObject(referral);
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse<Referral> updateReferral(@RequestHeader String requestorId, @RequestBody Referral req) {

		APIResponse<Referral> apiResponse = new APIResponse<Referral>();
		try {
			req = referralService.update(req, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.REFERRAL_CODE_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.REFERRAL_CODE_UPDATED.getCode());
			apiResponse.setResponseObject(req);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.REFERRAL_CODE_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.REFERRAL_CODE_UPDATION_FAILED.getCode());
		}
		return apiResponse;
    }

	@PutMapping("/delete/{id}")
	public APIResponse<Referral> deleteReferral(@RequestHeader String requestorId, @RequestParam String id) {

		APIResponse<Referral> apiResponse = new APIResponse<Referral>();
		try {
			Referral referral = referralService.delete(id, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.REFERRAL_CODE_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.REFERRAL_CODE_UPDATED.getCode());
			apiResponse.setResponseObject(referral);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.REFERRAL_CODE_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.REFERRAL_CODE_UPDATION_FAILED.getCode());
		}
		return apiResponse;
	}

	
}
