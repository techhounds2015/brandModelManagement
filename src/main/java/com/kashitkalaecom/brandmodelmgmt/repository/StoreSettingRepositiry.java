package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kashitkalaecom.brandmodelmgmt.models.StoreSetting;

@Repository
public interface StoreSettingRepositiry extends JpaRepository<StoreSetting, String>{

}
