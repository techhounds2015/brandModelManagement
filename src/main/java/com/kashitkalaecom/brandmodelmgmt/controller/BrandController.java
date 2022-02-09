package com.kashitkalaecom.brandmodelmgmt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {

	@GetMapping("/view/{brandId}")
	public APIResponse brand(@RequestHeader String tenant, @RequestHeader String requestorId,
			@PathVariable("brandId") String brandId) {

		APIResponse apiResponse = new APIResponse();
		return apiResponse;
	}

}
