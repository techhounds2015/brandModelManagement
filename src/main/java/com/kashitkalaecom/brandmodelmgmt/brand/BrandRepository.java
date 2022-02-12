package com.kashitkalaecom.brandmodelmgmt.brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {

	@Query(" from Brand b where b.name =:name ")
	public Brand findByName(String name);

	@Query("select count(*) from Brand b where b.name =:name ")
	public int brandExists(String name);

	@Query("select count(*) from Brand b where b.id =:brandId ")
	public int brandIdExists(String brandId);

}
