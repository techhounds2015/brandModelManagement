package com.kashitkalaecom.brandmodelmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.businessvalidation.InventoryBV;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Inventory;
import com.kashitkalaecom.brandmodelmgmt.service.InventoryService;
import com.kashitkalaecom.brandmodelmgmt.service.ProductService;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

	@Autowired
	InventoryService inventoryService;

	@Autowired
	ProductService productService;

	@Autowired
	InventoryBV inventoryBV;

	@PostMapping("/create")
	public APIResponse<Inventory> createInventory(@RequestHeader String requestorId, @RequestBody Inventory inventory) {
		APIResponse<Inventory> apiResponse = new APIResponse<>();

		try {

			apiResponse = inventoryBV.bValidateCreate(null, inventory, null);
			if (Boolean.FALSE.equals(apiResponse.getProcessingSuccess())) {
				return apiResponse;
			}

			// Save
			inventory = inventoryService.save(inventory, requestorId);

			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_CREATED.getDesc());
			apiResponse.setResponseObject(inventory);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_CREATION_FAILED.getDesc());
			apiResponse.setResponseObject(inventory);
		}
		return apiResponse;
	}

	@GetMapping("/view/{inventoryId}")
	public APIResponse<Inventory> viewInventory(@RequestHeader String requestorId,
			@PathVariable("inventoryId") String inventoryId) {

		APIResponse<Inventory> apiResponse = new APIResponse<>();

		int inventoryCount = inventoryService.inventoryIdExists(inventoryId);

		if (inventoryCount == 0) {
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_NOT_EXISTS.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		Inventory inventory = inventoryService.getInventoryById(inventoryId);

		apiResponse.setResponseMessage("Success");
		apiResponse.setResponseObject(inventory);
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse<Inventory> updateInventory(@RequestHeader String requestorId, @RequestBody Inventory inventory) {
		APIResponse<Inventory> apiResponse = new APIResponse<>();
		try {

			apiResponse = inventoryBV.bValidateCreate(null, inventory, null);
			if (Boolean.FALSE.equals(apiResponse.getProcessingSuccess())) {
				return apiResponse;
			}

			inventory = inventoryService.update(inventory, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_UPDATED.getDesc());
			apiResponse.setResponseObject(inventory);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_UPDATION_FAILED.getDesc());
		}
		return apiResponse;
	}

	@PutMapping("/update/mrp/{inventoryId}/{mrp}")
	public APIResponse<Inventory> updateInventoryMRP(@RequestHeader String requestorId,
			@PathVariable("inventoryId") String inventoryId, @PathVariable("mrp") Double mrp) {
		APIResponse<Inventory> apiResponse = new APIResponse<>();
		try {

			apiResponse = inventoryBV.bValidateCreate(null, inventoryId, mrp, null);
			if (Boolean.FALSE.equals(apiResponse.getProcessingSuccess())) {
				return apiResponse;
			}

			Inventory inventory = inventoryService.updateMRP(requestorId, inventoryId, mrp);
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_MRP_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_MRP_UPDATED.getDesc());
			apiResponse.setResponseObject(inventory);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_MRP_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_MRP_UPDATION_FAILED.getDesc());
		}
		return apiResponse;
	}

	@PutMapping("/update/stocksAvaiabale/{inventoryId}/{stocksAvaiabale}")
	public APIResponse<Inventory> updateInventoryStocksAvaiabale(@RequestHeader String requestorId,
			@PathVariable("inventoryId") String inventoryId, @PathVariable("stocksAvaiabale") Long stocksAvaiabale) {
		APIResponse<Inventory> apiResponse = new APIResponse<>();
		try {
			Inventory inventory = inventoryService.updateStocksAvaiabale(requestorId, inventoryId, stocksAvaiabale);
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_STOCKS_AVAIALBLE_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_STOCKS_AVAIALBLE_UPDATED.getDesc());
			apiResponse.setResponseObject(inventory);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_STOCKS_AVAIALBLE_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_STOCKS_AVAIALBLE_UPDATION_FAILED.getDesc());
		}
		return apiResponse;
	}

	@PutMapping("/update/sellingPrice/{inventoryId}/{sellingPrice}")
	public APIResponse<Inventory> updateInventorySellingPrice(@RequestHeader String requestorId,
			@PathVariable("inventoryId") String inventoryId, @PathVariable("sellingPrice") Double sellingPrice) {
		APIResponse<Inventory> apiResponse = new APIResponse<>();
		try {

			apiResponse = inventoryBV.bValidateCreateSP(null, inventoryId, sellingPrice, null);
			if (Boolean.FALSE.equals(apiResponse.getProcessingSuccess())) {
				return apiResponse;
			}

			Inventory inventory = inventoryService.updateSellingPrice(requestorId, inventoryId, sellingPrice);
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_SELLING_PRICE_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_SELLING_PRICE_UPDATED.getDesc());
			apiResponse.setResponseObject(inventory);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_SELLING_PRICE_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_SELLING_PRICE_UPDATION_FAILED.getDesc());
		}
		return apiResponse;
		
	}

	@PostMapping("/bulk/fileUpload")
	public APIResponse<Inventory> updateInventoryByFile(@RequestHeader String requestorId,
			@RequestPart MultipartFile file) {
		APIResponse<Inventory> apiResponse = new APIResponse<>();

		try {

			// Save
			inventoryService.saveInventoryFile(file, requestorId);

			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_CREATED.getDesc());
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.INVENTORY_CREATION_FAILED.getDesc());
		}
		return apiResponse;

	}

}
