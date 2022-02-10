package com.kashitkalaecom.brandmodelmgmt.models;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

//@Entity
public class CommonObject {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	protected String id = UUID.randomUUID().toString();

	@Column(name="createdby")
	protected String createdBy;

	@Column(name="createdon")
	protected Timestamp createdOn =  CustomClock.timestamp();

	@Column(name="modifiedby")
	protected String modifiedBy;

	@Column(name="modifiedon")
	protected Timestamp modifiedOn;

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
