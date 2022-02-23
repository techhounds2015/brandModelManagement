package com.kashitkalaecom.brandmodelmgmt.payment.razorpay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kashitkalaecom.brandmodelmgmt.apiresponse.APIResponse;
import com.kashitkalaecom.brandmodelmgmt.config.RazorPayClientConfig;
import com.kashitkalaecom.brandmodelmgmt.emuns.StatusCodeEnum;
import com.kashitkalaecom.brandmodelmgmt.models.User;
import com.kashitkalaecom.brandmodelmgmt.service.OrderService;
import com.kashitkalaecom.brandmodelmgmt.utilities.RazorpayUtility;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/api/v1/razorPay")
public class RazorpayController {
	
	private RazorpayClient client;
	 
    private RazorPayClientConfig razorPayClientConfig;
 
    @Autowired
    private OrderService orderService;
 
    @Autowired
    public RazorpayController(RazorPayClientConfig razorpayClientConfig) throws RazorpayException {
        this.razorPayClientConfig = razorpayClientConfig;
        this.client = new RazorpayClient(razorpayClientConfig.getKey(), razorpayClientConfig.getSecret());
    }
	
	@PostMapping("/create/orders")
	public APIResponse<RazorpayOrderResponse> createOrder(@RequestHeader String requestorId, @RequestBody RazorpayOrderRequest orderRequest) {
		APIResponse<RazorpayOrderResponse> apiResponse = new APIResponse<>();
		
		RazorpayOrderResponse  razorPay = new RazorpayOrderResponse();

		try {
			 // as paise (in case of INR)
            String amountInPaise = RazorpayUtility.convertRupeeToPaise(orderRequest.getAmount());
            // Create an order in RazorPay and get the order id
            Order order = RazorpayUtility.createRazorPayOrder(amountInPaise,client);
            razorPay = RazorpayUtility.getOrderResponse((String) order.get("id"), amountInPaise,razorPayClientConfig.getKey());
           
            
            // Save order in the database
           // orderService.saveOrder(razorPay.getRazorpayOrderId(), orderRequest.getUserId());
						
			apiResponse.setResponseCode(StatusCodeEnum.ORDER_CREATED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ORDER_CREATED.getDesc());
			apiResponse.setResponseObject(razorPay);
		} catch (Exception e) {
			apiResponse.setResponseCode(StatusCodeEnum.ORDER_CREATION_FAILED.getCode());
			apiResponse.setResponseMessage(StatusCodeEnum.ORDER_CREATION_FAILED.getDesc());
		}

		return apiResponse;
	}


}
