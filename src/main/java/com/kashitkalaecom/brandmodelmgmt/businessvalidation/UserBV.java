package com.kashitkalaecom.brandmodelmgmt.businessvalidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.User;
import com.kashitkalaecom.brandmodelmgmt.rolepermission.PermissionService;
import com.kashitkalaecom.brandmodelmgmt.rolepermission.RoleService;
import com.kashitkalaecom.brandmodelmgmt.service.UserService;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;

@Component
public class UserBV {

	@Autowired
	MasterDataService masterDataService;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;
	
	@Autowired
	PermissionService permissionService;

	private static final Logger logger = LoggerFactory.getLogger(UserBV.class);

	public APIResponse<User> bValidateCreate(String tenantCode, User user, String locale) {

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

		int user1 = userService.userEmailExists(user.getEmail());

		if (user1 > 0) {
			apiResponse.setResponseCode(StatusCodeEnum.USER_DUPLICATE.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.USER_DUPLICATE.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		int userMobile = userService.userMobileExists(user.getMobile());

		if (userMobile > 0) {
			apiResponse.setResponseCode(StatusCodeEnum.USER_DUPLICATE.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.USER_DUPLICATE.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		int roleCount = roleService.roleIdExists(user.getRoleId());

		if (roleCount == 0) {
			apiResponse.setResponseCode(StatusCodeEnum.ROLE_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ROLE_NOT_EXISTS.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		return apiResponse;
	}

}
