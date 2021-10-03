package com.personal.utility.model;
/**
 * UI Model for deleting a task.
 * It should be in sync with the data coming from FE
 * @author renjith
 *
 */
public class DeleteTaskModel {
	
	private String sessionId;
	private ToDoModel task;
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public ToDoModel getTask() {
		return task;
	}
	public void setTask(ToDoModel task) {
		this.task = task;
	}
	@Override
	public String toString() {
		return "DeleteTaskModel [sessionId=" + sessionId + ", task=" + task + "]";
	}

	

}
