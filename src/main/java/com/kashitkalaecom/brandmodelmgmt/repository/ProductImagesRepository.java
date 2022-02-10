package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kashitkalaecom.brandmodelmgmt.models.ProductImages;

@Repository
public interface ProductImagesRepository extends JpaRepository<ProductImages, String> {

}
