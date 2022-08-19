package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kashitkalaecom.brandmodelmgmt.models.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, String>{

	public Inventory getByProductId(String string);

	@Query("select count(1) from Inventory m where m.id =:inventoryId ")
	public int inventoryIdExists(String inventoryId);

	@Query(" from Inventory m where m.productId =:productId and m.outletId=:outletId ")
	public Inventory getByProductAndutletId(String productId, String outletId);

}
