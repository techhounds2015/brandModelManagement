package com.kashitkalaecom.brandmodelmgmt.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.Permission;
import com.kashitkalaecom.brandmodelmgmt.models.RolePermissionMapping;
import com.kashitkalaecom.brandmodelmgmt.models.User;
import com.kashitkalaecom.brandmodelmgmt.repository.PermissionRepository;
import com.kashitkalaecom.brandmodelmgmt.repository.RolePermissionMappingRepository;
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
