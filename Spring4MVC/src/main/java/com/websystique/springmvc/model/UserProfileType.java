package com.websystique.springmvc.model;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
	user("user"),
	admin("admin");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
