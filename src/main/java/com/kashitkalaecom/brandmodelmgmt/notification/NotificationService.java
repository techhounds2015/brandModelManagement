package com.kashitkalaecom.brandmodelmgmt.notification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	public int notificationCodeExists(String notificationcode) {
		return notificationTemplateRepo.notificationCodeExists(notificationcode);
	}

	public int notificationTemplateIdExists(String id) {
		return notificationTemplateRepo.notificationTemplateIdExists(id);
	}

	public NotificationTemplate getNotificationTempalteById(String notificationTemplateId) {
		return notificationTemplateRepo.getById(notificationTemplateId);
	}

	public NotificationTemplate delete(String notificationTemplateId, String requestorId) {

		NotificationTemplate notificationTemplate = notificationTemplateRepo.getById(notificationTemplateId);
		notificationTemplate.setStatus(false);
		notificationTemplate.setModifiedBy(requestorId);
		notificationTemplate.setModifiedOn(CustomClock.timestamp());
		return notificationTemplateRepo.save(notificationTemplate);
	}

	public int notificationTemplatesExists() {
		return notificationTemplateRepo.notificationTemplatesExists();
	}

	public List<NotificationTemplate> getAllNotificationTemplate(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<NotificationTemplate> pagedResult = notificationTemplateRepo.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<>();
		}
	}

}
