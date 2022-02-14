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
import com.kashitkalaecom.brandmodelmgmt.requests.LoginRequest;
import com.kashitkalaecom.brandmodelmgmt.responses.LoginResponse;
import com.kashitkalaecom.brandmodelmgmt.service.LoginService;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	LoginBV loginBV;
	
	@PostMapping("/signIn")
	public APIResponse<LoginResponse> user(@RequestHeader String requestorId, @RequestBody LoginRequest loginRequest) {
		APIResponse<LoginResponse> apiResponse = new APIResponse<>();

		try {
			
			apiResponse = loginBV.bValidateCreate(null, loginRequest, null);
            if (Boolean.FALSE.equals(apiResponse.getProcessingSuccess())) {

                return apiResponse;
            }
            LoginResponse  response= loginService.vaildateUser(apiResponse,requestorId);

			apiResponse.setResponseCode("200");
			apiResponse.setResponseMessage("Success");
			apiResponse.setResponseObject(response);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.USER_LOGIN_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.USER_LOGIN_FAILED.getDesc());
			apiResponse.setResponseObject(null);
		}

		return apiResponse;
	}
}
