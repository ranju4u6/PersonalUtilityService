package com.personal.utility.model;

/**
 * UI Model representing the task.
 * It should be in sync with the data coming from FE
 * @author renjith
 *
 */
public class ToDoModel {
	
	private String id;
	private String task;
	private String targetDate;
	private String userId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "ToDoModel [id=" + id + ", task=" + task + ", targetDate=" + targetDate + ", userId=" + userId + "]";
	}
	
	

}
