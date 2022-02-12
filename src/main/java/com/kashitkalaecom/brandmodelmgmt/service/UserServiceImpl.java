package com.kashitkalaecom.brandmodelmgmt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.kashitkalaecom.brandmodelmgmt.security.JwtTokenUtil;

public class UserServiceImpl  implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Value("${integration.url}")
	private String integrationUrl;
	
	@Value("${db.ext.url}")
	private String dbExtUrl;

	@Value("${getprofile.email.pwd}")
	private String getProfileByEmailAndPassword;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return null;
	}


}
