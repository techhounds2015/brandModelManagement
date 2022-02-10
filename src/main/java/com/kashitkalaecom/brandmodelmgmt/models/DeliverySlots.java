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
@Table(name="deliveryslots")
@DiscriminatorValue("E")
public class DeliverySlots {

	@Column(name="outletid")
	private String outletId;
	
	@Column(name="starthour")
	private String startHour;
	
	@Column(name="endhour")
	private Timestamp endHour;
	
	@Column(name="maxorders")
	private Timestamp maxOrders;
	
	private String sunday;
	private String monday;
	private String tuesday;
	private String wednesday;
	private String thursday;
	private String friday;
	private String saturday;
	
	@Column(name="alldays")
	private String allDays;
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
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public Timestamp getEndHour() {
		return endHour;
	}
	public void setEndHour(Timestamp endHour) {
		this.endHour = endHour;
	}
	public Timestamp getMaxOrders() {
		return maxOrders;
	}
	public void setMaxOrders(Timestamp maxOrders) {
		this.maxOrders = maxOrders;
	}
	public String getSunday() {
		return sunday;
	}
	public void setSunday(String sunday) {
		this.sunday = sunday;
	}
	public String getMonday() {
		return monday;
	}
	public void setMonday(String monday) {
		this.monday = monday;
	}
	public String getTuesday() {
		return tuesday;
	}
	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}
	public String getWednesday() {
		return wednesday;
	}
	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}
	public String getThursday() {
		return thursday;
	}
	public void setThursday(String thursday) {
		this.thursday = thursday;
	}
	public String getFriday() {
		return friday;
	}
	public void setFriday(String friday) {
		this.friday = friday;
	}
	public String getSaturday() {
		return saturday;
	}
	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}
	public String getAllDays() {
		return allDays;
	}
	public void setAllDays(String allDays) {
		this.allDays = allDays;
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
