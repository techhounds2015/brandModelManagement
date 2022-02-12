package com.kashitkalaecom.brandmodelmgmt.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

	private UserDetailsService userDetailsService;
	private JwtTokenUtil jwtTokenUtil;
	private String tokenHeader;

	public JwtAuthorizationTokenFilter(UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil,
			String tokenHeader) {
		this.userDetailsService = userDetailsService;
		this.jwtTokenUtil = jwtTokenUtil;
		this.tokenHeader = tokenHeader;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestHeader = request.getHeader(this.tokenHeader);
		logger.info("*** Path         ****** : " + request.getRequestURI());
	    logger.info("*** Method Type  ****** : " + request.getMethod());
			
		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
			response.setHeader("Access-Control-Allow-Headers", "authorization,content-type");

		} else {
			String username = null;
			String authToken = null;
			if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
				authToken = requestHeader.substring(7);
				username = jwtTokenUtil.getUsernameFromToken(authToken);
			} else {
				logger.warn("couldn't find bearer string, will ignore the header");
			}
			

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				// It is not compelling necessary to load the use details from the database. You
				// could also store the information
				// in the token and read it from it. It's up to you ;)
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(authToken);
				logger.info("*** Username     ****** : "+userDetails.getUsername());
			    logger.info("*** Path         ****** : " + request.getRequestURI());
			    logger.info("*** Method Type  ****** : " + request.getMethod());
				

				// For simple validation it is completely sufficient to just check the token
				// integrity. You don't have to call
				// the database compellingly. Again it's up to you ;)
				if (jwtTokenUtil.validateToken(authToken, userDetails)) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
			chain.doFilter(request, response);
		}
	}
}
