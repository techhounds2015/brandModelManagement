package com.kashitkalaecom.brandmodelmgmt.rolepermission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public Role save(Role role, String requestorId) {
		role.setCreatedBy(requestorId);
		role.setCreatedOn(CustomClock.timestamp());
		return roleRepository.save(role);
	}

	

	public Role update(Role role, String requestorId) {
		role.setModifiedBy(requestorId);
		role.setModifiedOn(CustomClock.timestamp());
		return roleRepository.save(role);
	}


	public int roleIdExists(String roleId) {
		return roleRepository.roleIdExists(roleId);
	}



	public Role getRoleById(String roleId) {
		return roleRepository.getRoleById(roleId);
	}

}
