package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kashitkalaecom.brandmodelmgmt.models.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, String>{

	public Inventory getByProductId(String string);

}
