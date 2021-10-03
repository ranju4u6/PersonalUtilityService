package com.personal.utility.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.utility.model.AddTaskModel;
import com.personal.utility.model.DeleteTaskModel;
import com.personal.utility.model.ToDoModel;
import com.personal.utility.service.ManageToDoService;
import com.personal.utility.service.ManageUserService;

/**
 * Handles all requests for Task/To Do.
 * All requests require to pass the session id.
 * @author renjith
 *
 */

@RestController
public class TaskController {
	
	Logger log = LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	private ManageToDoService toDoService;
	
	@Autowired
	private ManageUserService userService;
	
	/**
	 * Handles the task addition
	 * @param addTakModel
	 * @return
	 */
	@PostMapping("api/addTask")
	public ResponseEntity<ToDoModel> addTask(@RequestBody AddTaskModel addTakModel){
		System.out.println(addTakModel);
		log.info("Receoved add task request: {}", addTakModel);
		String orgSessionId = userService.getUser(addTakModel.getNewTask().getUserId()).getSessionId();
		String receivedSessionId = addTakModel.getSessionId();
		
		if(! receivedSessionId.equals(orgSessionId)) {
			return new ResponseEntity<ToDoModel>(HttpStatus.UNAUTHORIZED);
		}
		
		try {
			ToDoModel todoModel = toDoService.addTask(addTakModel.getNewTask());
			log.info("Added task: {}", todoModel);
			return new ResponseEntity<ToDoModel>(todoModel, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<ToDoModel>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * handles the delete task request
	 * @param deleteTaskModel
	 * @return
	 */
	@PostMapping("api/deleteTask")
	public ResponseEntity<Boolean> deleteTask(@RequestBody DeleteTaskModel deleteTaskModel){
		log.info("Received delete task request: {}", deleteTaskModel);
		String orgSessionId = userService.getUser(deleteTaskModel.getTask().getUserId()).getSessionId();
		String receivedSessionId = deleteTaskModel.getSessionId();
		
		if(! receivedSessionId.equals(orgSessionId)) {
			return new ResponseEntity<Boolean>(HttpStatus.UNAUTHORIZED);
		}
		
		try {
			Boolean status = toDoService.deleteTask(deleteTaskModel.getTask().getId(), deleteTaskModel.getTask().getUserId());
			log.info("deleted task: {}", deleteTaskModel);
			return new ResponseEntity<Boolean>(status, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * Handles the request for getting all tasks of a user
	 * @param userId
	 * @param sessionId
	 * @return
	 */
	@GetMapping("api/getAllTask")
	public ResponseEntity<List<ToDoModel>> getAllTasks(@RequestParam("userId") String userId, 
			@RequestParam("sessionId") String sessionId){
		log.info("Received get all task request");
		String orgSessionId = userService.getUser(userId).getSessionId();
		String receivedSessionId = sessionId;
		
		if(! receivedSessionId.equals(orgSessionId)) {
			return new ResponseEntity<List<ToDoModel>>(HttpStatus.UNAUTHORIZED);
		}
		
		try {
			List<ToDoModel> toDoList = toDoService.getAllTaskForUser(userId);
			log.info("Total tasked fetched: {}"+toDoList.size());
			return new ResponseEntity<List<ToDoModel>>(toDoList, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<ToDoModel>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
