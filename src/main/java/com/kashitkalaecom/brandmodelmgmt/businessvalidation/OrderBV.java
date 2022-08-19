
package com.kashitkalaecom.brandmodelmgmt.businessvalidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Inventory;
import com.kashitkalaecom.brandmodelmgmt.models.OrderItem;
import com.kashitkalaecom.brandmodelmgmt.repository.OrderItemRepository;
import com.kashitkalaecom.brandmodelmgmt.requests.OrderRequest;
import com.kashitkalaecom.brandmodelmgmt.service.InventoryService;
import com.kashitkalaecom.brandmodelmgmt.service.OrderService;
import com.kashitkalaecom.brandmodelmgmt.service.OutletService;
import com.kashitkalaecom.brandmodelmgmt.service.ProductService;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Component
public class OrderBV {

	@Autowired
	ProductService productService;

	@Autowired
	OrderService orderService;

	@Autowired
	private OrderItemRepository orderItemRepo;

	private static final Logger logger = LoggerFactory.getLogger(InventoryBV.class);

	public APIResponse<OrderRequest> bValidateCreate(String tenantCode, OrderRequest req, String locale) {

		APIResponse<OrderRequest> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
		try {
			apiResponse = validateCreate(tenantCode, req, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}

		return apiResponse;
	}

	private APIResponse<OrderRequest> validateCreate(String tenantCode, OrderRequest req, String locale) {

		APIResponse<OrderRequest> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);

		// items exist or not

		return apiResponse;
	}
}
