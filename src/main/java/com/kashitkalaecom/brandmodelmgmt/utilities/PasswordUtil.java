package com.kashitkalaecom.brandmodelmgmt.utilities;


import java.util.Base64;

import org.apache.commons.lang3.RandomStringUtils;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

import com.kashitkalaecom.brandmodelmgmt.models.User;
public class PasswordUtil {
	
	public static String encryptedPassword(String secret, String salt) {

		if (secret == null) {
			return "";
		}
		String toEncrypt = salt + secret;

		SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
		byte[] digest = digestSHA3.digest(toEncrypt.getBytes());

		String encryptedPassword = Hex.toHexString(digest);

		return encryptedPassword;
	}

	public static User generatePassword(User user) {

		String password = user.getPassword();
		if (null != password) {			
			String salt = RandomStringUtils.randomAlphanumeric(64);
			password = encryptedPassword(base64ToString(password), salt);
			user.setPassword(stringToBase64(password));
			user.setSalt(salt);
		}
		return user;

	}
	
	public static String stringToBase64(String text) {
		 Base64.Encoder encoder = Base64.getEncoder();  
		 return  encoder.encodeToString(text.getBytes());  
	}
	
	public static String base64ToString(String text) {
		 Base64.Decoder decoder = Base64.getDecoder();  
		 return  new String(decoder.decode(text));  
	}
	
	


}
