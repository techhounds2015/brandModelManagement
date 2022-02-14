package com.kashitkalaecom.brandmodelmgmt.rolepermission;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.User;
import com.kashitkalaecom.brandmodelmgmt.repository.UserRepository;

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

	
}
