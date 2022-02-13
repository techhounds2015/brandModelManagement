package com.kashitkalaecom.brandmodelmgmt.models;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.service.RolePermissionMappingService;

@Component
@Order(1)
public class PermissionsFilter implements Filter {

	@Autowired
	RolePermissionMappingService mapping;

	private static final Logger logger = LoggerFactory.getLogger(PermissionsFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		Filter.super.init(filterConfig);
		logger.info("PERMISSSIONS FILTER STARTED");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String reqUri = httpRequest.getRequestURI();
		String[] tokens = reqUri.split("/");
		String module = "";
		String action = "";
		String requestUserid = httpRequest.getHeader("requestorId");
		
		if (reqUri.equals("/api/v1/login/loginwithpassword")) {
			chain.doFilter(request, response);
			return;
		}
		 


		if (tokens != null && tokens.length > 3) {
			module = tokens[3];
		}

		if (tokens != null && tokens.length > 4) {
			action = tokens[4];
		}

		RolePermissionMapping pemisssion = mapping.getUserPermission(module, requestUserid);
		if ("CREATE".equals(pemisssion.getAction())) {
			chain.doFilter(request, response);
			return;
		}
		if (action.equals(pemisssion.getAction())) {
			chain.doFilter(request, response);
			return;
		}

	}

}
