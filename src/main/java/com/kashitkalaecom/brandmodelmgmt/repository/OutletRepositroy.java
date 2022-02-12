package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kashitkalaecom.brandmodelmgmt.models.Outlet;

@Repository
public interface OutletRepositroy extends JpaRepository<Outlet, String> {

	@Query(" from Outlet o where o.gstNo =:gstNo ")
	Outlet findByName(String gstNo);

}
