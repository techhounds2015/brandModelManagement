package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kashitkalaecom.brandmodelmgmt.notification.NotificationTemplate;

public interface NotificationTemplateRepo extends JpaRepository<NotificationTemplate, String>{

	@Query("Select count(1) from NotificationTemplate c where c.code =:code ")
	int notificationCodeExists(String code);
	
	@Query("Select count(1) from NotificationTemplate c where c.id =:id ")
	int notificationTemplateIdExists(String id);

}
