package com.kashitkalaecom.brandmodelmgmt.businessvalidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Inventory;
import com.kashitkalaecom.brandmodelmgmt.models.Product;
import com.kashitkalaecom.brandmodelmgmt.service.InventoryService;
import com.kashitkalaecom.brandmodelmgmt.service.OutletService;
import com.kashitkalaecom.brandmodelmgmt.service.ProductService;

@Component
public class InventoryBV {

	@Autowired
	ProductService productService;
	
	@Autowired
	InventoryService inventoryService;

	@Autowired
	OutletService outletService;

	private static final Logger logger = LoggerFactory.getLogger(InventoryBV.class);

	public APIResponse<Inventory> bValidateCreate(String tenantCode, Inventory inventory, String locale) {

		APIResponse<Inventory> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
		try {
			apiResponse = validateCreate(tenantCode, inventory, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}

		return apiResponse;
	}

	private APIResponse<Inventory> validateCreate(String tenantCode, Inventory inventory, String locale) {

		APIResponse<Inventory> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);

		// Selling Price can never be greater then MRP

		if (inventory.getSellingPrice() > inventory.getMrp()) {
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_SELLEING_PRICE_GT_MRP.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_SELLEING_PRICE_GT_MRP.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		if (inventory.getSellingPrice() <= 0) {
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_SELLING_PRICE_NEGATIVE.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_SELLING_PRICE_NEGATIVE.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		if (inventory.getMrp() <= 0) {
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_MRP_NEGATIVE.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_MRP_NEGATIVE.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		// Product exists
		int productCount = productService.productIdExists(inventory.getProductId());

		if (productCount == 0) {
			apiResponse.setResponseCode(StatusCodeEnum.PRODUCT_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.PRODUCT_NOT_EXISTS.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		// Outlet Exists

		int outletCount = outletService.outletIdExists(inventory.getOutletId());

		if (outletCount == 0) {
			apiResponse.setResponseCode(StatusCodeEnum.OUTLET_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.OUTLET_NOT_EXISTS.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		return apiResponse;
	}

	public APIResponse<Inventory> bValidateCreate(String tenantCode, String inventoryId, Double mrp, String locale) {

		APIResponse<Inventory> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
		try {
			apiResponse = validateCreate(tenantCode, inventoryId, mrp, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}

		return apiResponse;
	}

	private APIResponse<Inventory> validateCreate(String tenantCode, String inventoryId, Double mrp, String locale) {

		APIResponse<Inventory> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
		


		if (mrp <= 0) {
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_MRP_NEGATIVE.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_MRP_NEGATIVE.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		// Inventory exists
		int inventoryCount = inventoryService.inventoryIdExists(inventoryId);

		if (inventoryCount == 0) {
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_NOT_EXISTS.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		// MRP can never be less then selling price
		
		Inventory inventory =  inventoryService.getInventoryById(inventoryId);

		if (inventory.getSellingPrice() > mrp) {
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_SELLEING_PRICE_GT_MRP.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_SELLEING_PRICE_GT_MRP.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		

		return apiResponse;

	}

	public APIResponse<Inventory> bValidateCreateSP(String tenantCode, String inventoryId, Double sp, String locale) {

		APIResponse<Inventory> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
		try {
			apiResponse = validateCreateSP(tenantCode, inventoryId, sp, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}

		return apiResponse;
	}
	
	private APIResponse<Inventory> validateCreateSP(String tenantCode, String inventoryId, Double sp, String locale) {

		APIResponse<Inventory> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
		


		if (sp <= 0) {
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_SELLING_PRICE_NEGATIVE.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_SELLING_PRICE_NEGATIVE.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		// Inventory exists
		int inventoryCount = inventoryService.inventoryIdExists(inventoryId);

		if (inventoryCount == 0) {
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_NOT_EXISTS.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		// MRP can never be less then selling price
		
		Inventory inventory =  inventoryService.getInventoryById(inventoryId);

		if (sp > inventory.getMrp()) {
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_SELLEING_PRICE_GT_MRP.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_SELLEING_PRICE_GT_MRP.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		

		return apiResponse;

	}
}
