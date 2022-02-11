package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kashitkalaecom.brandmodelmgmt.models.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String>{
	
	@Query("select b.name from brand b where b.name =:name ")
	Brand findByName(String name);

	

}
