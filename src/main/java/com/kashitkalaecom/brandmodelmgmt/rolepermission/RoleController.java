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
@RequestMapping("/api/v1/role")
public class RoleController {

	@Autowired
	RoleService roleService;

	@PostMapping("/create")
	public APIResponse<Role> createRole(@RequestHeader String requestorId, @RequestBody Role role) {
		APIResponse<Role> apiResponse = new APIResponse<>();

		try {
			role = roleService.save(role, requestorId);

			apiResponse.setResponseCode(StatusCodeEnum.ROLE_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ROLE_CREATED.getDesc());
			apiResponse.setResponseObject(role);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.ROLE_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ROLE_CREATION_FAILED.getDesc());
		}
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse<Role> updateRole(@RequestHeader String requestorId, @RequestBody Role role) {
		APIResponse<Role> apiResponse = new APIResponse<>();

		try {
			
			int roleExists = roleService.roleIdExists(role.getId());

			if (roleExists == 0) {
				apiResponse.setResponseCode(StatusCodeEnum.ROLE_NOT_EXISTS.getCode());
				apiResponse.setResponseMessage(StatusCodeEnum.ROLE_NOT_EXISTS.getDesc());
				return apiResponse;
			}
			
			role = roleService.update(role, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.ROLE_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ROLE_UPDATED.getDesc());
			apiResponse.setResponseObject(role);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.ROLE_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ROLE_UPDATION_FAILED.getDesc());
		}
		return apiResponse;
	}

	@GetMapping("/view/{roleId}")
	public APIResponse<Role> role(@RequestHeader String requestorId, @PathVariable String roleId) {
		APIResponse<Role> apiResponse = new APIResponse<>();

		try {

			int roleExists = roleService.roleIdExists(roleId);

			if (roleExists == 0) {
				apiResponse.setResponseCode(StatusCodeEnum.ROLE_NOT_EXISTS.getCode());
				apiResponse.setResponseMessage(StatusCodeEnum.ROLE_NOT_EXISTS.getDesc());
				return apiResponse;
			}

			Role role = roleService.getRoleById(requestorId);
			apiResponse.setResponseCode("200");
			apiResponse.setResponseMessage("Success");
			apiResponse.setResponseObject(role);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.ERROR_WHILE_RETREVING_DATA.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ERROR_WHILE_RETREVING_DATA.getDesc());
		}
		return apiResponse;
	}
}
