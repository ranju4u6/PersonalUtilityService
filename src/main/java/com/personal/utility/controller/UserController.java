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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.utility.model.AddUserModel;
import com.personal.utility.model.DeleteUserModel;
import com.personal.utility.model.UserModel;
import com.personal.utility.model.UserTypeModel;
import com.personal.utility.service.ManageUserService;

/**
 * Controller class that handles the login request or user related operations
 * @author renjith
 *
 */

@RestController
public class UserController {
	
	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private ManageUserService userService;

	/**
	 * Handles the login request
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("api/userLogin")
	public ResponseEntity<UserModel> login(@RequestParam(name="userName") String userName,
			@RequestParam(name="password") String password){
		log.info("Received login request");
		UserModel user = this.userService.login(userName, password);
		
		if(user != null) return new ResponseEntity<UserModel>(user, HttpStatus.OK);
		else {
			log.info("Un Authorized User {}", userName);
			return new ResponseEntity<UserModel>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	/**
	 * Handles the add user request
	 * @param addUserModel
	 * @return
	 */
	@PostMapping("api/addUser")
	public ResponseEntity<UserModel> addUser(@RequestBody AddUserModel addUserModel){
		log.info("Received add user request");
		
		String orgSessionId = userService.getUser(addUserModel.getUpdatedBy()).getSessionId();
		String receivedSessionId = addUserModel.getSessionId();
		
		if(! receivedSessionId.equals(orgSessionId)) {
			return new ResponseEntity<UserModel>(HttpStatus.UNAUTHORIZED);
		}
		
		try {
			UserModel newUser = userService.addUser(addUserModel);
			log.info("Added user : {}", newUser);
			return new ResponseEntity<UserModel>(newUser, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<UserModel>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * Handles the delete user request
	 * @param deleteUserModel
	 * @return
	 */
	@PostMapping("api/deleteUser")
	public ResponseEntity<Boolean> deleteUser(@RequestBody DeleteUserModel deleteUserModel){
		log.info("Received delete user request for userId: {}", deleteUserModel.getUserModel().getUserId());
		
		String orgSessionId = userService.getUser(deleteUserModel.getUpdatedBy()).getSessionId();
		String receivedSessionId = deleteUserModel.getSessionId();
		
		if(! receivedSessionId.equals(orgSessionId)) {
			return new ResponseEntity<Boolean>(HttpStatus.UNAUTHORIZED);
		}
		
		try {
			boolean status = userService.deleteUser(deleteUserModel);
			log.info("Deleted user : {}", deleteUserModel.getUserModel());
			return new ResponseEntity<Boolean>(status, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * Used to retrieve all the active users
	 * @param sessonId
	 * @param userId
	 * @return
	 */
	@GetMapping("api/getAllUsers")
	public ResponseEntity<List<UserModel>> getAllUsers(@RequestParam("sessionId") String sessonId, @RequestParam("userId") String userId){
		log.info("Received get users request");
		
		String orgSessionId = userService.getUser(userId).getSessionId();
		String receivedSessionId = sessonId;
		
		if(! receivedSessionId.equals(orgSessionId)) {
			return new ResponseEntity<List<UserModel>>(HttpStatus.UNAUTHORIZED);
		}
		
		try {
			List<UserModel> userList = userService.getAllUsers();
			log.info("Returned User count : {}",userList.size());
			return new ResponseEntity<List<UserModel>>(userList, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<List<UserModel>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	/**
	 * Used to retrieve all user types
	 * @param sessonId
	 * @param userId
	 * @return
	 */
	@GetMapping("api/getAllUserType")
	public ResponseEntity<List<UserTypeModel>> getAllUserType(@RequestParam("sessionId") String sessonId, @RequestParam("userId") String userId){
		log.info("Received get User type request");
		
		String orgSessionId = userService.getUser(userId).getSessionId();
		String receivedSessionId = sessonId;
		
		if(! receivedSessionId.equals(orgSessionId)) {
			return new ResponseEntity<List<UserTypeModel>>(HttpStatus.UNAUTHORIZED);
		}
		
		try {
			List<UserTypeModel> userTypeList = userService.getAllUserTypes();
			log.info("Returned User Type count : {}",userTypeList.size());
			return new ResponseEntity<List<UserTypeModel>>(userTypeList, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<List<UserTypeModel>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	

}
