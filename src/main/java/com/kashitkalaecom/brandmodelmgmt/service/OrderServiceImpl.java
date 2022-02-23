package com.kashitkalaecom.brandmodelmgmt.service;

import java.security.Signature;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.Billing;
import com.kashitkalaecom.brandmodelmgmt.models.Order;
import com.kashitkalaecom.brandmodelmgmt.models.OrderItem;
import com.kashitkalaecom.brandmodelmgmt.repository.OrderItemRepository;
import com.kashitkalaecom.brandmodelmgmt.repository.OrderRepository;
import com.kashitkalaecom.brandmodelmgmt.requests.OrderRequest;
import com.kashitkalaecom.brandmodelmgmt.responses.OrderResponse;

@Service
public class OrderServiceImpl implements OrderService{
	
	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private OrderItemRepository orderItemRepo;
	
	@Override
	public OrderResponse findById(int id){
		log.info("Finding order by Id: {}",id);
		Optional<Order> orderResult = orderRepo.findById(id);
		OrderResponse response = new OrderResponse();
		if (orderResult.isPresent()) {
			Order ordResult = orderResult.get();
			response.setCustomerId(ordResult.getCustomerId());
			//response.setOrderId(ordResult.getId());
			response.setOrderStatus(ordResult.getStatus());
			//response.setTotal(ordResult.getTotal());
			List<OrderItem> orderItemResult = orderItemRepo.findAllById(id);
			if (!orderItemResult.isEmpty()) {
				List<String> itemName = new ArrayList<>();
				List<Integer> quantity = new ArrayList<>();
				for(OrderItem item : orderItemResult) {
					itemName.add(item.getItemName());
					quantity.add(item.getItemQuantity());
				}
				response.setItemName(itemName);
				response.setItemQuantity(quantity);
			}
		}else {
			log.error("Order is empty.");
            return null;
		}
		return response;
	}

	@Override
	public Order orderInsert(OrderRequest req){
//		if (req.getCustomerId() == 0 && req.getBilling() == null && req.getOrderItem() == null
//		&& req.getTotal() == 0){
			log.info("Inserting Order : {}", req.toString());
			String status = req.getOrderStatus();
			int tax = req.getTax();
			int subtotal = req.getSubtotal();
			int total = req.getTotal();
			int shipCharge = req.getShippingCharges();
			int customerId = req.getCustomerId();
			
			List<OrderItem> orderItem = req.getOrderItem();
			Billing billing = req.getBilling();
			
			//Order orderObj = new Order(status, subtotal, tax, total, shipCharge, customerId, orderItem, billing);
			
			//orderRepo.save(orderObj);
			
			//return orderObj;
			return null;

	}

	@Override
	public void insertAll(List<OrderRequest> o){
		if (o != null) {
			log.info("Inserting Bulk Order");
			for (OrderRequest ord: o) {
				this.orderInsert(ord);
			}
		}
	}

	@Override
	public boolean delete(int id) {
		if(id != 0) {
			log.info("Deleting order by Id: {}",id);
			orderRepo.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean updateAll(List<OrderRequest> o, int id) {
		log.info("Updating list of order of size : {}",o.size());
		for (OrderRequest ord: o) {
			this.update(ord, id);
			}
			return true;
	}

	@Override
	public boolean update(OrderRequest o, int id) {

			log.info("Updating order of of id : {}",id);
			if (orderRepo.existsById(id)) {
				
				orderRepo.deleteById(id);
				
				String status = o.getOrderStatus();
				int tax = o.getTax();
				int subtotal = o.getSubtotal();
				int total = o.getTotal();
				int shipCharge = o.getShippingCharges();
				int customerId = o.getCustomerId();
				
				List<OrderItem> orderItem = o.getOrderItem();
				Billing billing = o.getBilling();
				
				//Order orderObj = new Order(status, subtotal, tax, total, shipCharge, customerId, orderItem, billing);
				
				//orderRepo.save(orderObj);				
			}
			else {
				String status = o.getOrderStatus();
				int tax = o.getTax();
				int subtotal = o.getSubtotal();
				int total = o.getTotal();
				int shipCharge = o.getShippingCharges();
				int customerId = o.getCustomerId();
				
				List<OrderItem> orderItem = o.getOrderItem();
				Billing billing = o.getBilling();
				
				//Order orderObj = new Order(status, subtotal, tax, total, shipCharge, customerId, orderItem, billing);
				
				//orderRepo.save(orderObj);
			}
			return true;

	}
	
	

}
