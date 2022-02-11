package com.kashitkalaecom.brandmodelmgmt.service;

import java.util.List;

import com.kashitkalaecom.brandmodelmgmt.models.Order;
import com.kashitkalaecom.brandmodelmgmt.requests.OrderRequest;
import com.kashitkalaecom.brandmodelmgmt.responses.OrderResponse;

public interface OrderService {
	OrderResponse findById(int id);

	Order orderInsert(OrderRequest o);

	void insertAll(List<OrderRequest> o);

	boolean delete(int id);

	boolean update(OrderRequest o, int id);

	boolean updateAll(List<OrderRequest> o, int id);
}
