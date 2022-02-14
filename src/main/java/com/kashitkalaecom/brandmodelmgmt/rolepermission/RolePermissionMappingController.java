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
@RequestMapping("/api/v1/rolePermissionMapping")
public class RolePermissionMappingController {

	@Autowired
	RolePermissionMappingService rolePermissionMappingService;

	@Autowired
	RoleBV roleBV;

	@PostMapping("/create")
	public APIResponse<RolePermissionMapping> create(@RequestHeader String requestorId,
			@RequestBody RolePermissionMapping rolePermissionMapping) {
		APIResponse<RolePermissionMapping> apiResponse = new APIResponse<>();

		try {

			rolePermissionMapping = rolePermissionMappingService.save(rolePermissionMapping, requestorId);

			apiResponse.setResponseCode(StatusCodeEnum.ROLE_PERMISSION_MAPPING_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ROLE_PERMISSION_MAPPING_CREATED.getDesc());
			apiResponse.setResponseObject(rolePermissionMapping);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.ROLE_PERMISSION_MAPPING_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ROLE_PERMISSION_MAPPING_CREATION_FAILED.getDesc());
		}
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse<RolePermissionMapping> update(@RequestHeader String requestorId,
			@RequestBody RolePermissionMapping rolePermissionMapping) {
		APIResponse<RolePermissionMapping> apiResponse = new APIResponse<>();

		try {

			rolePermissionMapping = rolePermissionMappingService.update(rolePermissionMapping, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.ROLE_PERMISSION_MAPPING_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ROLE_PERMISSION_MAPPING_UPDATED.getDesc());
			apiResponse.setResponseObject(rolePermissionMapping);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.ROLE_PERMISSION_MAPPING_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ROLE_PERMISSION_MAPPING_UPDATION_FAILED.getDesc());
		}
		return apiResponse;
	}

	@PutMapping("/delete/{id}")
	public APIResponse<RolePermissionMapping> delete(@RequestHeader String requestorId, @PathVariable String id) {
		APIResponse<RolePermissionMapping> apiResponse = new APIResponse<>();

		try {

			RolePermissionMapping rolePermissionMapping = rolePermissionMappingService.delete(id, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.ROLE_PERMISSION_MAPPING_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ROLE_PERMISSION_MAPPING_UPDATED.getDesc());
			apiResponse.setResponseObject(rolePermissionMapping);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.ROLE_PERMISSION_MAPPING_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ROLE_PERMISSION_MAPPING_UPDATION_FAILED.getDesc());
		}
		return apiResponse;
	}

	@GetMapping("/view/{id}")
	public APIResponse<RolePermissionMapping> view(@RequestHeader String requestorId, @PathVariable String id) {
		APIResponse<RolePermissionMapping> apiResponse = new APIResponse<>();

		try {
			
			int roleExists = rolePermissionMappingService.mappingIdExists(id);

			if (roleExists == 0) {
				apiResponse.setResponseCode(StatusCodeEnum.ROLE_PERMISSION_MAPPING_NOT_EXISTS.getCode());
				apiResponse.setResponseMessage(StatusCodeEnum.ROLE_PERMISSION_MAPPING_NOT_EXISTS.getDesc());
				return apiResponse;
			}

			RolePermissionMapping rolePermissionMapping = rolePermissionMappingService.getMappingById(id);
			apiResponse.setResponseCode("200");
			apiResponse.setResponseMessage("Success");
			apiResponse.setResponseObject(rolePermissionMapping);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.ERROR_WHILE_RETREVING_DATA.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ERROR_WHILE_RETREVING_DATA.getDesc());
		}
		return apiResponse;
	}
}
