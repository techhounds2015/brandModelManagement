package com.kashitkalaecom.brandmodelmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.cache.CacheManager;
import com.kashitkalaecom.brandmodelmgmt.models.User;
import com.kashitkalaecom.brandmodelmgmt.repository.LoginRepository;
import com.kashitkalaecom.brandmodelmgmt.responses.LoginResponse;
import com.kashitkalaecom.brandmodelmgmt.security.JwtTokenRequest;
import com.kashitkalaecom.brandmodelmgmt.security.JwtTokenUtil;
@Service
public class LoginService {

	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	
	

	public LoginResponse vaildateUser( APIResponse apiResponse, String requestorId) throws Exception {
		User user= (User) apiResponse.getResponseObject();
		JwtTokenRequest jwtTokenRequest = new JwtTokenRequest();
		jwtTokenRequest.setUserName(user.getUserId());;
		String token = jwtTokenUtil.generateToken(user.getUserId(), jwtTokenRequest);
		LoginResponse  loginResponse=new LoginResponse();
		loginResponse.setId(user.getId());
		loginResponse.setToken(token);
		CacheManager.getCacheManager().cacheObject(user.getUserId(), user);
		return loginResponse;
	}

}
