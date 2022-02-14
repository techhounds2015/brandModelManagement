package com.kashitkalaecom.brandmodelmgmt.rolepermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{
	
	@Query("Select count(1) from Role c where c.id =:id ")
	public int roleIdExists(String id);

	@Query("Select c from Role c where c.id =:id ")
	public Role getRoleById(String id);


}
