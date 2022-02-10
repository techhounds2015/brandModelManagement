package com.kashitkalaecom.brandmodelmgmt.validation;


import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kashitkalaecom.brandmodelmgmt.models.CommonObject;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Entity
@Table(name="validation")
public class Validation {
	
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

	@Column(name = "simpleclassname")
	private String simpleClassName;

	@Column(name = "fieldnameinternal")
	private String fieldNameInternal;

	@Column(name = "fieldnamekey")
	private String fieldNameKey;
	private Boolean mandatory;

	@Column(name = "fieldtype")
	private String fieldType;

	@Column(name = "minlength")
	private int minLength;

	@Column(name = "maxlength")
	private int maxLength;

	@Column(name = "charsnotallowed")
	private String charsNotAllowed;

	@Column(name = "numbersallowed")
	private boolean numbersAllowed;

	@Column(name = "lengthbeforedecimal")
	private int lengthBeforeDecimal;

	@Column(name = "lengthafterdecimal")
	private int lengthAfterDecimal;

	@Column(name = "pastdateallowed")
	private boolean pastDateAllowed;

	@Column(name = "infokey")
	private String infoKey;

	@Column(name = "fielddisplayname")
	private String fieldDisplayName;

	@Column(name = "fieldinfomessage")
	private String fieldInfoMessage;
	private String regex;

	@Column(name = "minvalue")
	private String minValue;

	@Column(name = "maxvalue")
	private String maxValue;

	@Column(name = "mobilefield")
	private boolean mobileField;
	private boolean disable;

	public String getSimpleClassName() {
		return simpleClassName;
	}

	public void setSimpleClassName(String simpleClassName) {
		this.simpleClassName = simpleClassName;
	}

	public String getFieldNameInternal() {
		return fieldNameInternal;
	}

	public void setFieldNameInternal(String fieldNameInternal) {
		this.fieldNameInternal = fieldNameInternal;
	}

	public String getFieldNameKey() {
		return fieldNameKey;
	}

	public void setFieldNameKey(String fieldNameKey) {
		this.fieldNameKey = fieldNameKey;
	}

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public int getMinLength() {
		return minLength;
	}

	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public String getCharsNotAllowed() {
		return charsNotAllowed;
	}

	public void setCharsNotAllowed(String charsNotAllowed) {
		this.charsNotAllowed = charsNotAllowed;
	}

	public boolean isNumbersAllowed() {
		return numbersAllowed;
	}

	public void setNumbersAllowed(boolean numbersAllowed) {
		this.numbersAllowed = numbersAllowed;
	}

	public int getLengthBeforeDecimal() {
		return lengthBeforeDecimal;
	}

	public void setLengthBeforeDecimal(int lengthBeforeDecimal) {
		this.lengthBeforeDecimal = lengthBeforeDecimal;
	}

	public int getLengthAfterDecimal() {
		return lengthAfterDecimal;
	}

	public void setLengthAfterDecimal(int lengthAfterDecimal) {
		this.lengthAfterDecimal = lengthAfterDecimal;
	}

	public boolean isPastDateAllowed() {
		return pastDateAllowed;
	}

	public void setPastDateAllowed(boolean pastDateAllowed) {
		this.pastDateAllowed = pastDateAllowed;
	}

	public String getInfoKey() {
		return infoKey;
	}

	public void setInfoKey(String infoKey) {
		this.infoKey = infoKey;
	}

	public String getFieldDisplayName() {
		return fieldDisplayName;
	}

	public void setFieldDisplayName(String fieldDisplayName) {
		this.fieldDisplayName = fieldDisplayName;
	}

	public String getFieldInfoMessage() {
		return fieldInfoMessage;
	}

	public void setFieldInfoMessage(String fieldInfoMessage) {
		this.fieldInfoMessage = fieldInfoMessage;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public boolean isMobileField() {
		return mobileField;
	}

	public void setMobileField(boolean mobileField) {
		this.mobileField = mobileField;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
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
