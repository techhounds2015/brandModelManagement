package com.kashitkalaecom.brandmodelmgmt.validation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationRepository extends JpaRepository<Validation, String>{

	@Query("FROM Validation v where v.simpleClassName = :simpleClassName")
	List<Validation> getValidations(@Param("simpleClassName") String simpleClassName);

}
