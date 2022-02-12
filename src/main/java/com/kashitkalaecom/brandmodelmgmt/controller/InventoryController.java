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
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Inventory;
import com.kashitkalaecom.brandmodelmgmt.service.InventoryService;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
	
	@Autowired 
	InventoryService inventoryService;
	

	@PostMapping("/create")
	public APIResponse<Inventory> createBrand(@RequestHeader String requestorId, @RequestBody Inventory inventory) {
		APIResponse<Inventory> apiResponse = new APIResponse<Inventory>();

		try {

			// Field Validation
			/*
			 * apiResponse = brandFV.fValidateCreate(null, brand, null); if
			 * (!apiResponse.getValidationSuccess()) { return apiResponse; }
			 */
			// Business Validation

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
	public APIResponse brand(@RequestHeader String requestorId, @PathVariable("inventoryId") String inventoryId) {

		APIResponse apiResponse = new APIResponse();
		Inventory inventory = inventoryService.getInventoryById(inventoryId);

		if (inventory == null) {
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_NOT_EXISTS.getCode());
			apiResponse.setResponseCode(StatusCodeEnum.INVENTORY_NOT_EXISTS.getDesc());
			apiResponse.setResponseObject(inventory);
			return apiResponse;
		}
		apiResponse.setResponseMessage("Success");
		apiResponse.setResponseObject(inventory);
		return apiResponse;
	}

	
	@PutMapping("/update")
	public APIResponse<Inventory> updateInventory(@RequestHeader String requestorId, @RequestBody Inventory inventory) {
		APIResponse<Inventory> apiResponse = new APIResponse<Inventory>();
		try {
			inventory = inventoryService.update(inventory, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.BRAND_UPDATED.getCode());
			apiResponse.setResponseObject(inventory);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.BRAND_UPDATION_FAILED.getCode());
		}
		return apiResponse;
	}

	
	@PutMapping("/update/mrp/{inventoryId}/{mrp}")
	public APIResponse<Inventory> updateInventoryMRP(@RequestHeader String requestorId, @PathVariable("inventoryId") String inventoryId,@PathVariable("mrp") Double mrp) {
		APIResponse<Inventory> apiResponse = new APIResponse<Inventory>();
		try {
			Inventory inventory = inventoryService.updateMRP(requestorId, inventoryId,mrp);
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.BRAND_UPDATED.getCode());
			apiResponse.setResponseObject(inventory);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.BRAND_UPDATION_FAILED.getCode());
		}
		return apiResponse;
	}


	@PutMapping("/update/mrp/{inventoryId}/{stocksAvaiabale}")
	public APIResponse<Inventory> updateInventoryStocksAvaiabale(@RequestHeader String requestorId, @PathVariable("inventoryId") String inventoryId,@PathVariable("stocksAvaiabale") Long stocksAvaiabale) {
		APIResponse<Inventory> apiResponse = new APIResponse<Inventory>();
		try {
			Inventory inventory = inventoryService.updateStocksAvaiabale(requestorId, inventoryId,stocksAvaiabale);
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.BRAND_UPDATED.getCode());
			apiResponse.setResponseObject(inventory);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.BRAND_UPDATION_FAILED.getCode());
		}
		return apiResponse;
	}

	@PutMapping("/update/mrp/{inventoryId}/{sellingPrice}")
	public APIResponse<Inventory> updateInventorySellingPrice(@RequestHeader String requestorId, @PathVariable("inventoryId") String inventoryId,@PathVariable("sellingPrice") Double sellingPrice) {
		APIResponse<Inventory> apiResponse = new APIResponse<Inventory>();
		try {
			Inventory inventory = inventoryService.updateSellingPrice(requestorId, inventoryId,sellingPrice);
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.BRAND_UPDATED.getCode());
			apiResponse.setResponseObject(inventory);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.BRAND_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.BRAND_UPDATION_FAILED.getCode());
		}
		return apiResponse;
	}

	
	@PostMapping("/bulk/fileUpload")
	public APIResponse<Inventory> updateInventoryByFile(@RequestHeader String requestorId, @RequestPart MultipartFile file) {
		APIResponse<Inventory> apiResponse = new APIResponse<Inventory>();

		try {

			// Field Validation
			/*
			 * apiResponse = brandFV.fValidateCreate(null, brand, null); if
			 * (!apiResponse.getValidationSuccess()) { return apiResponse; }
			 */
			// Business Validation

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
