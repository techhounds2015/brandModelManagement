package com.kashitkalaecom.brandmodelmgmt.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {

	@Query(" from Model m where m.name =:name ")
	public Model findByName(String name);

	@Query("select count(1) from Model m where m.id =:modelId ")
	public int modelIdExists(String modelId);

}
