package com.kashitkalaecom.brandmodelmgmt.utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;


import org.json.JSONObject;

import com.kashitkalaecom.brandmodelmgmt.payment.razorpay.RazorpayOrderResponse;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import java.security.SignatureException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class RazorpayUtility {
	
	private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

	public RazorpayUtility() {

	}

	public static RazorpayOrderResponse getOrderResponse(String orderId, String amountInPaise, String key) {
		RazorpayOrderResponse razorPay = new RazorpayOrderResponse();
		razorPay.setApplicationFee(amountInPaise);
		razorPay.setRazorpayOrderId(orderId);
		razorPay.setSecretKey(key);
		return razorPay;
	}

	public static Order createRazorPayOrder(String amount, RazorpayClient client) throws RazorpayException {
		JSONObject options = new JSONObject();
		options.put("amount", amount);
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		return client.Orders.create(options);
	}

	public static String convertRupeeToPaise(String paise) {
		BigDecimal b = new BigDecimal(paise);
		BigDecimal value = b.multiply(new BigDecimal("100"));
		return value.setScale(0, RoundingMode.UP).toString();
	}
	
	public static String calculateRFC2104HMAC(String data, String secret) throws java.security.SignatureException {
        String result;
        try {
 
            // get an hmac_sha256 key from the raw secret bytes
            SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), HMAC_SHA256_ALGORITHM);
 
            // get an hmac_sha256 Mac instance and initialize with the signing
            // key
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
 
            // compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(data.getBytes());
 
            // base64-encode the hmac
            result = DatatypeConverter.printHexBinary(rawHmac).toLowerCase();
 
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }

}
