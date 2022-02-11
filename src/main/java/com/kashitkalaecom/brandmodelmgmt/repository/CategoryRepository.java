package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kashitkalaecom.brandmodelmgmt.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String>{

	@Query(" from Category m where m.name =:name ")
	Category findByName(String name);

}
