package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kashitkalaecom.brandmodelmgmt.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{
	
	@Query("from Product c where c.categoryId =:categoryId ")
	public Product findByName(String categoryId);

}
