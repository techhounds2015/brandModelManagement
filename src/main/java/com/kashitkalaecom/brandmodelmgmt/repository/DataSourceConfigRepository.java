package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kashitkalaecom.brandmodelmgmt.config.DataSourceConfig;

public interface DataSourceConfigRepository extends JpaRepository<DataSourceConfig, Long> {
    DataSourceConfig findByName(String name);
}
