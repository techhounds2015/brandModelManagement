package com.kashitkalaecom.brandmodelmgmt.models;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Entity
@Table(name="product")
@DiscriminatorValue("E")
public class Product {
	
	@Column(name="categoryid")
	private String categoryId;
	
	@Column(name="brandid")
	private String brandId;
	
	@Column(name="modelid")
	private String modelId;
	private String title;
	
	@Column(name="hsncode")
	private String hsnCode;
	
	@Column(name="vegnonvegstatus")
	private Boolean vegNonvegStatus;
	
	private Integer sizes;
	
	@Column(name="avaliableforsubscription")
	private Boolean avaliableForSubscription;
	
	private String description;
	
	@Column(name="maxqualitypurchased")
	private String maxQualityPurchased;
	
	private Boolean status;
	private String sku;
	private String mrp;
	
	@Column(name="sellingprice")
	private Integer sellingPrice;
	private String cgst;
	private String sgst;
	private String cesss;
	
	@Column(name="outletid")
	private String outletId;
	
	@Column(name="markasfeatured")
	private String markAsFeatured;
	
	@Column(name="markasbestproduct")
	private String markAsBestProduct;
	
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
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public Boolean getVegNonvegStatus() {
		return vegNonvegStatus;
	}
	public void setVegNonvegStatus(Boolean vegNonvegStatus) {
		this.vegNonvegStatus = vegNonvegStatus;
	}
	public Integer getSizes() {
		return sizes;
	}
	public void setSizes(Integer sizes) {
		this.sizes = sizes;
	}
	public Boolean getAvaliableForSubscription() {
		return avaliableForSubscription;
	}
	public void setAvaliableForSubscription(Boolean avaliableForSubscription) {
		this.avaliableForSubscription = avaliableForSubscription;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMaxQualityPurchased() {
		return maxQualityPurchased;
	}
	public void setMaxQualityPurchased(String maxQualityPurchased) {
		this.maxQualityPurchased = maxQualityPurchased;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getMrp() {
		return mrp;
	}
	public void setMrp(String mrp) {
		this.mrp = mrp;
	}
	public Integer getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(Integer sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public String getCgst() {
		return cgst;
	}
	public void setCgst(String cgst) {
		this.cgst = cgst;
	}
	public String getSgst() {
		return sgst;
	}
	public void setSgst(String sgst) {
		this.sgst = sgst;
	}
	public String getCesss() {
		return cesss;
	}
	public void setCesss(String cesss) {
		this.cesss = cesss;
	}
	public String getOutletId() {
		return outletId;
	}
	public void setOutletId(String outletId) {
		this.outletId = outletId;
	}
	public String getMarkAsFeatured() {
		return markAsFeatured;
	}
	public void setMarkAsFeatured(String markAsFeatured) {
		this.markAsFeatured = markAsFeatured;
	}
	public String getMarkAsBestProduct() {
		return markAsBestProduct;
	}
	public void setMarkAsBestProduct(String markAsBestProduct) {
		this.markAsBestProduct = markAsBestProduct;
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
