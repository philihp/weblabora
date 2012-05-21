package com.philihp.weblabora.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class FacebookUtil {

	public static boolean isValidSignedRequest(String clientSecret,
			String givenSignature, String payload) {
		Base64 decoder = new Base64(true);
		try {
			Mac mac = Mac.getInstance("HMACSHA256");
			mac.init(new SecretKeySpec(clientSecret.getBytes(), mac.getAlgorithm()));
			byte[] calculatedSignature = mac.doFinal(payload.getBytes());
			return Arrays.equals(decoder.decode(givenSignature), calculatedSignature);
		}
		catch(InvalidKeyException e) {
			throw new RuntimeException(e);
		}
		catch(NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
