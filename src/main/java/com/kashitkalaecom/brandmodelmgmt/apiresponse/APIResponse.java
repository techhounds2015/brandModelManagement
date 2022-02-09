package com.kashitkalaecom.brandmodelmgmt.apiresponse;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class APIResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private String responseCode = "200";
	private String responseMessage = "";
	private T responseObject;

	private Map<String, String> errors;



	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	

	public T getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(T responseObject) {
		this.responseObject = responseObject;
	}

	
	@Override
	public String toString() {
		return "APIResponse [responseCode=" + responseCode + ", responseMessage=" + responseMessage
				+ ", responseObject=" + responseObject + "";
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

}
