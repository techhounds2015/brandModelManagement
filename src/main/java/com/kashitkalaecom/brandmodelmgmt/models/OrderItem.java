package com.kashitkalaecom.brandmodelmgmt.models;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Entity
@Table(name = "orderitem")
public class OrderItem {
	
	
	@Id
    @Column(name="id")
	protected String id = UUID.randomUUID().toString();
		
	@Column(name = "createdby")
	protected String createdBy;

	@Column(name = "createdon")
	protected Timestamp createdOn = CustomClock.timestamp();

	@Column(name = "modifiedby")
	protected String modifiedBy;

	@Column(name = "modifiedon")
	protected Timestamp modifiedOn;
	
	@Column(name = "itemQuantity")
	private int itemQuantity;
	
	@Column(name = "itemName")
	private String itemName;
	
	@Column(name = "itemPrice")
	private String itemPrice;

	@Column(name = "orderid")
	private String orderId;
	
	@Column(name = "productid")
    private String productId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	protected OrderItem() {
	}
	
	public OrderItem(int itemQuantity, String itemName) {
		this.itemQuantity = itemQuantity;
		this.itemName = itemName;
	}

	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	
	public int getItemQuantity() {
		return itemQuantity;
	}

	/**
	 * @param itemQuantity the itemQuantity to set
	 */
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	
}