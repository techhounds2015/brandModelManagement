package com.kashitkalaecom.brandmodelmgmt.models;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Entity
@Table(name = "billing")
public class Billing {
	
	@Id
    @Column(name="id")
	protected String id= UUID.randomUUID().toString();

	@Column(name = "createdon")
	protected Timestamp createdOn = CustomClock.timestamp();

	@Column(name = "modifiedby")
	protected String modifiedBy;

	@Column(name = "modifiedon")
	protected Timestamp modifiedOn;
	
	@Column(name = "billingaddress1")
	private String billingAddress1;
	
	@Column(name = "billingaddress2")
	private String billingAddress2;
	
	@Column(name = "billingcity")
	private String billingCity;
	
	@Column(name = "billingstate")
	private String billingState;
	
	@Column(name = "billingzipcode")
	private int billingZipCode;

	@Column(name = "orderid")
	private String orderId;
	
	@Column(name = "latitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitude;
	
	protected Billing() {
	}
	
	public Billing(String billingAddress1, String billingAddress2, String billingCity, String billingState,
			int billingZipCode) {
		this.billingAddress1 = billingAddress1;
		this.billingAddress2 = billingAddress2;
		this.billingCity = billingCity;
		this.billingState = billingState;
		this.billingZipCode = billingZipCode;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "createdby")
	protected String createdBy;

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
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getBillingAddress1() {
		return billingAddress1;
	}

	public void setBillingAddress1(String billingAddress1) {
		this.billingAddress1 = billingAddress1;
	}

	public String getBillingAddress2() {
		return billingAddress2;
	}

	public void setBillingAddress2(String billingAddress2) {
		this.billingAddress2 = billingAddress2;
	}

	public String getBillingCity() {
		return billingCity;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public String getBillingState() {
		return billingState;
	}

	public void setBillingState(String billingState) {
		this.billingState = billingState;
	}

	public int getBillingZipCode() {
		return billingZipCode;
	}

	public void setBillingZipCode(int billingZipCode) {
		this.billingZipCode = billingZipCode;
	}

	@Override
	public String toString() {
		return "billingAddress1=" + billingAddress1 + ", billingAddress2=" + billingAddress2 + ", billingCity="
				+ billingCity + ", billingState=" + billingState + ", billingZipCode=" + billingZipCode ;
	}
}