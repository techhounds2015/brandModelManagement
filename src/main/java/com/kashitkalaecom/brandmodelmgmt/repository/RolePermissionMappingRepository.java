package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kashitkalaecom.brandmodelmgmt.models.RolePermissionMapping;
@Repository
public interface RolePermissionMappingRepository  extends JpaRepository<RolePermissionMapping, String> {

	
	  @Query("from RolePermissionMapping rpm  where rpm.roleId =:roleId and rpm.permissionId =:permissionId")
	  public RolePermissionMapping getUserPermission(String roleId, String permissionId);
	 

}
