package com.kashitkalaecom.brandmodelmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.businessvalidation.LoginBV;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.User;
import com.kashitkalaecom.brandmodelmgmt.service.LoginService;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	LoginBV loginBV;
	
	@PostMapping("/loginwithpassword")
	public APIResponse user(@RequestHeader String tenantCode, @RequestHeader String requestorId, @RequestBody User user, @RequestHeader String locale) {
		APIResponse apiResponse = new APIResponse();

		try {
			
			apiResponse = loginBV.bValidateCreate(tenantCode, user, locale);
            if (!apiResponse.getProcessingSuccess()) {
                return apiResponse;
            }
			user = loginService.save(user, requestorId);

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
}
