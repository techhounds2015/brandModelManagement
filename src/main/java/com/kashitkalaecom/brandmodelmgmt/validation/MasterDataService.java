package com.kashitkalaecom.brandmodelmgmt.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataService {
	
	@Autowired
	MasterDataRepository masterDataRepository;

	public List<String> getDataNameByType(String tenantCode, String masterDataType) {
		return	masterDataRepository.getDataNameByType(masterDataType);
		
	}

}
