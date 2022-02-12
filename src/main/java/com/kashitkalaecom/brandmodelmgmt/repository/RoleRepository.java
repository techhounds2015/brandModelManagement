package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kashitkalaecom.brandmodelmgmt.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{
	
	@Query("Select count(1) from role c where c.id =:id ")
	public int roleIdExists(String id);


}
