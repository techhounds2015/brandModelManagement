package com.kashitkalaecom.brandmodelmgmt.rolepermission;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;

@Component
public class PermissionBV {

	@Autowired
	MasterDataService masterDataService;
	
	@Autowired
	PermissionService permissionService;
	
	private static final Logger logger = LoggerFactory.getLogger(PermissionBV.class);

	public APIResponse<Permission> bValidateCreate(String tenantCode, Permission permission, String locale) {

		APIResponse<Permission> apiResponse = new APIResponse<>();
		 apiResponse.setProcessingSuccess(true);
		try {
			apiResponse = validateCreate(tenantCode, permission, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}
	
		return apiResponse;
	}

	private APIResponse<Permission> validateCreate(String tenantCode, Permission permission, String locale) {

		APIResponse<Permission> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
       		
		// Role Name Already exists
		
        int roleNameCount = permissionService.permissionNameExists(permission.getName());        
        
        if (roleNameCount > 0) {
        	apiResponse.setResponseCode(StatusCodeEnum.PERMISSION_DUPLICATE.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.PERMISSION_DUPLICATE.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        return apiResponse;
	}


	}
