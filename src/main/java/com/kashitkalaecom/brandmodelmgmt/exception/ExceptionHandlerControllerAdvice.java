package com.kashitkalaecom.brandmodelmgmt.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
	

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    APIResponse handleException(final Exception exception, final HttpServletRequest request, WebRequest webRequest) {

    	APIResponse commonResponse = new APIResponse();

        commonResponse.setResponseCode("1010");
        commonResponse.setResponseMessage("Invalid Request");
        Map<String, String> errors = new HashMap<String,String>();
        	
        try {
        	
        	JsonMappingException jsobExp = (JsonMappingException) exception.getCause();
        	
        	int size = jsobExp.getPath().size();
        	
        	String invalidField = jsobExp.getPath().get(size-1).getFieldName();

        	
        if (exception.getCause().getMessage().contains("not a valid Integer value") 
        		|| exception.getCause().getMessage().contains("Unexpected character") ) {
        	errors.put(invalidField, "Special Characters and Alphabet are not Allowed");
        	commonResponse.setErrors(errors);
        }
        } catch (Exception e) {
        	logger.error("Exception in handling exception-"+e.getMessage());
        }

        return commonResponse;
    }
}
