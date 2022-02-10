package com.kashitkalaecom.brandmodelmgmt.models;
import com.kashitkalaecom.brandmodelmgmt.models.CommonObject;

public class Validation extends CommonObject{

	private String simpleClassName;
	private String fieldNameInternal;
	private String fieldNameKey;
	private Boolean mandatory;
	private String fieldType;
	private int minLength;
	private int maxLength;
	private String charsNotAllowed;
    private boolean numbersAllowed;
    private int lengthBeforeDecimal;
    private int lengthAfterDecimal;
    private boolean pastDateAllowed;
    private String infoKey;
    private String fieldDisplayName;
    private String fieldInfoMessage;
    private String regex;
    private String minValue;
    private String maxValue;
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
}
