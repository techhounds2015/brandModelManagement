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
import com.kashitkalaecom.brandmodelmgmt.businessvalidation.DeliverySlotsBV;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.fieldvalidation.DeliverySlotsFV;
import com.kashitkalaecom.brandmodelmgmt.models.DeliverySlots;
import com.kashitkalaecom.brandmodelmgmt.service.DeliverySlotsService;

@RestController

@RequestMapping("/api/v1/deliverySlots")
public class DeliverySlotsController {

	@Autowired
	DeliverySlotsService deliverySlotsService;
	
	@Autowired
	DeliverySlotsFV deliverySlotsFV;
	
	@Autowired
	DeliverySlotsBV deliverySlotsBV;

	@PostMapping("/create")
	public APIResponse<DeliverySlots> createDeliverySlots(@RequestHeader String requestorId, @RequestBody DeliverySlots req) {
		APIResponse<DeliverySlots> apiResponse = new APIResponse<DeliverySlots>();
		try {
			apiResponse = deliverySlotsFV.fValidateCreate(null, req, null);
			if (!apiResponse.getValidationSuccess()) {
				return apiResponse;
			}
			
			apiResponse = deliverySlotsBV.bValidateCreate(null, req, null);
            if (!apiResponse.getProcessingSuccess()) {
                return apiResponse;
            }
			req = deliverySlotsService.save(req, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.DELIVERY_CONFIG_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.DELIVERY_CONFIG_CREATED.getDesc());
			apiResponse.setResponseObject(req);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.DELIVERY_CONFIG_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.DELIVERY_CONFIG_CREATION_FAILED.getDesc());
			apiResponse.setResponseObject(req);
		}
		return apiResponse;
	}

	@GetMapping("/view/{id}")
	public APIResponse<DeliverySlots> deliverySlots(@RequestHeader String requestorId, @PathVariable("id") String id) {
		APIResponse<DeliverySlots> apiResponse = new APIResponse<DeliverySlots>();
		DeliverySlots deliverySlots = deliverySlotsService.getDeliverySlotsById(id);
		if (deliverySlots == null) {
			apiResponse.setResponseCode(StatusCodeEnum.DELIVERY_CONFIG_NOT_EXISTS.getCode());
			apiResponse.setResponseCode(StatusCodeEnum.DELIVERY_CONFIG_NOT_EXISTS.getDesc());
			apiResponse.setResponseObject(deliverySlots);
			return apiResponse;
		}
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("Success");
		apiResponse.setResponseObject(deliverySlots);
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse<DeliverySlots> updateDeliverySlots(@RequestHeader String requestorId, @RequestBody DeliverySlots req) {

		APIResponse<DeliverySlots> apiResponse = new APIResponse<DeliverySlots>();
		try {
			req = deliverySlotsService.update(req, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.DELIVERY_CONFIG_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.DELIVERY_CONFIG_UPDATED.getCode());
			apiResponse.setResponseObject(req);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.DELIVERY_CONFIG_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.DELIVERY_CONFIG_UPDATION_FAILED.getCode());
		}
		return apiResponse;
	}

	@PutMapping("/delete/{id}")
	public APIResponse<DeliverySlots> deleteDeliverySlots(@RequestHeader String requestorId,  @PathVariable String id) {

		APIResponse<DeliverySlots> apiResponse = new APIResponse<DeliverySlots>();
		try {
			DeliverySlots deliverySlots = deliverySlotsService.delete(id, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.DELIVERY_CONFIG_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.DELIVERY_CONFIG_UPDATED.getCode());
			apiResponse.setResponseObject(deliverySlots);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.DELIVERY_CONFIG_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.DELIVERY_CONFIG_UPDATION_FAILED.getCode());
		}
		return apiResponse;
		}

}
