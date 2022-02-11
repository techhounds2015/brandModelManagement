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
import com.kashitkalaecom.brandmodelmgmt.validation.MasterDataService;
import com.kashitkalaecom.brandmodelmgmt.validation.ValidationService;
@Component
public class CategoryBV {
	
	@Autowired
	MasterDataService masterDataService;
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryBV.class);
	
	 public APIResponse bValidateCreate(String tenantCode, Category category, String locale) {
		 
		 APIResponse<Category> apiResponse = new APIResponse<Category>();
		 apiResponse.setProcessingSuccess(true);
		 List<String> list = new ArrayList<String>();
		try {
			list = validateCreate(tenantCode, category, locale);

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

	private List<String> validateCreate(String tenantCode, Category category, String locale) {
		
		List<String> errorList = new ArrayList<String>();

       
        List<String> categoryList = masterDataService.getDataNameByType(tenantCode, "Category");
       
        if (categoryList != null && !categoryList.contains(category.getCategory())) {
        	String localError="Category.category invailid";
            logger.debug("localError::" + localError);
            errorList.add(localError);  
        }
        
        if (categoryList != null && !categoryList.contains(category.getParentCategory())) {
        	String localError="Category.parentCategory invailid";
            logger.debug("localError::" + localError);
            errorList.add(localError);  
        }
        
        if (categoryList != null && !categoryList.contains(category.getImage())) {
        	String localError="Category.image invailid";
            logger.debug("localError::" + localError);
            errorList.add(localError);  
        }
        
        if (categoryList != null && !categoryList.contains(category.getFriendlyName())) {
        	String localError="Category.friendlyName invailid";
            logger.debug("localError::" + localError);
            errorList.add(localError);  
        }
        
        if (categoryList != null && !categoryList.contains(category.getName())) {
        	String localError="Category.name invailid";
            logger.debug("localError::" + localError);
            errorList.add(localError);  
        }
        
        if (categoryList != null && !categoryList.contains(category.getStatus())) {
        	String localError="Category.status invailid";
            logger.debug("localError::" + localError);
            errorList.add(localError);  
        }
        if (categoryList != null && !categoryList.contains(category.getDescription())) {
        	String localError="Category.description invailid";
            logger.debug("localError::" + localError);
            errorList.add(localError);  
        }
        
        return errorList;
	}

}
