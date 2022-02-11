package com.kashitkalaecom.brandmodelmgmt.validation;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Entity
@Table(name = "masterdata")
public class MasterData {

	@Id // @GeneratedValue(strategy = GenerationType.AUTO)
	protected String id = UUID.randomUUID().toString();

	@Column(name = "createdby")
	protected String createdBy;

	@Column(name = "createdon")
	protected Timestamp createdOn = CustomClock.timestamp();

	@Column(name = "modifiedby")
	protected String modifiedBy;

	@Column(name = "modifiedon")
	protected Timestamp modifiedOn;

	@Column(name = "masterdatatype")
	private String masterDataType;

	@Column(name = "datavalue")
	private String dataValue;

	@Column(name = "dataname")
	private String dataName;

	@Column(name = "dataname_key")
	private String dataName_Key;

	@Column(name = "displayorder")
	private Integer displayOrder;

	private Boolean editable;
	private String status;

	@Column(name = "datavaluecode")
	private String dataValueCode;

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

	public String getMasterDataType() {
		return masterDataType;
	}

	public void setMasterDataType(String masterDataType) {
		this.masterDataType = masterDataType;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getDataName_Key() {
		return dataName_Key;
	}

	public void setDataName_Key(String dataName_Key) {
		this.dataName_Key = dataName_Key;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDataValueCode() {
		return dataValueCode;
	}

	public void setDataValueCode(String dataValueCode) {
		this.dataValueCode = dataValueCode;
	}

}
