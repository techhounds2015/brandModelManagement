package com.kashitkalaecom.brandmodelmgmt.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;

@RestController
@RequestMapping("/api/v1/notificationTemplate")
public class NotificationTemplateController {

	@Autowired
	private NotificationService notificationService;

	@PostMapping("/create")
	public APIResponse<NotificationTemplate> createNewNotificationTemplate(@RequestHeader String requestorId,
			@RequestBody NotificationTemplate notificationTemplate)

	{

		APIResponse<NotificationTemplate> apiResponse = new APIResponse<>();

		try {
			notificationTemplate = notificationService.save(notificationTemplate, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.NOTIFICATION_TEMPLATE_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.NOTIFICATION_TEMPLATE_CREATED.getDesc());
			apiResponse.setResponseObject(notificationTemplate);

		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.NOTIFICATION_TEMPLATE_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.NOTIFICATION_TEMPLATE_CREATION_FAILED.getDesc());
		}
		return apiResponse;
	}

	@PutMapping("/update")
	public APIResponse<NotificationTemplate> updateNewNotificationTemplate(@RequestHeader String requestorId,
			@RequestBody NotificationTemplate notificationTemplate)

	{

		APIResponse<NotificationTemplate> apiResponse = new APIResponse<>();

		try {
			
			notificationTemplate = notificationService.update(notificationTemplate, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.NOTIFICATION_TEMPLATE_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.NOTIFICATION_TEMPLATE_UPDATED.getDesc());
			apiResponse.setResponseObject(notificationTemplate);

		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.NOTIFICATION_TEMPLATE_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.NOTIFICATION_TEMPLATE_UPDATION_FAILED.getDesc());
		}

		return apiResponse;
	}
}
