
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
import com.kashitkalaecom.brandmodelmgmt.models.DeliverySlots;
import com.kashitkalaecom.brandmodelmgmt.service.DeliverySlotsService;

@RestController

@RequestMapping("/api/v1/deliverySlots")
public class DeliverySlotsController {

	@Autowired
	DeliverySlotsService deliverySlotsService;

	@PostMapping("/create")
	public APIResponse deliverySlots(@RequestHeader String requestorId, @RequestBody DeliverySlots req) {

		req = deliverySlotsService.save(req, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(req);
		return apiResponse;
	}

	@GetMapping("/view/{id}")
	public APIResponse deliverySlots(@RequestHeader String requestorId, @PathVariable("id") String id) {

		DeliverySlots deliverySlots = deliverySlotsService.getDeliverySlotsById(id);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(deliverySlots);
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse updateDeliverySlots(@RequestHeader String requestorId,

			@RequestBody DeliverySlots req) {

		req = deliverySlotsService.update(req, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(req);
		return apiResponse;
	}

	@PutMapping("/delete")
	public APIResponse deleteDeliverySlots(@RequestHeader String requestorId, @RequestBody DeliverySlots deliverySlots) {

		deliverySlots = deliverySlotsService.delete(deliverySlots, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(deliverySlots);
		return apiResponse;
	}

}
