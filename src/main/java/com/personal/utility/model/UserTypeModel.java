package com.personal.utility.model;

/**
 * UI Model class that represents user type
 * @author renjith
 *
 */
public class UserTypeModel {
	
	private String userTypeId;
	private String userType;
	
	public String getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "UserTypeModel [userTypeId=" + userTypeId + ", userType=" + userType + "]";
	}
	

}
