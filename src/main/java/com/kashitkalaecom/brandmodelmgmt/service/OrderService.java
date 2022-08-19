package com.kashitkalaecom.brandmodelmgmt.service;

import java.util.List;

import com.kashitkalaecom.brandmodelmgmt.models.Order;
import com.kashitkalaecom.brandmodelmgmt.requests.OrderRequest;
import com.kashitkalaecom.brandmodelmgmt.responses.OrderResponse;

public interface OrderService {
	
	Order orderInsert(String requestorId,OrderRequest o);
	
	void insertAll(List<OrderRequest> o);

	boolean update(OrderRequest o, String id);

	boolean updateAll(List<OrderRequest> o, String id);

	OrderResponse findById(String id);
	
	List<OrderSearchResponse> findByCustomerId(Integer customerId);

	boolean delete(String id);
}
