package com.kashitkalaecom.brandmodelmgmt.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Entity
@Table(name = "brand")
@DiscriminatorValue("E")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Brand implements Serializable // extends CommonObject
{
	private static final long serialVersionUID = 1L;
	@Column(name = "categoryid")
	private String categoryId;
	private String name;
	private Boolean status;
	private String description;
	private String logo;

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	protected String id = UUID.randomUUID().toString();

	@Column(name = "createdby")
	protected String createdBy;

	@Column(name = "createdon")
	protected Timestamp createdOn = CustomClock.timestamp();

	@Column(name = "modifiedby")
	protected String modifiedBy;

	@Column(name = "modifiedon")
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}
