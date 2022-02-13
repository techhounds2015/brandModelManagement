package com.kashitkalaecom.brandmodelmgmt.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kashitkalaecom.brandmodelmgmt.cache.CacheManager;
import com.kashitkalaecom.brandmodelmgmt.models.User;
import com.kashitkalaecom.brandmodelmgmt.security.JwtTokenRequest;
import com.kashitkalaecom.brandmodelmgmt.security.JwtTokenUtil;
import com.kashitkalaecom.brandmodelmgmt.security.JwtUserFactory;

import io.jsonwebtoken.Claims;
@Service
public class UserServiceImpl  implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	UserService userService;


	@Override
	public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
		
		String consumerId = jwtTokenUtil.getUsernameFromToken(token);
		logger.info(consumerId);
		Claims claims = jwtTokenUtil.getAllClaimsFromToken(token);
		@SuppressWarnings("unchecked")
		HashMap<String, String> map = (HashMap<String, String>) claims.get("permissionList");
		ObjectMapper mapper = new ObjectMapper();

		JwtTokenRequest req = mapper.convertValue(map, JwtTokenRequest.class);
		
		try {
			User user = CacheManager.getCacheManager().getObjectUsingCacheKey(consumerId, User.class);
			if(null != user)
			return JwtUserFactory.create(req);
		} catch (Exception e) {
			throw new UsernameNotFoundException("Unauthorized");

			
		}
		return null;
	}
	

	

}
