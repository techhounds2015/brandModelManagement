package com.kashitkalaecom.brandmodelmgmt.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.service.BrandService;
import com.kashitkalaecom.brandmodelmgmt.service.CategoryService;
import com.kashitkalaecom.brandmodelmgmt.service.ModelService;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;

@Component
public class NotificationTemplateBV {

	@Autowired
	MasterDataService masterDataService;

	@Autowired
	NotificationService notificationService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ModelService modelService;

	@Autowired
	BrandService brandService;

	private static final Logger logger = LoggerFactory.getLogger(NotificationTemplateBV.class);

	public APIResponse<NotificationTemplate> bValidateCreate(String tenantCode, NotificationTemplate notificationTemplate, String locale) {

		APIResponse<NotificationTemplate> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
		try {
			apiResponse = validateCreate(tenantCode, notificationTemplate, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}

		return apiResponse;
	}

	private APIResponse<NotificationTemplate> validateCreate(String tenantCode, NotificationTemplate notificationTemplate, String locale) {

		APIResponse<NotificationTemplate> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);

		int catCount = notificationService.notificationCodeExists(notificationTemplate.getNotificationcode());

		if (catCount > 0) {
			apiResponse.setResponseCode(StatusCodeEnum.NOTIFICATION_TEMPLATE_CODE_DUPLCIATE.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.NOTIFICATION_TEMPLATE_CODE_DUPLCIATE.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}


		return apiResponse;
	}

	public APIResponse<NotificationTemplate> bValidateUpdate(String tenantCode, NotificationTemplate notificationTemplate, String locale) {
		APIResponse<NotificationTemplate> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
		try {
			apiResponse = validateUpdate(tenantCode, notificationTemplate, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}

		return apiResponse;
	}

	private APIResponse<NotificationTemplate> validateUpdate(String tenantCode, NotificationTemplate notificationTemplate, String locale) {

		APIResponse<NotificationTemplate> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);

		// Product exists

		int productCount = notificationService.notificationTemplateIdExists(notificationTemplate.getId());
		if (productCount == 0) {
			apiResponse.setResponseCode(StatusCodeEnum.NOTIFICATION_TEMPLATE_CODE_DUPLCIATE.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.NOTIFICATION_TEMPLATE_CODE_NOT_EXISTS.getDesc());
			apiResponse.setProcessingSuccess(false);
			return apiResponse;
		}

		

		return apiResponse;
	}

}
