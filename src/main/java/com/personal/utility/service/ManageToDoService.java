package com.personal.utility.service;

import java.util.List;

import com.personal.utility.model.ToDoModel;

/**
 * Holds all the methods to manage the to do list
 * @author renjith
 *
 */
public interface ManageToDoService {
	
	/**
	 * used to add a task
	 * @param toDoModel
	 */
	public ToDoModel addTask(ToDoModel toDoModel);
	
	/**
	 * Get all the tasks for a spaecific user
	 * @param userId
	 * @return
	 */
	public List<ToDoModel> getAllTaskForUser(String userId);
	
	/**
	 * Delete a task
	 * @param taskId
	 * @return
	 */
	public boolean deleteTask(String taskId, String updatedUser);
	
	
	/**
	 * 
	 * @param toDoList
	 * @return
	 * @throws UnsupportedOperationException
	 */
	public List<ToDoModel> updateTask(List<ToDoModel> toDoList)throws UnsupportedOperationException;
	
}
