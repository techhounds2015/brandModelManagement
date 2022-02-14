package com.kashitkalaecom.brandmodelmgmt.rolepermission;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.User;
import com.kashitkalaecom.brandmodelmgmt.repository.UserRepository;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class RolePermissionMappingService {

	@Autowired
	RolePermissionMappingRepository mapping;
	
	@Autowired
	PermissionRepository permissionRepository;
	
	@Autowired
	UserRepository repository;

	@Transactional
	public RolePermissionMapping getUserPermission(String module, String requestUserid) {
		
		User user= repository.getById(requestUserid);
		Permission permission= permissionRepository.getByName(module);
		return mapping.getUserPermission(user.getRoleId(),permission.getId());
		 
	}

	public RolePermissionMapping save(RolePermissionMapping rolePermissionMapping, String requestorId) {
		rolePermissionMapping.setCreatedBy(requestorId);
		rolePermissionMapping.setCreatedOn(CustomClock.timestamp());
		return mapping.save(rolePermissionMapping);
	}
	
	public RolePermissionMapping update(RolePermissionMapping rolePermissionMapping, String requestorId) {
		rolePermissionMapping.setModifiedBy(requestorId);
		rolePermissionMapping.setModifiedOn(CustomClock.timestamp());
		return mapping.save(rolePermissionMapping);
	}
	
	public RolePermissionMapping delete(String id, String requestorId) {
	
		RolePermissionMapping rolePermissionMapping = mapping.getById(id);
		rolePermissionMapping.setModifiedBy(requestorId);
		rolePermissionMapping.setModifiedOn(CustomClock.timestamp());
		rolePermissionMapping.setStatus(false);
		return mapping.save(rolePermissionMapping);
	}

	public RolePermissionMapping getMappingById(String id) {
		return mapping.getById(id);
	}

	public int mappingIdExists(String id) {
		return mapping.mappingIdExists(id);
	}
	
	

	
}
