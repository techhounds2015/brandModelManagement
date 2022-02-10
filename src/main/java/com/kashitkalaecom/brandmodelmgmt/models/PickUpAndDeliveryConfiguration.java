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
@Table(name="pickupanddeliveryconfiguration")
@DiscriminatorValue("E")
public class PickUpAndDeliveryConfiguration {
	
	@Column(name="outletid")
	private String outletId;
	private String distance;
	
	@Column(name="dileverycharges")
	private String dileveryCharges;
	
	@Column(name="extraperKm")
	private String extraPerKm;
	
	@Column(name="minorderamountfornodeliverycharges")
	private String minOrderAmountForNoDeliveryCharges;
	
	@Column(name="deliverychargesforbeloworderamount")
	private String deliveryChargesForBelowOrderAmount;
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

	public String getOutletId() {
		return outletId;
	}
	public void setOutletId(String outletId) {
		this.outletId = outletId;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getDileveryCharges() {
		return dileveryCharges;
	}
	public void setDileveryCharges(String dileveryCharges) {
		this.dileveryCharges = dileveryCharges;
	}
	public String getExtraPerKm() {
		return extraPerKm;
	}
	public void setExtraPerKm(String extraPerKm) {
		this.extraPerKm = extraPerKm;
	}
	public String getMinOrderAmountForNoDeliveryCharges() {
		return minOrderAmountForNoDeliveryCharges;
	}
	public void setMinOrderAmountForNoDeliveryCharges(String minOrderAmountForNoDeliveryCharges) {
		this.minOrderAmountForNoDeliveryCharges = minOrderAmountForNoDeliveryCharges;
	}
	public String getDeliveryChargesForBelowOrderAmount() {
		return deliveryChargesForBelowOrderAmount;
	}
	public void setDeliveryChargesForBelowOrderAmount(String deliveryChargesForBelowOrderAmount) {
		this.deliveryChargesForBelowOrderAmount = deliveryChargesForBelowOrderAmount;
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
