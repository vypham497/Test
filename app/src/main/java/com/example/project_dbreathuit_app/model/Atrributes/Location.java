package com.example.project_dbreathuit_app.model.Atrributes;

import com.google.gson.annotations.SerializedName;

public class Location{

	@SerializedName("name")
	private String name;

	@SerializedName("type")
	private String type;

	@SerializedName("value")
	private Object value;

	@SerializedName("timestamp")
	private long timestamp;

	public String getName(){
		return name;
	}

	public String getType(){
		return type;
	}

	public Object getValue(){
		return value;
	}

	public long getTimestamp(){
		return timestamp;
	}
}