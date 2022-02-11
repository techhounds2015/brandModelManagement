package com.kashitkalaecom.brandmodelmgmt.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.repository.NotificationTemplateRepo;
import com.kashitkalaecom.brandmodelmgmt.utilities.CustomClock;

@Service
public class NotificationService {

	@Autowired
	NotificationTemplateRepo notificationTemplateRepo;

	public NotificationTemplate save(NotificationTemplate notificationTemplate, String requestorId) {
		notificationTemplate.setCreatedBy(requestorId);
		notificationTemplate.setCreatedOn(CustomClock.timestamp());
		return notificationTemplateRepo.save(notificationTemplate);
	}

	public NotificationTemplate update(NotificationTemplate notificationTemplate, String requestorId) {
		notificationTemplate.setModifiedBy(requestorId);
		notificationTemplate.setModifiedOn(CustomClock.timestamp());
		return notificationTemplateRepo.save(notificationTemplate);

	}

}
