package com.kashitkalaecom.brandmodelmgmt.complain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;

@RestController
@RequestMapping("/api/v1/complain")
public class ComplainController {

	@Autowired
	ComplainService complainService;

	@Autowired
	ComplainFV complainFV;

	@Autowired
	ComplainBV complainBV;

	@PostMapping("/create")
	public APIResponse<Complain> createComplain(@RequestHeader String requestorId, @RequestBody Complain complain) {
		APIResponse<Complain> apiResponse = new APIResponse<>();
		try {
			apiResponse = complainFV.fValidateCreate(null, complain, null);
			if (Boolean.FALSE.equals(apiResponse.getValidationSuccess())) {
				return apiResponse;
			}
			apiResponse = complainBV.bValidateCreate(null, complain, null);
			if (Boolean.FALSE.equals(apiResponse.getProcessingSuccess())) {
				return apiResponse;
			}
			complain = complainService.save(complain, requestorId);
			apiResponse.setResponseCode(StatusCodeEnum.COMPLAIN_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.COMPLAIN_CREATED.getDesc());
			apiResponse.setResponseObject(complain);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.COMPLAIN_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.COMPLAIN_CREATION_FAILED.getDesc());
			apiResponse.setResponseObject(complain);
		}
		return apiResponse;
	}

	@GetMapping("/view/{customerId}")
	public APIResponse<Complain> complain(@RequestHeader String requestorId,
			@PathVariable("customerId") String customerId) {

		APIResponse<Complain> apiResponse = new APIResponse<>();
		Complain complainCount = complainService.getComplainByCustomerId(customerId);
		if (complainCount == null) {
			apiResponse.setResponseCode(StatusCodeEnum.COMPLAIN_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.COMPLAIN_NOT_EXISTS.getDesc());
			return apiResponse;
		}
		Complain complain = complainService.getComplainByCustomerId(customerId);
		apiResponse.setResponseMessage("Success");
		apiResponse.setResponseObject(complain);
		return apiResponse;
	}

	@GetMapping("/viewAll/{offset}/{limit}")
	public APIResponse<Page<Complain>> allcomplain(@PathVariable("offset") int offset,
			@PathVariable("limit") int limit) {

		Page<Complain> complainLists;

		APIResponse<Page<Complain>> apiResponse = new APIResponse<>();
		try {
			complainLists = complainService.getAllComplain(offset, limit);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.ERROR_WHILE_RETREVING_DATA.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ERROR_WHILE_RETREVING_DATA.getDesc());
			return apiResponse;
		}
		if (complainLists.isEmpty()) {
			apiResponse.setResponseCode(StatusCodeEnum.COMPLAIN_NOT_EXISTS.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.COMPLAIN_NOT_EXISTS.getDesc());
			return apiResponse;
		}
		apiResponse.setResponseCode("200");
		apiResponse.setResponseMessage("Success");
		apiResponse.setResponseObject(complainLists);
		return apiResponse;
	}
}
