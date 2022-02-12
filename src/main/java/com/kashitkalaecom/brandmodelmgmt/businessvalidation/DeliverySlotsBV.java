package com.kashitkalaecom.brandmodelmgmt.businessvalidation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Category;
import com.kashitkalaecom.brandmodelmgmt.models.DeliverySlots;
import com.kashitkalaecom.brandmodelmgmt.service.DeliverySlotsService;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;
@Component
public class DeliverySlotsBV {
	
	@Autowired
	MasterDataService masterDataService;
	
	@Autowired
	DeliverySlotsService deliverySlotsService;
	
	private static final Logger logger = LoggerFactory.getLogger(DeliverySlotsBV.class);
	
	 public APIResponse<DeliverySlots> bValidateCreate(String tenantCode, DeliverySlots deliverySlots, String locale) {
		 
		 APIResponse<DeliverySlots> apiResponse = new APIResponse<DeliverySlots>();
		 apiResponse.setProcessingSuccess(true);
		 List<String> list = new ArrayList<String>();
		try {
			apiResponse = validateCreate(tenantCode, deliverySlots, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}
		if (list.size() > 0) {
			apiResponse.setResponseMessage("FAILURE");
			apiResponse.setResponseCode("9000");
			apiResponse.setProcessingErrors(list);
			apiResponse.setProcessingSuccess(false);

		}
		return apiResponse;

}

	private APIResponse<DeliverySlots> validateCreate(String tenantCode, DeliverySlots deliverySlots, String locale) {
		
		APIResponse<DeliverySlots> apiResponse = new APIResponse<>();
		apiResponse.setProcessingSuccess(true);
        List<String> deliverySlotsList = masterDataService.getDataNameByType(tenantCode, "DeliverySlots");
        
        if (deliverySlotsList != null && !deliverySlotsList.contains(deliverySlots.getAllDays())) {
        	apiResponse.setResponseCode(StatusCodeEnum.DELIVERY_CONFIG_INVALID.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.DELIVERY_CONFIG_INVALID.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        DeliverySlots delv = deliverySlotsService.getDeliverySlotsByName(deliverySlots.getAllDays());
        
        if (delv != null) {
        	apiResponse.setResponseCode(StatusCodeEnum.DELIVERY_CONFIG_DUPLICATE.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.DELIVERY_CONFIG_DUPLICATE.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        return apiResponse;
	}

}
