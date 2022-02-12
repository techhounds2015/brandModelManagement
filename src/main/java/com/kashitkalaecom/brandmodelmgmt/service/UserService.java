package com.kashitkalaecom.brandmodelmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.User;
import com.kashitkalaecom.brandmodelmgmt.repository.UserRepository;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	public User save(User user, String requestorId) {
		user.setCreatedBy(requestorId);
		user.setCreatedOn(CustomClock.timestamp());
		return userRepository.save(user);
	}

	public User getUserById(String userId) {
		return userRepository.getById(userId);
	}

	public User update(User user, String requestorId) {
		user.setModifiedBy(requestorId);
		user.setModifiedOn(CustomClock.timestamp());
		return userRepository.save(user);
	}

	public User delete(String id, String requestorId) {
		User user = userRepository.getById(id);
		user.setModifiedBy(requestorId);
		user.setModifiedOn(CustomClock.timestamp());
		user.setStatus(false);
		return userRepository.save(user);
	}

	public User getUserByWalletId(String walletId) {
		return userRepository.findByWalletId(walletId);
	}

	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User getUserByPhoneNumber(String mobile) {
		return userRepository.findByMobile(mobile);
	}

}
