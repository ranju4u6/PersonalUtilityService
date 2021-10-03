package com.personal.utility.model;

/**
 * UI Model for adding a new task.
 * It should be in sync with the data coming from FE
 * @author renjith
 *
 */
public class AddTaskModel {
	
	private String sessionId;
	private ToDoModel newTask;
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public ToDoModel getNewTask() {
		return newTask;
	}
	public void setNewTask(ToDoModel newTask) {
		this.newTask = newTask;
	}
	@Override
	public String toString() {
		return "AddTaskModel [sessionId=" + sessionId + ", newTask=" + newTask + "]";
	}
	

}
