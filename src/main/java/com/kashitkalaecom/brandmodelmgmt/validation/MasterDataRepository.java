package com.kashitkalaecom.brandmodelmgmt.validation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterDataRepository extends JpaRepository<MasterData, String> {
	
	@Query("select m.dataName from MasterData m where m.masterDataType =:masterDataType ")
	List<String> getDataNameByType(String masterDataType);

}
