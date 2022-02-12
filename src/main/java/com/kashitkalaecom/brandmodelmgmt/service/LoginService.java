package com.kashitkalaecom.brandmodelmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.User;
import com.kashitkalaecom.brandmodelmgmt.repository.LoginRepository;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;
@Service
public class LoginService {

	@Autowired
	LoginRepository loginRepository;
	
	public User save(User user, String requestorId) {
		user.setCreatedBy(requestorId);
		user.setCreatedOn(CustomClock.timestamp());
		return loginRepository.save(user);
	}

	public User getUserByPassword(String password) {
		return loginRepository.findByName(password);
	}

}
