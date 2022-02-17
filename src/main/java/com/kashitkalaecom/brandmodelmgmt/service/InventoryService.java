package com.kashitkalaecom.brandmodelmgmt.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kashitkalaecom.brandmodelmgmt.models.Inventory;
import com.kashitkalaecom.brandmodelmgmt.repository.InventoryRepository;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;
	
	@Autowired
	Inventory inventory;

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
				//Inventory inventory = inventoryRepository.getByProductId(csvRecord.get(0));
				inventory.setBatchNumber(csvRecord.get(12)); //batchNumber
				inventory.setCess(Float.parseFloat(csvRecord.get(9))); //cess
				inventory.setCgst(Float.parseFloat(csvRecord.get(8))); //cgst
				inventory.setSgst(csvRecord.get(7).equals("") ? Float.parseFloat("0.0") : Float.parseFloat(csvRecord.get(7))); //sgst
				inventory.setMrp(Double.parseDouble(csvRecord.get(3))); // MRP
				inventory.setOutletId(csvRecord.get(1)); // OutletId
				inventory.setPoNumber(csvRecord.get(14)); //poNumber
				inventory.setProductId(csvRecord.get(0)); // ProductId
				inventory.setSellingPrice(Double.parseDouble(csvRecord.get(4))); //sellingPrice
				inventory.setSku(csvRecord.get(2)); // SKU
				inventory.setSold(csvRecord.get(15).equals("") ? Long.parseLong("0") : Long.parseLong(csvRecord.get(15))); //sold
				inventory.setStatus(true);
				inventory.setStockavaiable(Long.parseLong(csvRecord.get(5))); //stockavaiable
				inventory.setVendorId(csvRecord.get(13)); //vendorId
				inventory.setWarehouseRackNumber(csvRecord.get(13)); //warehouseRackNumber
				inventory.setBuyingPrice(Double.parseDouble(csvRecord.get(6))); //buyingPrice
				//inventory.setMfgDate(CustomClock.stringToTS(csvRecord.get(10), "yyyy-MM-dd HH:mm:ss.SSS")); //mfgDate
				//inventory.setExpDate(CustomClock.stringToTS(csvRecord.get(11), "yyyy-MM-dd HH:mm:ss.SSS")); //expDate
				inventoryRepository.save(inventory);
			}

		} catch (IOException e) {
			//throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
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
