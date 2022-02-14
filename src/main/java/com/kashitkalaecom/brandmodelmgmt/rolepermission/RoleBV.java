package com.kashitkalaecom.brandmodelmgmt.rolepermission;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;

@Component
public class RoleBV {

	@Autowired
	MasterDataService masterDataService;
	
	@Autowired
	RoleService roleService;
	
	private static final Logger logger = LoggerFactory.getLogger(RoleBV.class);

	public APIResponse<Role> bValidateCreate(String tenantCode, Role role, String locale) {

		APIResponse<Role> apiResponse = new APIResponse<>();
		 apiResponse.setProcessingSuccess(true);
		try {
			apiResponse = validateCreate(tenantCode, role, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}
	
		return apiResponse;
	}

	private APIResponse<Role> validateCreate(String tenantCode, Role role, String locale) {

		APIResponse<Role> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
       		
		// Role Name Already exists
		
        int roleNameCount = roleService.roleNameExists(role.getName());        
        
        if (roleNameCount > 0) {
        	apiResponse.setResponseCode(StatusCodeEnum.ROLE_DUPLICATE.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.ROLE_DUPLICATE.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        return apiResponse;
	}


	}
