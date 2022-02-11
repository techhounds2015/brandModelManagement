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
import com.kashitkalaecom.brandmodelmgmt.businessvalidation.PickUpAndDeliveryConfigurationBV;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.fieldvalidation.BrandFV;
import com.kashitkalaecom.brandmodelmgmt.fieldvalidation.ModelFV;
import com.kashitkalaecom.brandmodelmgmt.fieldvalidation.PickUpAndDeliveryConfigurationFV;
import com.kashitkalaecom.brandmodelmgmt.models.Brand;
import com.kashitkalaecom.brandmodelmgmt.models.Model;
import com.kashitkalaecom.brandmodelmgmt.models.PickUpAndDeliveryConfiguration;
import com.kashitkalaecom.brandmodelmgmt.service.BrandService;
import com.kashitkalaecom.brandmodelmgmt.service.ModelService;
import com.kashitkalaecom.brandmodelmgmt.service.PickUpAndDeliveryConfigurationService;

@RestController
@RequestMapping("/api/v1/pickUpAndDeliveryConfiguration")
public class PickUpAndDeliveryConfigurationController {

	@Autowired
	PickUpAndDeliveryConfigurationService pickUpAndDeliveryConfigurationService;

	@Autowired
	PickUpAndDeliveryConfigurationFV pickUpAndDeliveryConfigurationFV;
	
	@Autowired
	PickUpAndDeliveryConfigurationBV pickUpAndDeliveryConfigurationBV;

	@PostMapping("/create")
	public APIResponse pickUpAndDeliveryConfiguration(@RequestHeader String requestorId, @RequestBody PickUpAndDeliveryConfiguration pickUpAndDeliveryConfiguration) {
		APIResponse apiResponse = new APIResponse();

		try {

			apiResponse = pickUpAndDeliveryConfigurationFV.fValidateCreate(null, pickUpAndDeliveryConfiguration, null);
			if (!apiResponse.getValidationSuccess()) {
				return apiResponse;
			}
			
			apiResponse = pickUpAndDeliveryConfigurationBV.bValidateCreate(null, pickUpAndDeliveryConfiguration, null);
            if (!apiResponse.getProcessingSuccess()) {
                return apiResponse;
            }
            
			pickUpAndDeliveryConfiguration = pickUpAndDeliveryConfigurationService.save(pickUpAndDeliveryConfiguration, requestorId);

			apiResponse.setResponseCode(StatusCodeEnum.DELIVERY_CONFIG_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.DELIVERY_CONFIG_CREATED.getDesc());
			apiResponse.setResponseObject(pickUpAndDeliveryConfiguration);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.DELIVERY_CONFIG_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.DELIVERY_CONFIG_CREATION_FAILED.getDesc());
			apiResponse.setResponseObject(pickUpAndDeliveryConfiguration);
		}

		return apiResponse;
	}
	
	@GetMapping("/view/{modelId}")
	public APIResponse pickUpAndDeliveryConfiguration(@RequestHeader String requestorId, @PathVariable("pickUpAndDeliveryConfigurationId") String pickUpAndDeliveryConfigurationId) {

		PickUpAndDeliveryConfiguration pickUpAndDeliveryConfiguration = pickUpAndDeliveryConfigurationService.getpickUpAndDeliveryConfigurationById(pickUpAndDeliveryConfigurationId);
		APIResponse apiResponse = new APIResponse();
		if (pickUpAndDeliveryConfiguration == null) {
			apiResponse.setResponseCode(StatusCodeEnum.DELIVERY_CONFIG_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.DELIVERY_CONFIG_NOT_EXISTS.getDesc());
			return apiResponse;
		}
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(pickUpAndDeliveryConfiguration);
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse updatepickUpAndDeliveryConfiguration(@RequestHeader String requestorId, @RequestBody PickUpAndDeliveryConfiguration pickUpAndDeliveryConfiguration) {

		APIResponse apiResponse = new APIResponse();
		try {
			pickUpAndDeliveryConfiguration = pickUpAndDeliveryConfigurationService.update(pickUpAndDeliveryConfiguration, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.DELIVERY_CONFIG_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.DELIVERY_CONFIG_UPDATED.getDesc());
			apiResponse.setResponseObject(pickUpAndDeliveryConfiguration);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.DELIVERY_CONFIG_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.DELIVERY_CONFIG_UPDATION_FAILED.getDesc());
		}
		return apiResponse;
	}

	@PutMapping("/delete/{id}")
	public APIResponse deletepickUpAndDeliveryConfiguration(@RequestHeader String requestorId, @PathVariable String id) {

		APIResponse apiResponse = new APIResponse();
	try {	
		PickUpAndDeliveryConfiguration pickUpAndDeliveryConfiguration = pickUpAndDeliveryConfigurationService.delete(id, requestorId);
		apiResponse.setResponseCode(StatusCodeEnum.DELIVERY_CONFIG_UPDATED.getCode());
		apiResponse.setResponseMessage(StatusCodeEnum.DELIVERY_CONFIG_UPDATED.getDesc());
		apiResponse.setResponseObject(pickUpAndDeliveryConfiguration);
	} catch (Exception e) {
		apiResponse.setResponseCode(StatusCodeEnum.DELIVERY_CONFIG_UPDATION_FAILED.getCode());
		apiResponse.setResponseMessage(StatusCodeEnum.DELIVERY_CONFIG_UPDATION_FAILED.getDesc());
	}
	return apiResponse;
	}
}
