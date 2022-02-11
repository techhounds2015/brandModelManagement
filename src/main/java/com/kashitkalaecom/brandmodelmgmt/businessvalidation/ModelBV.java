package com.kashitkalaecom.brandmodelmgmt.businessvalidation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.Model;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;

@Component
public class ModelBV {

	@Autowired
	MasterDataService masterDataService;

	private static final Logger logger = LoggerFactory.getLogger(ModelBV.class);

	public APIResponse bValidateCreate(String tenantCode, Model model, String locale) {

		APIResponse<Model> apiResponse = new APIResponse<Model>();
		apiResponse.setProcessingSuccess(true);
		List<String> list = new ArrayList<String>();
		try {
			list = validateCreate(tenantCode, model, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage(StatusCodeEnum.ERROR_ON_VALIDATING_REQUEST.getCode());
			apiResponse.setResponseCode(StatusCodeEnum.ERROR_ON_VALIDATING_REQUEST.getDesc());
			apiResponse.setProcessingSuccess(false);
			logger.error("error while validating request", e);
			return apiResponse;
		}
		if (list.size() > 0) {
			apiResponse.setResponseMessage(StatusCodeEnum.INVALID_REQUEST.getCode());
			apiResponse.setResponseCode(StatusCodeEnum.INVALID_REQUEST.getDesc());
			apiResponse.setProcessingErrors(list);
			apiResponse.setProcessingSuccess(false);

		}
		return apiResponse;

	}

	private List<String> validateCreate(String tenantCode, Model model, String locale) {

		List<String> errorList = new ArrayList<String>();

		List<String> modelList = masterDataService.getDataNameByType(tenantCode, "Model");

		if (modelList != null && !modelList.contains(model.getCategoryId())) {
			String localError = "Model.categoryId invailid";
			logger.debug("localError::" + localError);
			errorList.add(localError);
		}

		if (modelList != null && !modelList.contains(model.getName())) {
			String localError = "Model.name invailid";
			logger.debug("localError::" + localError);
			errorList.add(localError);
		}

		if (modelList != null && !modelList.contains(model.getStatus())) {
			String localError = "Model.status invailid";
			logger.debug("localError::" + localError);
			errorList.add(localError);
		}

		if (modelList != null && !modelList.contains(model.getBrandId())) {
			String localError = "Model.brandId invailid";
			logger.debug("localError::" + localError);
			errorList.add(localError);
		}

		if (modelList != null && !modelList.contains(model.getDescription())) {
			String localError = "Model.description invailid";
			logger.debug("localError::" + localError);
			errorList.add(localError);
		}

		return errorList;
	}

}
