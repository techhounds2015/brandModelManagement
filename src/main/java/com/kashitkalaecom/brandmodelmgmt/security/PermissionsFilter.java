package com.kashitkalaecom.brandmodelmgmt.security;

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

import com.kashitkalaecom.brandmodelmgmt.rolepermission.RolePermissionMapping;
import com.kashitkalaecom.brandmodelmgmt.rolepermission.RolePermissionMappingService;
import com.kashitkalaecom.brandmodelmgmt.utilities.PropertyConfig;

@Component
@Order(1)
public class PermissionsFilter implements Filter {

	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	RolePermissionMappingService mapping;
	
	private String enableJwt;

	private static final Logger logger = LoggerFactory.getLogger(PermissionsFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		Filter.super.init(filterConfig);
		logger.info("PERMISSSIONS FILTER STARTED");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		enableJwt=PropertyConfig.loadProperties().getProperty("enableJwt");
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String reqUri = httpRequest.getRequestURI();
		String[] tokens = reqUri.split("/");
		String module = "";
		String action = "";
		String requestUserid = httpRequest.getHeader("requestorId");
		String authHeader =  httpRequest.getHeader("Authorization");
		String username = null;
		String authToken = null;
		
		
		if (!Boolean.valueOf(enableJwt)) {
			chain.doFilter(request, response);
			return;
		}
		
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			authToken = authHeader.substring(7);
			username = jwtTokenUtil.getUsernameFromToken(authToken);
		} else {
			logger.warn("couldn't find bearer string, will ignore the header");
			return;
		}
		
		
		if (reqUri.equals("/api/v1/login/signIn")) {
			chain.doFilter(request, response);
			return;
		}
		 


		if (tokens != null && tokens.length > 3) {
			module = tokens[3];
		}

		if (tokens != null && tokens.length > 4) {
			action = tokens[4];
		}

		RolePermissionMapping pemisssion = mapping.getUserPermission(module, username);
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
