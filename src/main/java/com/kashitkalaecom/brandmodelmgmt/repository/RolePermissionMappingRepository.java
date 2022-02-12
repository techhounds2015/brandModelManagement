package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kashitkalaecom.brandmodelmgmt.models.RolePermissionMapping;

public interface RolePermissionMappingRepository  extends JpaRepository<RolePermissionMapping, String> {

	
	@Query("Select rpm.* from RolePermissionMapping rpm join Role r on rpm.roleId = r.id "
			+ " join Permission p on p.id=rpm.permissionId "
			+ " join User u on u.roleId = r.id "
			+ " where p.name :=module and u.name :=name")
	public RolePermissionMapping getUserPermission(String module, String name);

}
