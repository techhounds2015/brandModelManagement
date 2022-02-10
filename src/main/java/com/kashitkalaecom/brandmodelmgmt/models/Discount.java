package com.kashitkalaecom.brandmodelmgmt.models;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Entity
@Table(name="discount")
@DiscriminatorValue("E")
public class Discount {

	private String code;
	private String title;
	private String discount;
	
	@Column(name="discounttype")
	private String discountType;
	
	@Column(name="maxdiscount")
	private String maxDiscount;
	
	@Column(name="categoryids")
	private String categoryIds;
	
	@Column(name="minpurchase")
	private String minPurchase;
	
	@Column(name="startingdate")
	private Timestamp startingDate;
	
	@Column(name="expirydate")
	private Timestamp expiryDate;
	
	@Id //@GeneratedValue(strategy = GenerationType.AUTO)
	protected String id = UUID.randomUUID().toString();
	@Column(name="createdby")
	protected String createdBy;
	@Column(name="createdon")
	protected Timestamp createdOn =  CustomClock.timestamp();
	@Column(name="modifiedby")
	protected String modifiedBy;
	@Column(name="modifiedon")
	protected Timestamp modifiedOn;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public String getMaxDiscount() {
		return maxDiscount;
	}
	public void setMaxDiscount(String maxDiscount) {
		this.maxDiscount = maxDiscount;
	}
	public String getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}
	public String getMinPurchase() {
		return minPurchase;
	}
	public void setMinPurchase(String minPurchase) {
		this.minPurchase = minPurchase;
	}
	public Timestamp getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(Timestamp startingDate) {
		this.startingDate = startingDate;
	}
	public Timestamp getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Timestamp expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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


	
}
