package com.kashitkalaecom.brandmodelmgmt.complain;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class ComplainService {

	@Autowired
	ComplainRepository complainRepository;

	public Complain save(Complain complain, String requestorId) {
		
		if(null == complain.getComplainNumber() || complain.getComplainNumber().isEmpty()) {
			String randomComplainID = generateComplainID().toUpperCase();
			complain.setComplainNumber(randomComplainID);
		}
		complain.setCreatedBy(requestorId);
		complain.setCreatedOn(CustomClock.timestamp());
		return complainRepository.save(complain);
	}

	public Complain getComplainByCustomerId(String ccustomerId) {
		return complainRepository.getById(ccustomerId);
	}

	public Page<Complain> getAllComplain(int offset, int limit) {
		return complainRepository.findAll(PageRequest.of(offset, limit, Sort.by("createdOn").descending()));
	}

	private static String generateComplainID() {
		return RandomStringUtils.randomAlphanumeric(10);
	}
}
