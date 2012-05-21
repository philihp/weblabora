package com.philihp.weblabora.util;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class FacebookSignedRequestDeserializer implements
		JsonDeserializer<FacebookSignedRequest> {

	@Override
	public FacebookSignedRequest deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext ctx) throws JsonParseException {
		FacebookSignedRequest fsr = new FacebookSignedRequest();
		JsonObject obj = json.getAsJsonObject();
		for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
			if("algorithm".equals(entry.getKey()))
				fsr.setAlgorithm(entry.getValue().getAsString());
			else if("oauth_token".equals(entry.getKey()))
				fsr.setOauthToken(entry.getValue().getAsString());
			else if("user_id".equals(entry.getKey()))
				fsr.setUserId(entry.getValue().getAsString());
			
		}
		return fsr;
	}
}
