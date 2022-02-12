package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kashitkalaecom.brandmodelmgmt.models.Referral;

@Repository
public interface ReferralRepository extends JpaRepository<Referral, String> {


	@Query(" from Referral r where r.code =:code ")
	Referral findByName(String code);
	
	
}
