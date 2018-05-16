package com.qa.util;

import java.util.Properties;

import com.google.gson.Gson;

public class JSONUtil {

	private Gson gson;

	public JSONUtil() {
		this.gson = new Gson();
	}
	
	public String getAttribute(String jsonString, String attribute) {
		return gson.fromJson(jsonString, Properties.class).getProperty(attribute);
	}

	public String getJSONForObject(Object obj) {
		if(obj == null) {
			return null;
		}
		return gson.toJson(obj);
	}

	public <T> T getObjectForJSON(String jsonString, Class<T> clazz) {
		return gson.fromJson(jsonString, clazz);
	}

}