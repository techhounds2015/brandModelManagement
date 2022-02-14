package com.kashitkalaecom.brandmodelmgmt.rolepermission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class PermissionService {

	@Autowired
	PermissionRepository permissionRepository;

	public Permission save(Permission permission, String requestorId) {
		permission.setCreatedBy(requestorId);
		permission.setCreatedOn(CustomClock.timestamp());
		return permissionRepository.save(permission);
	}

	

	public Permission update(Permission permission, String requestorId) {
		permission.setModifiedBy(requestorId);
		permission.setModifiedOn(CustomClock.timestamp());
		return permissionRepository.save(permission);
	}


	public int permissionIdExists(String id) {
		return permissionRepository.permissionIdExists(id);
	}



	public Permission getPermissionById(String id) {
		return permissionRepository.getPermissionById(id);
	}



	public int permissionNameExists(String name) {
		return permissionRepository.permissionNameExists(name);
	}



	public Permission delete(String requestorId, String permissionId) {
		Permission permission = permissionRepository.getById(permissionId);
		permission.setModifiedBy(requestorId);
		permission.setModifiedOn(CustomClock.timestamp());
		permission.setStatus(false);
		return permissionRepository.save(permission);
	}

}
