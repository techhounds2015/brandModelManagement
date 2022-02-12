package com.kashitkalaecom.brandmodelmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kashitkalaecom.brandmodelmgmt.notification.NotificationTemplate;

public interface NotificationTemplateRepo extends JpaRepository<NotificationTemplate, String>{

}
