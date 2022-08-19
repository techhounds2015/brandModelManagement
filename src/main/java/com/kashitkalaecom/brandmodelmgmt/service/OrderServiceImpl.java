package com.kashitkalaecom.brandmodelmgmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.Billing;
import com.kashitkalaecom.brandmodelmgmt.models.Inventory;
import com.kashitkalaecom.brandmodelmgmt.models.Order;
import com.kashitkalaecom.brandmodelmgmt.models.OrderItem;
import com.kashitkalaecom.brandmodelmgmt.repository.BillingOrder;
import com.kashitkalaecom.brandmodelmgmt.repository.InventoryRepository;
import com.kashitkalaecom.brandmodelmgmt.repository.OrderItemRepository;
import com.kashitkalaecom.brandmodelmgmt.repository.OrderRepository;
import com.kashitkalaecom.brandmodelmgmt.requests.OrderRequest;
import com.kashitkalaecom.brandmodelmgmt.responses.OrderResponse;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private OrderItemRepository orderItemRepo;

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private BillingOrder billingOrderRepository;

	@Override
	public OrderResponse findById(String id) {
		log.info("Finding order by Id: {}", id);
		Optional<Order> orderResult = orderRepo.findById(id);
		OrderResponse response = new OrderResponse();
		if (orderResult.isPresent()) {
			Order ordResult = orderResult.get();
			response.setCustomerId(ordResult.getCustomerId());
			// response.setOrderId(ordResult.getId());
			response.setOrderStatus(ordResult.getStatus());
			// response.setTotal(ordResult.getTotal());
			List<OrderItem> orderItemResult = orderItemRepo.findAllById(id);
			if (!orderItemResult.isEmpty()) {
				List<String> itemName = new ArrayList<>();
				List<Integer> quantity = new ArrayList<>();
				for (OrderItem item : orderItemResult) {
					itemName.add(item.getItemName());
					quantity.add(item.getItemQuantity());
				}
				response.setItemName(itemName);
				response.setItemQuantity(quantity);
			}
		} else {
			log.error("Order is empty.");
			return null;
		}
		return response;
	}

	@Override
	public List<OrderSearchResponse> findByCustomerId(Integer id) {
		log.info("Finding order by Id: {}", id);
		List<Order> orderResult = orderRepo.findByCustomerId(id);
		
		if (orderResult != null) {
			List<OrderSearchResponse> request=new ArrayList<>();
			for (Order order : orderResult) {
				OrderSearchResponse response = new OrderSearchResponse();
				response.setOrder(order);
				List<OrderItem> orderItemResult = orderItemRepo.findAllById(order.getId());
				if (!orderItemResult.isEmpty()) {
					response.setOrderItem(orderItemResult);
					
				}
				Billing billing=billingOrderRepository.getByOrderId(order.getId());
				response.setBilling(billing);
				request.add(response);
			}
			return request;
		} else {
			log.error("Order is empty.");
			return null;
		}
	}

	@Override
	public Order orderInsert(String requestorId, OrderRequest req) {
		log.info("Inserting Order : {}", req.toString());

		Order orderObj = new Order();
		orderObj.setCreatedOn(CustomClock.timestamp());
		orderObj.setCreatedBy(requestorId);
		orderObj.setStatus(req.getOrderStatus());
		orderObj.setSubtotal(req.getSubtotal());
		orderObj.setTax(req.getTax());
		orderObj.setTotal(req.getTotal());
		orderObj.setShippingCharge(req.getShippingCharges());
		orderObj.setCustomerId(req.getCustomerId());
		orderObj.setOutletId(req.getOutletId());
		Order orderObj2 = orderRepo.save(orderObj);

		List<OrderItem> orderItem = req.getOrderItem();
		Billing billing = req.getBilling();
		billing.setCreatedBy(requestorId);
		billing.setCreatedOn(CustomClock.timestamp());
		billing.setOrderId(orderObj2.getId());

		billingOrderRepository.save(billing);

		for (OrderItem orderItemObj : orderItem) {
			orderItemObj.setOrderId(orderObj2.getId());
			orderItemObj.setCreatedBy(requestorId);
			orderItemObj.setCreatedOn(CustomClock.timestamp());
			orderItemRepo.save(orderItemObj);

			Inventory inventory = inventoryRepository.getByProductAndutletId(orderItemObj.getProductId(),
					req.getOutletId());
			inventory.setStockavaiable(inventory.getStockavaiable() - orderItemObj.getItemQuantity());
			inventory.setSold(inventory.getSold() + orderItemObj.getItemQuantity());
			inventory.setModifiedOn(CustomClock.timestamp());
			inventory.setModifiedBy(requestorId);
			inventoryRepository.save(inventory);

		}

		return orderObj;

	}

	private int outletIdId(String productId, String outletId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertAll(List<OrderRequest> o) {
		if (o != null) {
			log.info("Inserting Bulk Order");
			for (OrderRequest ord : o) {
				this.orderInsert("",ord);
			}
		}
	}

	@Override
	public boolean delete(String id) {
		if (id == null) {
			log.info("Deleting order by Id: {}", id);
			orderRepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateAll(List<OrderRequest> o, String id) {
		log.info("Updating list of order of size : {}", o.size());
		for (OrderRequest ord : o) {
			this.update(ord, id);
		}
		return true;
	}

	@Override
	public boolean update(OrderRequest o, String id) {

		log.info("Updating order of of id : {}", id);
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

			// Order orderObj = new Order(status, subtotal, tax, total, shipCharge,
			// customerId, orderItem, billing);

			// orderRepo.save(orderObj);
		} else {
			String status = o.getOrderStatus();
			int tax = o.getTax();
			int subtotal = o.getSubtotal();
			int total = o.getTotal();
			int shipCharge = o.getShippingCharges();
			int customerId = o.getCustomerId();

			List<OrderItem> orderItem = o.getOrderItem();
			Billing billing = o.getBilling();

			// Order orderObj = new Order(status, subtotal, tax, total, shipCharge,
			// customerId, orderItem, billing);

			// orderRepo.save(orderObj);
		}
		return true;

	}
}
