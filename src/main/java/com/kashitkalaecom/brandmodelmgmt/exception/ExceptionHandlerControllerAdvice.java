package com.kashitkalaecom.brandmodelmgmt.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
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
	public @ResponseBody APIResponse handleException(final Exception exception, final HttpServletRequest request,
			WebRequest webRequest) {

		APIResponse commonResponse = new APIResponse();

		commonResponse.setResponseCode("1010");
		commonResponse.setResponseMessage("Invalid Request");
		Map<String, String> errors = new HashMap<String, String>();

		try {

			JsonMappingException jsobExp = (JsonMappingException) exception.getCause();
			int size = 0;
			String invalidField = "";
			if (jsobExp != null) {
				size = jsobExp.getPath().size();
				invalidField = jsobExp.getPath().get(size - 1).getFieldName();
			}

			if ((null != exception.getCause()
					&& exception.getCause().getMessage().contains("not a valid Integer value"))
					|| (null != exception.getCause()
							&& exception.getCause().getMessage().contains("Unexpected character"))) {
				errors.put(invalidField, "Special Characters and Alphabet are not Allowed");
				commonResponse.setErrors(errors);
			}

            // header missing
			if (null != exception.getMessage() && exception.getMessage().contains("Required request header")) {
				commonResponse.setErrors(errors);
				commonResponse.setResponseCode("1010");
				commonResponse.setResponseMessage("Invalid Request");
				commonResponse.setResponseObject(exception.getMessage());
			}
			if(exception instanceof AuthenticationException) {
				commonResponse.setErrors(errors);
				commonResponse.setResponseCode("1010");
				commonResponse.setResponseMessage("Invalid Request");
				commonResponse.setResponseObject(exception.getMessage());	
			}else {
				commonResponse.setErrors(errors);
				commonResponse.setResponseCode("1010");
				commonResponse.setResponseMessage("Something went worng");
				commonResponse.setResponseObject(exception.getMessage());
			}

		} catch (Exception e) {
			logger.error("Exception in handling exception-" + e.getMessage());
		}

		return commonResponse;
	}
}
