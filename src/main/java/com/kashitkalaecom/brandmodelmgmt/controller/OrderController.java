package com.kashitkalaecom.brandmodelmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.businessvalidation.OrderBV;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.requests.OrderRequest;
import com.kashitkalaecom.brandmodelmgmt.responses.OrderResponse;
import com.kashitkalaecom.brandmodelmgmt.service.OrderSearchResponse;
import com.kashitkalaecom.brandmodelmgmt.service.OrderService;


@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	OrderBV orderBV;
 
	@PostMapping("/create")
	public APIResponse<OrderRequest> placeOrder(@RequestHeader String requestorId,  @RequestBody OrderRequest req) {
		APIResponse<OrderRequest> apiResponse = new APIResponse<>();
		try {

			apiResponse = orderBV.bValidateCreate(null, req, null);
			if (Boolean.FALSE.equals(apiResponse.getProcessingSuccess())) {
				return apiResponse;
			}

			// save
			orderService.orderInsert(requestorId,req);
			apiResponse.setResponseCode(StatusCodeEnum.ORDER_PLACED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ORDER_PLACED.getDesc());
			apiResponse.setResponseObject(req);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.ORDER_PLACED_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ORDER_PLACED_FAILED.getDesc());
			System.out.println(e);
		}

		return apiResponse;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponse> getOrder(@PathVariable String id){
		OrderResponse result = orderService.findById(id);
		return new ResponseEntity<OrderResponse>(result,HttpStatus.OK);
	}
	
	@GetMapping("/view/{customerId}")
	public APIResponse< List<OrderSearchResponse>> viewOrderByCustomerId(@PathVariable Integer customerId) {
		APIResponse< List<OrderSearchResponse>> apiResponse = new APIResponse<>();
		try {
			 List<OrderSearchResponse> response=orderService.findByCustomerId(customerId);
			 apiResponse.setResponseObject(response);
			apiResponse.setResponseCode(StatusCodeEnum.ORDER_DETAIL_FETCHED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ORDER_DETAIL_FETCHED.getDesc());
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.ORDER_ITEMS_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ORDER_ITEMS_NOT_EXISTS.getDesc());
			System.out.println(e);
		}

		return apiResponse;
	}
	
	/*
	 * @GetMapping("/viewByCustomerId/{customerId}") public
	 * APIResponse<OrderRequest> getOrderByCustomerId(@PathVariable String
	 * customerId){ APIResponse<OrderRequest> apiResponse = new APIResponse<>();
	 * 
	 * OrderResponse result = orderService.findByCustomerId(customerId); return
	 * apiResponse; }
	 */	
	
	@DeleteMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable String id){
		if (id !=null ) {
			if(orderService.delete(id)) {
                return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
            }
		}	
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/create/bulk")
    public ResponseEntity<Void> addBulkOrder(@RequestBody List<OrderRequest> order){
        if(order != null && !order.isEmpty()) {
            orderService.insertAll(order);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
        	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Void> updateOrder(@RequestBody OrderRequest order, @PathVariable String id){
		if (order != null){
			orderService.update(order, id);
			 return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
        	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
	}
	
	@PutMapping("/update/bulk/{id}")
	public ResponseEntity<Void> bulkUpdateOrder(@RequestBody List<OrderRequest> order, @PathVariable String id){
		if (order != null){
			orderService.updateAll(order, id);
			 return new ResponseEntity<>(HttpStatus.OK);
        } else {
        	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
	}
	
	
}
