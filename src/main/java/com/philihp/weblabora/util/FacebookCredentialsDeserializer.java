package com.philihp.weblabora.util;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class FacebookCredentialsDeserializer implements JsonDeserializer<FacebookCredentials> {

	@Override
	public FacebookCredentials deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext ctx)
			throws JsonParseException {
		FacebookCredentials credentials = new FacebookCredentials();
		JsonObject obj = json.getAsJsonObject();
		for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
			switch (entry.getKey()) {
			case "name":
				credentials.setName(entry.getValue().getAsString());
				break;
			case "id":
				credentials.setFacebookId(entry.getValue().getAsString());
				break;
			case "first_name":
				credentials.setFirstName(entry.getValue().getAsString());
				break;
			case "link":
				credentials.setLink(entry.getValue().getAsString());
				break;
			case "username":
				credentials.setUsername(entry.getValue().getAsString());
				break;
			}
		}
		return credentials;
	}
}
