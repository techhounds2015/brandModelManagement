package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kashitkalaecom.brandmodelmgmt.models.PickUpAndDeliveryConfiguration;

@Repository
public interface PickUpAndDeliveryConfigurationRepository extends JpaRepository<PickUpAndDeliveryConfiguration, String> {

}
