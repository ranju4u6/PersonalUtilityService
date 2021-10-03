package com.personal.utility.model;

/**
 * UI Model for deleting a user.
 * It should be in sync with the data coming from FE
 * @author renjith
 *
 */
public class DeleteUserModel {

	private String sessionId;
	private String updatedBy;
	private UserModel userModel;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	@Override
	public String toString() {
		return "DeleteUserModel [sessionId=" + sessionId + ", updatedBy=" + updatedBy + ", userModel=" + userModel + "]";
	}

}
