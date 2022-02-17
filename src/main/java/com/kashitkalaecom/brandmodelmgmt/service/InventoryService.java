package com.kashitkalaecom.brandmodelmgmt.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.csv.CSVFormat;
import com.kashitkalaecom.brandmodelmgmt.models.Inventory;
import com.kashitkalaecom.brandmodelmgmt.repository.InventoryRepository;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;

	public Inventory save(Inventory inventory, String requestorId) {
		inventory.setCreatedBy(requestorId);
		return inventoryRepository.save(inventory);
	}

	public Inventory getInventoryById(String inventoryId) {
		return inventoryRepository.getById(inventoryId);
	}

	public Inventory update(Inventory inventory, String requestorId) {
		inventory.setModifiedOn(CustomClock.timestamp());
		inventory.setModifiedBy(requestorId);
		return inventoryRepository.save(inventory);
	}

	public void saveInventoryFile(MultipartFile file, String requestorId) throws Exception {

		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			for (CSVRecord csvRecord : csvRecords) {
				Inventory inventory = inventoryRepository.getByProductId(csvRecord.get("productId"));
				inventory.setBatchNumber(csvRecord.get("batchNumber"));
				inventory.setCess(Float.parseFloat(csvRecord.get("cess")));
				inventory.setCgst(Float.parseFloat(csvRecord.get("cgst")));
				inventory.setSgst(Float.parseFloat(csvRecord.get("sgst")));
				inventory.setMrp(Double.parseDouble(csvRecord.get("mrp")));
				inventory.setOutletId(csvRecord.get("outletId"));
				inventory.setPoNumber(csvRecord.get("poNumbe"));
				inventory.setProductId(csvRecord.get("productId"));
				inventory.setSellingPrice(Double.parseDouble(csvRecord.get("sellingPrice")));
				inventory.setSku(csvRecord.get("sku"));
				inventory.setSold(Long.parseLong(csvRecord.get("sold")));
				inventory.setStatus(true);
				inventory.setStockavaiable(Long.parseLong(csvRecord.get("stockavaiable")));
				inventory.setVendorId(csvRecord.get("vendorId"));
				inventory.setWarehouseRackNumber(csvRecord.get("warehouseRackNumber"));
				inventory.setBuyingPrice(Double.parseDouble(csvRecord.get("buyingPrice")));
				inventory.setMfgDate(CustomClock.stringToTS(csvRecord.get("mfgDate"), "yyyy-MM-dd HH:mm:ss.SSS"));
				inventory.setExpDate(CustomClock.stringToTS(csvRecord.get("expDate"), "yyyy-MM-dd HH:mm:ss.SSS"));
				inventoryRepository.save(inventory);
			}

		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

	public Inventory updateMRP(String requestorId, String inventoryId, Double mrp) {
		Inventory inventory = inventoryRepository.getById(inventoryId);
		inventory.setModifiedOn(CustomClock.timestamp());
		inventory.setModifiedBy(requestorId);
		inventory.setMrp(mrp);
		return inventoryRepository.save(inventory);
	}

	public Inventory updateSellingPrice(String requestorId, String inventoryId, Double sellingPrice) {
		Inventory inventory = inventoryRepository.getById(inventoryId);
		inventory.setModifiedOn(CustomClock.timestamp());
		inventory.setModifiedBy(requestorId);
		inventory.setSellingPrice(sellingPrice);
		return inventoryRepository.save(inventory);
	}

	public Inventory updateStocksAvaiabale(String requestorId, String inventoryId, Long stocksAvaiabale) {
		Inventory inventory = inventoryRepository.getById(inventoryId);
		inventory.setModifiedOn(CustomClock.timestamp());
		inventory.setModifiedBy(requestorId);
		inventory.setStockavaiable(stocksAvaiabale);
		return inventoryRepository.save(inventory);
	}

	public int inventoryIdExists(String inventoryId) {
		return inventoryRepository.inventoryIdExists(inventoryId);
	}

}
