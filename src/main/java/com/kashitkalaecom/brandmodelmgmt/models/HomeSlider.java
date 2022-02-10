package com.kashitkalaecom.brandmodelmgmt.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Entity
@Table(name="homeslider")
@DiscriminatorValue("E")
public class HomeSlider {
	
	@Column(name="maxprice")
	private BigDecimal maxPrice;
	
	@Column(name="minprice")
	private BigDecimal minPrice;
	
	@Column(name="brandid")
	private String brandId;
	
	@Column(name="categoryid")
	private String categoryId;
	
	private String image;
	private Boolean status;
	
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
	
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	public BigDecimal getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
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
