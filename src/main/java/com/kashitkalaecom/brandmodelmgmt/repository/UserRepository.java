package com.kashitkalaecom.brandmodelmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kashitkalaecom.brandmodelmgmt.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	@Query("from User u where u.walletId =:walletId ")
	public User findByWalletId(String walletId);

	@Query("from User u where u.email =:email ")
	public User findByEmail(String email);

	@Query("from User u where u.mobile =:mobile ")
	public User findByMobile(String mobile);

	public List<User> findByUserId(String userId);

}
