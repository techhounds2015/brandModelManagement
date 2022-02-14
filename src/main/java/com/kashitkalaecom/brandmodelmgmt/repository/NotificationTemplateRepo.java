package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.kashitkalaecom.brandmodelmgmt.notification.NotificationTemplate;

public interface NotificationTemplateRepo extends PagingAndSortingRepository<NotificationTemplate, String> {

	@Query("Select count(1) from NotificationTemplate c where c.code =:code ")
	int notificationCodeExists(String code);
	
	@Query("Select count(1) from NotificationTemplate c where c.id =:id ")
	int notificationTemplateIdExists(String id);

	@Query("Select count(1) from NotificationTemplate c")
	int notificationTemplatesExists();

	@Query("Select c from NotificationTemplate c where c.id = :id")
	NotificationTemplate getById(String id);

}
