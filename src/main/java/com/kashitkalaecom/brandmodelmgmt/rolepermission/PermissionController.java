package com.kashitkalaecom.brandmodelmgmt.rolepermission;

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

@RestController
@RequestMapping("/api/v1/permission")
public class PermissionController {

	@Autowired
	PermissionService permissionService;
	
	@Autowired
	PermissionBV permissionBV;

	@PostMapping("/create")
	public APIResponse<Permission> createPermission(@RequestHeader String requestorId, @RequestBody Permission permission) {
		APIResponse<Permission> apiResponse = new APIResponse<>();

		try {
			
			apiResponse = permissionBV.bValidateCreate(null, permission, null);
            if (Boolean.FALSE.equals(apiResponse.getProcessingSuccess())) {
                return apiResponse;
            }
			permission = permissionService.save(permission, requestorId);

			apiResponse.setResponseCode(StatusCodeEnum.PERMISSION_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PERMISSION_CREATED.getDesc());
			apiResponse.setResponseObject(permission);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.PERMISSION_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PERMISSION_CREATION_FAILED.getDesc());
		}
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse<Permission> updatePermission(@RequestHeader String requestorId, @RequestBody Permission permission) {
		APIResponse<Permission> apiResponse = new APIResponse<>();

		try {
			
			int permissionExists = permissionService.permissionIdExists(permission.getId());

			if (permissionExists == 0) {
				apiResponse.setResponseCode(StatusCodeEnum.PERMISSION_NOT_EXISTS.getCode());
				apiResponse.setResponseMessage(StatusCodeEnum.PERMISSION_NOT_EXISTS.getDesc());
				return apiResponse;
			}
			
			permission = permissionService.update(permission, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.PERMISSION_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PERMISSION_UPDATED.getDesc());
			apiResponse.setResponseObject(permission);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.PERMISSION_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PERMISSION_UPDATION_FAILED.getDesc());
		}
		return apiResponse;
	}
	
	
	@PutMapping("/delete/{permissionId}")
	public APIResponse<Permission> delete(@RequestHeader String requestorId, @PathVariable String permissionId) {
		APIResponse<Permission> apiResponse = new APIResponse<>();

		try {

			int permissionExists = permissionService.permissionIdExists(permissionId);

			if (permissionExists == 0) {
				apiResponse.setResponseCode(StatusCodeEnum.PERMISSION_NOT_EXISTS.getCode());
				apiResponse.setResponseMessage(StatusCodeEnum.PERMISSION_NOT_EXISTS.getDesc());
				return apiResponse;
			}

			Permission permission = permissionService.delete(requestorId, permissionId);
			apiResponse.setResponseCode(StatusCodeEnum.PERMISSION_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PERMISSION_UPDATED.getDesc());
			apiResponse.setResponseObject(permission);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.ERROR_WHILE_RETREVING_DATA.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ERROR_WHILE_RETREVING_DATA.getDesc());
		}
		return apiResponse;
	}
	

	@GetMapping("/view/{permissionId}")
	public APIResponse<Permission> permission(@RequestHeader String requestorId, @PathVariable String permissionId) {
		APIResponse<Permission> apiResponse = new APIResponse<>();

		try {

			int permissionExists = permissionService.permissionIdExists(permissionId);

			if (permissionExists == 0) {
				apiResponse.setResponseCode(StatusCodeEnum.PERMISSION_NOT_EXISTS.getCode());
				apiResponse.setResponseMessage(StatusCodeEnum.PERMISSION_NOT_EXISTS.getDesc());
				return apiResponse;
			}

			Permission permission = permissionService.getPermissionById(permissionId);
			apiResponse.setResponseCode("200");
			apiResponse.setResponseMessage("Success");
			apiResponse.setResponseObject(permission);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.ERROR_WHILE_RETREVING_DATA.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ERROR_WHILE_RETREVING_DATA.getDesc());
		}
		return apiResponse;
	}
}
