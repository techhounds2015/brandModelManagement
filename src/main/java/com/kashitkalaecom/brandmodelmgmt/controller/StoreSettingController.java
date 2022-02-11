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
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.fieldvalidation.StoreSettingFV;
import com.kashitkalaecom.brandmodelmgmt.models.StoreSetting;
import com.kashitkalaecom.brandmodelmgmt.service.StoreSettingService;

@RestController
@RequestMapping("/api/v1/storeSetting")
public class StoreSettingController {

	@Autowired
	StoreSettingService storeSettingService;

	@Autowired
	StoreSettingFV storeSettingFV;

	@PostMapping("/create")
	public APIResponse storeSetting(@RequestHeader String requestorId, @RequestBody StoreSetting storeSetting) {
		APIResponse apiResponse = new APIResponse();

		try {

			apiResponse = storeSettingFV.fValidateCreate(null, storeSetting, null);
			if (!apiResponse.getValidationSuccess()) {
				return apiResponse;
			}
			// Business Validation

			// Save
			storeSetting = storeSettingService.save(storeSetting, requestorId);

			apiResponse.setResponseCode(StatusCodeEnum.USER_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.USER_CREATED.getDesc());
			apiResponse.setResponseObject(storeSetting);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.USER_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.USER_CREATION_FAILED.getDesc());
			apiResponse.setResponseObject(storeSetting);
		}

		return apiResponse;
	}

	@GetMapping("/view/{storeSettingId}")
	public APIResponse storeSetting(@RequestHeader String requestorId,
			@PathVariable("storeSettingId") String storeSettingId) {

		StoreSetting storeSetting = storeSettingService.getStoreSettingById(storeSettingId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(storeSetting);
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse updatestoreSetting(@RequestHeader String requestorId, @RequestBody StoreSetting storeSetting) {

		storeSetting = storeSettingService.update(storeSetting, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(storeSetting);
		return apiResponse;
	}

	@PutMapping("/delete/{id}")
	public APIResponse deletestoreSetting(@RequestHeader String requestorId, @PathVariable String id) {

		StoreSetting storeSetting = storeSettingService.delete(id, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(storeSetting);
		return apiResponse;
	}

}
