package com.kashitkalaecom.brandmodelmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kashitkalaecom.brandmodelmgmt.models.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
	
	@Query("FROM OrderItem WHERE orderId =:orderId")
	List<OrderItem> findAllById(String orderId);

}
