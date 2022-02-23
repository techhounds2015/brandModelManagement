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

	@Query("select count(*) from User u where u.email =:email ")
	public int userEmailExists(String email);
	
	@Query("select count(*) from User u where u.mobile =:mobile ")
	public int userMobileExists(String mobile);

	@Query("select count(*) from User u where u.id =:userId ")
	public int userIdExists(String userId);

	@Query("from User u where u.userName =:userName")
	public User getByUserName(String userName);
	
	@Query("select count(*) from User u where u.userName =:userName")
	public int userNameExists(String userName);

}
