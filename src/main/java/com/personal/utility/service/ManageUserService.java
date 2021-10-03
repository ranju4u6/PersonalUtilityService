package com.personal.utility.service;

import java.util.List;

import com.personal.utility.model.AddUserModel;
import com.personal.utility.model.DeleteUserModel;
import com.personal.utility.model.UserModel;
import com.personal.utility.model.UserTypeModel;

/**
 * Class to service the user related requests
 * @author renjith
 *
 */
public interface ManageUserService {
	
	/**
	 * Method to service the login request
	 * @param userName
	 * @param password
	 * @return
	 */
	UserModel login(String userName, String password);
	
	/**
	 * Retrieves the user with the userId
	 * @param userId
	 * @return
	 */
	UserModel getUser(String userId);
	
	/**
	 * Method to add a new user
	 * @param addUserModel
	 * @return
	 */
	UserModel addUser(AddUserModel addUserModel);
	
	/**
	 * Method used to delete a user
	 * @param deleteUserModel
	 * @return
	 */
	boolean deleteUser(DeleteUserModel deleteUserModel);
	
	/**
	 * Retrieve all Active users
	 * @return
	 */
	List<UserModel> getAllUsers();
	
	/**
	 * Retrieve all the user types
	 * @return
	 */
	List<UserTypeModel> getAllUserTypes();

}
