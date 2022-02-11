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
import com.kashitkalaecom.brandmodelmgmt.fieldvalidation.UserFV;
import com.kashitkalaecom.brandmodelmgmt.models.User;
import com.kashitkalaecom.brandmodelmgmt.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserFV userFV;

	@PostMapping("/create")
	public APIResponse user(@RequestHeader String requestorId, @RequestBody User user) {
		APIResponse apiResponse = new APIResponse();

		try {

			apiResponse = userFV.fValidateCreate(null, user, null);
			if (!apiResponse.getValidationSuccess()) {
				return apiResponse;
			}
			// Business Validation

			// Save
			user = userService.save(user, requestorId);

			apiResponse.setResponseCode(StatusCodeEnum.USER_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.USER_CREATED.getDesc());
			apiResponse.setResponseObject(user);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.USER_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.USER_CREATION_FAILED.getDesc());
			apiResponse.setResponseObject(user);
		}

		return apiResponse;
	}

	@GetMapping("/view/{userId}")
	public APIResponse user(@RequestHeader String requestorId, @PathVariable("userId") String userId) {

		User user = userService.getUserById(userId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(user);
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse updateuser(@RequestHeader String requestorId, @RequestBody User user) {

		user = userService.update(user, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(user);
		return apiResponse;
	}

	@PutMapping("/delete/{id}")
	public APIResponse deleteuser(@RequestHeader String requestorId, @PathVariable String id) {

		User user = userService.delete(id, requestorId);
		APIResponse apiResponse = new APIResponse();
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("success");
		apiResponse.setResponseObject(user);
		return apiResponse;
	}

}
