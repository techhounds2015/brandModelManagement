package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kashitkalaecom.brandmodelmgmt.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	@Query("select u.walletId from user u where u.walletId =:walletId ")
	User findByName(String walletId);

}
