package com.kashitkalaecom.brandmodelmgmt.notification;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "smsqueue")
@DiscriminatorValue("E")
public class SMSQueue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	protected String id = UUID.randomUUID().toString();

	@Column(name = "message")
	protected String message;
	
	@Column(name = "phoneNumber")
	protected String phoneNumber;

	@Column(name = "status")
	protected String status;
	
	@Column(name = "event")
	protected String event;
	
	@Column(name = "sentdatetime")
	protected Timestamp sentDateTime;
	
	
	@Column(name = "createdon")
	protected Timestamp createdOn;
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getEvent() {
		return event;
	}


	public void setEvent(String event) {
		this.event = event;
	}


	public Timestamp getSentDateTime() {
		return sentDateTime;
	}


	public void setSentDateTime(Timestamp sentDateTime) {
		this.sentDateTime = sentDateTime;
	}


	public Timestamp getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	

	
	

}
