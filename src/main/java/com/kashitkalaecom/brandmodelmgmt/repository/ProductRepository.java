package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kashitkalaecom.brandmodelmgmt.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{
	
	@Query("from Product c where c.categoryId =:categoryId ")
	public Product findByName(String categoryId);

	@Query("Select count(1) from Product c where c.id =:id ")
	public int productIdExists(String id);

	@Query("Select count(1) from Product c where c.modelId =:modelId and c.brandId=:brandId and c.categoryId=:categoryId")
	public int titleExists(String modelId, String brandId, String categoryId);

}
