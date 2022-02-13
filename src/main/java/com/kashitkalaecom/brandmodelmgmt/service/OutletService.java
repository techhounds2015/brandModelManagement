package com.kashitkalaecom.brandmodelmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.Outlet;
import com.kashitkalaecom.brandmodelmgmt.repository.OutletRepositroy;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;


@Service
public class OutletService {

	@Autowired
	OutletRepositroy outletRepositroy;

	public Outlet getOutletByGstNo(String gstNo) {
		return outletRepositroy.findByGstNo(gstNo);
	}
	
	public int outletGstExists(String gstNo) {
		return outletRepositroy.outletGstExists(gstNo);
	}

	public Outlet save(Outlet outlet, String requestorId) {
		outlet.setCreatedOn(CustomClock.timestamp());
		outlet.setCreatedBy(requestorId);
		return outletRepositroy.save(outlet);
	}
	
	public Outlet update(Outlet outlet, String requestorId) {
		outlet.setModifiedBy(requestorId);
		return outletRepositroy.save(outlet);
	}
	
	public Outlet delete(String id, String requestorId) {
		Outlet outlet= outletRepositroy.getById(id);
		outlet.setModifiedOn(CustomClock.timestamp());
		outlet.setModifiedBy(requestorId);
		outlet.setStatus(false);
		return outletRepositroy.save(outlet);
	}

	public Outlet getOutletById(String id) {
		return outletRepositroy.getById(id);
	}
	
	public int outletIdExists(String id) {
		return outletRepositroy.outletIdExists(id);
	}

	public int outletNameExists(String name) {
		return outletRepositroy.outletNameExists(name);
	}
	
	

	
	
}
