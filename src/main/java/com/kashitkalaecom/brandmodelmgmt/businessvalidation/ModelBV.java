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
import com.kashitkalaecom.brandmodelmgmt.models.Model;
import com.kashitkalaecom.brandmodelmgmt.service.ModelService;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;

@Component
public class ModelBV {

	@Autowired
	MasterDataService masterDataService;
	
	@Autowired
	ModelService modelService;

	private static final Logger logger = LoggerFactory.getLogger(ModelBV.class);

	public APIResponse bValidateCreate(String tenantCode, Model model, String locale) {

		APIResponse<Model> apiResponse = new APIResponse<Model>();
		apiResponse.setProcessingSuccess(true);
		
		try {
			apiResponse = validateCreate(tenantCode, model, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setProcessingSuccess(false);
			logger.error(e.getMessage(), e);
			return apiResponse;
		}
		return apiResponse;

	}

	private APIResponse<Model> validateCreate(String tenantCode, Model model, String locale) {

		APIResponse<Model> apiResponse = new APIResponse<>();

		List<String> modelList = masterDataService.getDataNameByType(tenantCode, "Model");

		if (modelList != null && !modelList.contains(model.getCategoryId())) {
        	apiResponse.setResponseCode(StatusCodeEnum.MODEL_NOT_EXISTS.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.MODEL_NOT_EXISTS.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        Model model1 = modelService.getModelByName(model.getName());
        
        if (model1 != null) {
        	apiResponse.setResponseCode(StatusCodeEnum.CATEGORY_DUPLICATE.getCode());
        	apiResponse.setResponseMessage(StatusCodeEnum.CATEGORY_DUPLICATE.getDesc());
        	apiResponse.setProcessingSuccess(false);
        	return apiResponse;
        }
        
        return apiResponse;
	}

}
