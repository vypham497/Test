package com.example.project_dbreathuit_app.model;

import com.google.gson.annotations.SerializedName;

public class UserResponseModel {

	@SerializedName("firstName")
	private String firstName;

	@SerializedName("lastName")
	private String lastName;

	@SerializedName("realmId")
	private String realmId;

	@SerializedName("realm")
	private String realm;

	@SerializedName("serviceAccount")
	private boolean serviceAccount;

	@SerializedName("id")
	private String id;

	@SerializedName("createdOn")
	private long createdOn;

	@SerializedName("email")
	private String email;

	@SerializedName("enabled")
	private boolean enabled;

	@SerializedName("username")
	private String username;

	public String getFirstName(){
		return firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public String getRealmId(){
		return realmId;
	}

	public String getRealm(){
		return realm;
	}

	public boolean isServiceAccount(){
		return serviceAccount;
	}

	public String getId(){
		return id;
	}

	public long getCreatedOn(){
		return createdOn;
	}

	public String getEmail(){
		return email;
	}

	public boolean isEnabled(){
		return enabled;
	}

	public String getUsername(){
		return username;
	}
}