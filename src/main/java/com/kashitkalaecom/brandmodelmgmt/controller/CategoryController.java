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
import com.kashitkalaecom.brandmodelmgmt.businessvalidation.CategoryBV;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.fieldvalidation.CategoryFV;
import com.kashitkalaecom.brandmodelmgmt.models.Category;
import com.kashitkalaecom.brandmodelmgmt.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	CategoryFV categoryFV;
	
	@Autowired
	CategoryBV categoryBV;

	@PostMapping("/create")
	public APIResponse<Category> category(@RequestHeader String requestorId, @RequestBody Category category) {
		APIResponse<Category> apiResponse = new APIResponse<>();

		try {

			apiResponse = categoryFV.fValidateCreate(null, category, null);
			if (!apiResponse.getValidationSuccess()) {
				return apiResponse;
			}

			apiResponse = categoryBV.bValidateCreate(null, category, null);
            if (!apiResponse.getProcessingSuccess()) {
                return apiResponse;
            }

			// Save
			category = categoryService.save(category, requestorId);

			apiResponse.setResponseCode(StatusCodeEnum.CATEGORY_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.CATEGORY_CREATED.getDesc());
			apiResponse.setResponseObject(category);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.CATEGORY_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.CATEGORY_CREATION_FAILED.getDesc());
			apiResponse.setResponseObject(category);
		}

		return apiResponse;
	}

	@GetMapping("/view/{categoryId}")
	public APIResponse<Category> category(@RequestHeader String requestorId, @PathVariable("categoryId") String categoryId) {

		Category category = categoryService.getCategoryById(categoryId);
		APIResponse<Category> apiResponse = new APIResponse<>();

		if (category == null) {
			apiResponse.setResponseCode(StatusCodeEnum.CATEGORY_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.CATEGORY_NOT_EXISTS.getDesc());
			return apiResponse;
		}

		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("Success");
		apiResponse.setResponseObject(category);
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse<Category> updatecategory(@RequestHeader String requestorId, @RequestBody Category category) {

		APIResponse<Category> apiResponse = new APIResponse<>();

		try {
			category = categoryService.update(category, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.CATEGORY_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.CATEGORY_UPDATED.getDesc());
			apiResponse.setResponseObject(category);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.CATEGORY_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.CATEGORY_UPDATION_FAILED.getDesc());
		}
		return apiResponse;
	}

	@PutMapping("/delete/{id}")
	public APIResponse<Category> deletecategory(@RequestHeader String requestorId, @RequestParam String id) {

		APIResponse<Category> apiResponse = new APIResponse<>();
		try {
			Category category = categoryService.delete(id, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.CATEGORY_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.CATEGORY_UPDATED.getDesc());
			apiResponse.setResponseObject(category);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.CATEGORY_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.CATEGORY_UPDATION_FAILED.getDesc());
		}
		return apiResponse;
	}

}
