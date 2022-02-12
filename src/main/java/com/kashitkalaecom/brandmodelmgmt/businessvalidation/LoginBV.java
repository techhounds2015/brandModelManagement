package com.kashitkalaecom.brandmodelmgmt.businessvalidation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Product;
import com.kashitkalaecom.brandmodelmgmt.models.User;
import com.kashitkalaecom.brandmodelmgmt.service.LoginService;
import com.kashitkalaecom.brandmodelmgmt.service.ProductService;
import com.kashitkalaecom.brandmodelmgmt.service.UserService;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;
@Component
public class LoginBV {

	@Autowired
	UserService userService;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	LoginBV loginBV;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginBV.class);
	
	public APIResponse bValidateCreate(String tenantCode, User user, String locale) {
		
		APIResponse<User> apiResponse = new APIResponse<>();
		 apiResponse.setProcessingSuccess(true);
		try {
			apiResponse = validateCreate(tenantCode, user, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}
	
		return apiResponse;
	}

	private APIResponse<User> validateCreate(String tenantCode, User user, String locale) {
		
		APIResponse<User> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
        List<User> userList = userService.getByUserName(tenantCode, "User");
        
        if (userList != null && !userList.contains(user.getName())) {
        	apiResponse.setResponseCode(StatusCodeEnum.USER_NOT_EXISTS.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.USER_NOT_EXISTS.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
		/*
		 * User user1 = loginService.getUserByPassword(user.getPassword()); if (user1 !=
		 * null) {
		 * apiResponse.setResponseCode(StatusCodeEnum.USER_NOT_EXISTS.getCode());
		 * apiResponse.setResponseMessage(StatusCodeEnum.USER_NOT_EXISTS.getDesc());
		 * apiResponse.setProcessingSuccess(false); return apiResponse; }
		 */
        
        return apiResponse;
	}

}
