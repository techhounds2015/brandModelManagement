package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kashitkalaecom.brandmodelmgmt.models.Permission;


@Repository
public interface PermissionRepository extends JpaRepository<Permission, String>{
	
	public Permission getByName(String name);

}
