package com.kashitkalaecom.brandmodelmgmt.service;

import java.util.List;

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

	public List<User> getByUserName(String tenantCode, String userId) {
		return userRepository.findByUserId(userId);
	}

	public int userEmailExists(String email) {
		return userRepository.userEmailExists(email);
	}
	
	public int userMobileExists(String email) {
		return userRepository.userMobileExists(email);
	}

	public int userIdExists(String userId) {
		return userRepository.userIdExists(userId);
	}

}
