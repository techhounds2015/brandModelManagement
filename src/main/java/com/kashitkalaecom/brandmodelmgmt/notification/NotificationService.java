package com.kashitkalaecom.brandmodelmgmt.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashitkalaecom.brandmodelmgmt.models.Brand;
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

	public int notificationCodeExists(String notificationcode) {
		return notificationTemplateRepo.notificationCodeExists(notificationcode);
	}

	public int notificationTemplateIdExists(String id) {
		return notificationTemplateRepo.notificationTemplateIdExists(id);
	}

	public NotificationTemplate getNotificationTempalteById(String notificationTemplateId) {
		return notificationTemplateRepo.getById(notificationTemplateId);
	}

}
