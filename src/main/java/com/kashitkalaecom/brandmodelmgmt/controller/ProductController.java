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
import com.kashitkalaecom.brandmodelmgmt.models.Product;
import com.kashitkalaecom.brandmodelmgmt.service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/create")
	public APIResponse product(@RequestHeader String requestorId, @RequestBody Product product) {

		product = productService.save(product, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(product);
		return apiResponse;
	}

	@GetMapping("/view/{productId}")
	public APIResponse product(@RequestHeader String requestorId, @PathVariable("productId") String productId) {

		Product product = productService.getProductById(productId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(product);
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse updateproduct(@RequestHeader String requestorId, @RequestBody Product product) {

		product = productService.update(product, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(product);
		return apiResponse;
	}

	@PutMapping("/delete/{id}")
	public APIResponse deleteproduct(@RequestHeader String requestorId, @PathVariable String id) {

		Product product = productService.delete(id, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(product);
		return apiResponse;
	}

}
