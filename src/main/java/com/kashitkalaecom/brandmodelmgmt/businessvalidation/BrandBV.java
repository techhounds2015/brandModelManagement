package com.kashitkalaecom.brandmodelmgmt.businessvalidation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.models.Brand;
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;

@Component
public class BrandBV {

	@Autowired
	MasterDataService masterDataService;

	private static final Logger logger = LoggerFactory.getLogger(BrandBV.class);

	public APIResponse<Brand> bValidateCreate(String tenantCode, Brand brand, String locale) {

		APIResponse<Brand> apiResponse = new APIResponse<Brand>();
		apiResponse.setProcessingSuccess(true);
		List<String> list = new ArrayList<String>();
		try {
			list = validateCreate(tenantCode, brand, locale);

		} catch (Exception e) {
			apiResponse.setResponseMessage("FAILURE");
			apiResponse.setResponseCode("9000");
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

	private List<String> validateCreate(String tenantCode, Brand brand, String locale) {

		List<String> errorList = new ArrayList<String>();

		List<String> brandList = masterDataService.getDataNameByType(tenantCode, "Brand");

		if (brandList != null && !brandList.contains(brand.getCategoryId())) {
			String localError = "Brand.categoryid invailid";
			logger.debug("localError::" + localError);
			errorList.add(localError);
		}

		if (brandList != null && !brandList.contains(brand.getLogo())) {
			String localError = "Brand.logo invailid";
			logger.debug("localError::" + localError);
			errorList.add(localError);
		}

		if (brandList != null && !brandList.contains(brand.getName())) {
			String localError = "Brand.name invailid";
			logger.debug("localError::" + localError);
			errorList.add(localError);
		}

		if (brandList != null && !brandList.contains(brand.getStatus())) {
			String localError = "Category.status invailid";
			logger.debug("localError::" + localError);
			errorList.add(localError);
		}

		if (brandList != null && !brandList.contains(brand.getDescription())) {
			String localError = "Brand.description invailid";
			logger.debug("localError::" + localError);
			errorList.add(localError);
		}

		return errorList;
	}

}
