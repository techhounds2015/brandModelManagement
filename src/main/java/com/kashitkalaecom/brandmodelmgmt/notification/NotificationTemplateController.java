package com.kashitkalaecom.brandmodelmgmt.notification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;

@RestController
@RequestMapping("/api/v1/notificationTemplate")
public class NotificationTemplateController {

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private NotificationTemplateBV notificationTemplateBV;

	@PostMapping("/create")
	public APIResponse<NotificationTemplate> createNotificationTemplate(@RequestHeader String requestorId,
			@RequestBody NotificationTemplate notificationTemplate)

	{

		APIResponse<NotificationTemplate> apiResponse = new APIResponse<>();

		try {
			
			apiResponse = notificationTemplateBV.bValidateCreate(null, notificationTemplate, null);
			if (Boolean.FALSE.equals(apiResponse.getProcessingSuccess())) {
				return apiResponse;
			}
			
			
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
	public APIResponse<NotificationTemplate> updateNotificationTemplate(@RequestHeader String requestorId,
			@RequestBody NotificationTemplate notificationTemplate)

	{

		APIResponse<NotificationTemplate> apiResponse = new APIResponse<>();

		try {
			
			apiResponse = notificationTemplateBV.bValidateUpdate(null, notificationTemplate, null);
			if (Boolean.FALSE.equals(apiResponse.getProcessingSuccess())) {
				return apiResponse;
			}
			
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
	
	@GetMapping("/view/{id}")
	public APIResponse<NotificationTemplate> getNotificationTemplate(@RequestHeader String requestorId,
			@PathVariable("id") String notificationTemplateId)

	{

		APIResponse<NotificationTemplate> apiResponse = new APIResponse<>();

		try {
			
			int count = notificationService.notificationTemplateIdExists(notificationTemplateId);
			
			if (count == 0) {
				apiResponse.setResponseCode(StatusCodeEnum.NOTIFICATION_TEMPLATE_CODE_NOT_EXISTS.getCode());
				apiResponse.setResponseMessage(StatusCodeEnum.NOTIFICATION_TEMPLATE_CODE_NOT_EXISTS.getDesc());
				return apiResponse;
			}
						
			NotificationTemplate notificationTemplate  = notificationService.getNotificationTempalteById(notificationTemplateId);
			apiResponse.setResponseCode("200");
			apiResponse.setResponseMessage("Success");
			apiResponse.setResponseObject(notificationTemplate);

		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.ERROR_WHILE_RETREVING_DATA.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ERROR_WHILE_RETREVING_DATA.getDesc());
			apiResponse.setResponseObject(null);
		}

		return apiResponse;
	}
	
	@GetMapping("/viewAll")
	public APIResponse<List<NotificationTemplate>> getAllNotificationTemplate(@RequestHeader String requestorId,@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "code") String sortBy
			)

	{

		APIResponse<List<NotificationTemplate>> apiResponse = new APIResponse<>();

		try {
			
			int count = notificationService.notificationTemplatesExists();
			
			if (count == 0) {
				apiResponse.setResponseCode(StatusCodeEnum.NOTIFICATION_TEMPLATE_CODE_NOT_EXISTS.getCode());
				apiResponse.setResponseMessage(StatusCodeEnum.NOTIFICATION_TEMPLATE_CODE_NOT_EXISTS.getDesc());
				return apiResponse;
			}
						
			List<NotificationTemplate> notificationTemplate  = notificationService.getAllNotificationTemplate(pageNo, pageSize, sortBy);
			apiResponse.setResponseCode("200");
			apiResponse.setResponseMessage("Success");
			apiResponse.setResponseObject(notificationTemplate);

		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.ERROR_WHILE_RETREVING_DATA.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ERROR_WHILE_RETREVING_DATA.getDesc());
			apiResponse.setResponseObject(null);
		}

		return apiResponse;
	}
	
	@PutMapping("/delete/{id}")
	public APIResponse<NotificationTemplate> deleteNotificationTemplate(@RequestHeader String requestorId,
			@PathVariable("id") String notificationTemplateId)

	{

		APIResponse<NotificationTemplate> apiResponse = new APIResponse<>();

		try {
			
			int count = notificationService.notificationTemplateIdExists(notificationTemplateId);
			
			if (count == 0) {
				apiResponse.setResponseCode(StatusCodeEnum.NOTIFICATION_TEMPLATE_CODE_NOT_EXISTS.getCode());
				apiResponse.setResponseMessage(StatusCodeEnum.NOTIFICATION_TEMPLATE_CODE_NOT_EXISTS.getDesc());
				return apiResponse;
			}
						
			NotificationTemplate notificationTemplate = notificationService.delete(notificationTemplateId, requestorId);
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
