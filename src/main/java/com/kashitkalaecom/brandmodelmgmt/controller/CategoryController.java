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
import com.kashitkalaecom.brandmodelmgmt.models.Category;
import com.kashitkalaecom.brandmodelmgmt.models.Model;
import com.kashitkalaecom.brandmodelmgmt.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@PostMapping("/create")
	public APIResponse category(@RequestHeader String requestorId, @RequestBody Category category) {

		category = categoryService.save(category, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(category);
		return apiResponse;
	}

	@GetMapping("/view/{categoryId}")
	public APIResponse category(@RequestHeader String requestorId, @PathVariable("categoryId") String categoryId) {

		Category category = categoryService.getCategoryById(categoryId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(category);
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse updatecategory(@RequestHeader String requestorId, @RequestBody Category category) {

		category = categoryService.update(category, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(category);
		return apiResponse;
	}

	@PutMapping("/delete/{id}")
	public APIResponse deletecategory(@RequestHeader String requestorId, @RequestParam String id) {

		Category category = categoryService.delete(id, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(category);
		return apiResponse;
	}

}
