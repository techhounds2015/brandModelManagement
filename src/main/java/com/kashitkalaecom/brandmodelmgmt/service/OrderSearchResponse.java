package com.kashitkalaecom.brandmodelmgmt.service;

import java.util.List;

import com.kashitkalaecom.brandmodelmgmt.models.Billing;
import com.kashitkalaecom.brandmodelmgmt.models.Order;
import com.kashitkalaecom.brandmodelmgmt.models.OrderItem;

public class OrderSearchResponse {

	private Order order;

	private List<OrderItem> orderItem;
	
	private Billing billing;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

	public Billing getBilling() {
		return billing;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}

}
