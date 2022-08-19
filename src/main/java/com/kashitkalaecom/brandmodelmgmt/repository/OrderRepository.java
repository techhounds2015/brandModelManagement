package com.kashitkalaecom.brandmodelmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kashitkalaecom.brandmodelmgmt.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

	@Query(" from Order r where r.id =:id ")
	Order findByName(String id);

	@Query(" from Order r where r.customerId =:customerId ")
	List<Order> findByCustomerId(Integer customerId);

	

}
