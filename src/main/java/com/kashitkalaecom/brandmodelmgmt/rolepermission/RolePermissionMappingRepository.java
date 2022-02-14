package com.kashitkalaecom.brandmodelmgmt.rolepermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface RolePermissionMappingRepository  extends JpaRepository<RolePermissionMapping, String> {

	
	  @Query("from RolePermissionMapping rpm  where rpm.roleId =:roleId and rpm.permissionId =:permissionId")
	  public RolePermissionMapping getUserPermission(String roleId, String permissionId);
	 

}
