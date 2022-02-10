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
import com.kashitkalaecom.brandmodelmgmt.models.Outlet;
import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.service.OutletService;

@RestController
@RequestMapping("/api/v1/outlet")
public class OutletController {

	@Autowired
	OutletService outletService;
	
	@PostMapping("/create")
	public APIResponse Outlet(@RequestHeader String requestorId, @RequestBody Outlet outlet) {

		 outlet = outletService.save(outlet, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(outlet);
		return apiResponse;
	}
	
	@GetMapping("/view/{id}")
	public APIResponse outlet(@RequestHeader String requestorId, @PathVariable("id") String id) {

		Outlet outlet = outletService.getOutletById(id);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(outlet);
		return apiResponse;
	}
	
	@PutMapping("/update")
	public APIResponse updateOutlet(@RequestHeader String requestorId,
			@RequestBody Outlet outlet) {

		 outlet = outletService.update(outlet, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(outlet);
		return apiResponse;
	}
	
	@PutMapping("/delete")
	public APIResponse deletebrand(@RequestHeader String requestorId, @RequestBody Outlet outlet) {

		 outlet = outletService.delete(outlet, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(outlet);
		return apiResponse;
	}


	
}
