package com.philihp.weblabora.util;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class FacebookCredentialsDeserializer implements
		JsonDeserializer<FacebookCredentials> {

	@Override
	public FacebookCredentials deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext ctx) throws JsonParseException {
		FacebookCredentials credentials = new FacebookCredentials();
		JsonObject obj = json.getAsJsonObject();
		for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
			if ("name".equals(entry.getKey()))
				credentials.setName(entry.getValue().getAsString());
			else if ("id".equals(entry.getKey()))
				credentials.setFacebookId(entry.getValue().getAsString());
			else if ("first_name".equals(entry.getKey()))
				credentials.setFirstName(entry.getValue().getAsString());
			else if ("link".equals(entry.getKey()))
				credentials.setLink(entry.getValue().getAsString());
			else if ("username".equals(entry.getKey()))
				credentials.setUsername(entry.getValue().getAsString());
		}
		return credentials;
	}
}
