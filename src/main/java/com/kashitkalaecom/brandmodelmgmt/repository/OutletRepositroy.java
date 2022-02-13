package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kashitkalaecom.brandmodelmgmt.models.Outlet;

@Repository
public interface OutletRepositroy extends JpaRepository<Outlet, String> {

	@Query(" from Outlet o where o.gstNo =:gstNo ")
	Outlet findByGstNo(String gstNo);

	@Query("select count(*) from Outlet o where o.id =:id ")
	int outletIdExists(String id);

	@Query("select count(*) from Outlet o where o.gstNo =:gstNo ")
	int outletGstExists(String gstNo);

	@Query("select count(*) from Outlet o where o.name =:name ")
	int outletNameExists(String name);

}
