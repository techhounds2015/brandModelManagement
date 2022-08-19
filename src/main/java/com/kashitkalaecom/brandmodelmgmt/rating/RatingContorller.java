package com.kashitkalaecom.brandmodelmgmt.rating;

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
import org.springframework.web.bind.annotation.RestController;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;

@RestController
@RequestMapping("/api/v1/rating")
public class RatingContorller {

	@Autowired
	RatingService ratingService;

	@Autowired
	RatingFV ratingFV;
	
	@Autowired
	RatingBV ratingBV;

	@PostMapping("/create")
	public APIResponse<Ratings> createRating(@RequestHeader String requestorId, @RequestBody Ratings rating) {
		APIResponse<Ratings> apiResponse = new APIResponse<>();
		try {
			apiResponse = ratingFV.fValidateCreate(null, rating, null);
			if (Boolean.FALSE.equals(apiResponse.getValidationSuccess())) {
				return apiResponse;
			}
			apiResponse = ratingBV.bValidateCreate(null, rating, null);
			if (Boolean.FALSE.equals(apiResponse.getProcessingSuccess())) {
				return apiResponse;
			}
			rating = ratingService.save(rating, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.RATING_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.RATING_CREATED.getDesc());
			apiResponse.setResponseObject(rating);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.RATING_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.RATING_CREATION_FAILED.getDesc());
			apiResponse.setResponseObject(rating);
		}
		return apiResponse;
	}

	@GetMapping("/view/{ratingId}")
	public APIResponse<Ratings> rating(@RequestHeader String requestorId, @PathVariable("ratingId") String ratingId) {

		APIResponse<Ratings> apiResponse = new APIResponse<>();
		Ratings ratingCount = ratingService.getRatingById(ratingId);
		if (ratingCount == null) {
			apiResponse.setResponseCode(StatusCodeEnum.RATING_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.RATING_NOT_EXISTS.getDesc());
			return apiResponse;
		}
		Ratings rating = ratingService.getRatingById(ratingId);
		apiResponse.setResponseMessage("Success");
		apiResponse.setResponseObject(rating);
		return apiResponse;
	}
	
	@GetMapping("/viewAll")
	public APIResponse<List<Ratings>> allRatings(@RequestHeader String requestorId) {

		List<Ratings> ratingList = new ArrayList<>();
		APIResponse<List<Ratings>> apiResponse = new APIResponse<>();
		try {
			ratingList = ratingService.getAllRatings();
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.ERROR_WHILE_RETREVING_DATA.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ERROR_WHILE_RETREVING_DATA.getDesc());
			return apiResponse;
		}
		if (ratingList.isEmpty()) {
			apiResponse.setResponseCode(StatusCodeEnum.RATING_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.RATING_NOT_EXISTS.getDesc());
			return apiResponse;
		}
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("Success");
		apiResponse.setResponseObject(ratingList);
		return apiResponse;
	}
	
	@GetMapping("/viewAll/{offset}/{limit}")
	public APIResponse<Page<Ratings>> allRating(@PathVariable("offset") int offset, @PathVariable("limit") int limit) {

		Page<Ratings> ratingLists;
		
		APIResponse<Page<Ratings>> apiResponse = new APIResponse<>();
		try {
		 ratingLists = ratingService.getAllRating(offset, limit);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.ERROR_WHILE_RETREVING_DATA.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ERROR_WHILE_RETREVING_DATA.getDesc());
			return apiResponse;
		}
		if (ratingLists.isEmpty()) {
			apiResponse.setResponseCode(StatusCodeEnum.RATING_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.RATING_NOT_EXISTS.getDesc());
			return apiResponse;
		}
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("Success");
		apiResponse.setResponseObject(ratingLists);
		return apiResponse;
	}
	

	@PutMapping("/update")
	public APIResponse<Ratings> updateRating(@RequestHeader String requestorId, @RequestBody Ratings rating) {
		APIResponse<Ratings> apiResponse = new APIResponse<>();
		try {
			apiResponse = ratingBV.bValidateUpdate(null, rating, null);
			
            if (Boolean.FALSE.equals(apiResponse.getProcessingSuccess())) {
                return apiResponse;
            }
            rating = ratingService.update(rating, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.RATING_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.RATING_UPDATED.getCode());
			apiResponse.setResponseObject(rating);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.RATING_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.RATING_UPDATION_FAILED.getCode());
		}
		return apiResponse;
	}

	@PutMapping("/delete/{id}")
	public APIResponse<Ratings> deleteRating(@RequestHeader String requestorId, @PathVariable String id) {

		APIResponse<Ratings> apiResponse = new APIResponse<>();
		try {
			Ratings ratingCount = ratingService.getRatingById(id);
			if (ratingCount == null) {
				apiResponse.setResponseCode(StatusCodeEnum.RATING_NOT_EXISTS.getCode());
				apiResponse.setResponseMessage(StatusCodeEnum.RATING_NOT_EXISTS.getDesc());
				return apiResponse;
			}
			Ratings rating = ratingService.delete(id, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.RATING_UPDATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.RATING_UPDATED.getCode());
			apiResponse.setResponseObject(rating);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.RATING_UPDATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.RATING_UPDATION_FAILED.getCode());
		}
		return apiResponse;
	}
}
