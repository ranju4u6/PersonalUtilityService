package com.personal.utility.service.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.personal.utility.constants.ApplicationConstants;
import com.personal.utility.entity.User;
import com.personal.utility.entity.UserType;
import com.personal.utility.model.AddUserModel;
import com.personal.utility.model.DeleteUserModel;
import com.personal.utility.model.UserModel;
import com.personal.utility.model.UserTypeModel;
import com.personal.utility.repository.UserRepository;
import com.personal.utility.repository.UserTypeRepository;
import com.personal.utility.service.ManageUserService;


/**
 * Implementation class for ManageUserService
 * @author renjith
 *
 */
@Service
public class ManageUserServiceImpl implements ManageUserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserTypeRepository userTypeRepo;

	@Override
	@Transactional
	public UserModel login(String userName, String password) {
		User user = userRepo.findByUserName(userName);
		
		String decodedPassword = new String(Base64.getDecoder().decode(password));
		String storedPassword = new String(Base64.getDecoder().decode(user.getPassword()));
		
		//only active users are selected
		if(user !=null && decodedPassword.equals(storedPassword) && user.isActive()) {
			setSessionId(user);
			
			UserModel userModel = new UserModel();
			userModel.setUserId(user.getId());
			userModel.setUserName(user.getUserName());
			userModel.setSessionId(user.getSessionId());
			
			UserTypeModel userType = new UserTypeModel();
			userType.setUserTypeId(user.getUserType().getId());
			userType.setUserType(user.getUserType().getUserType());
			userModel.setUserType(userType);
			
			return userModel;
		}
		
		return null;
	}
	
	/**
	 * Replace session id on each login
	 * @param user
	 */
	@Transactional(propagation=Propagation.MANDATORY)
	private void setSessionId(User user) {
		String sessionId = UUID.randomUUID().toString();
		user.setSessionId(sessionId);
		
		userRepo.save(user);
	}
	
	@Transactional(readOnly=true)
	public UserModel getUser(String userId) {
		User user = userRepo.getOne(userId);
		UserModel userModel = new UserModel();
		userModel.setSessionId(user.getSessionId());
		userModel.setUserId(user.getId());
		userModel.setUserName(user.getUserName());
		
		UserTypeModel userType = new UserTypeModel();
		userType.setUserTypeId(user.getUserType().getId());
		userType.setUserType(user.getUserType().getUserType());
		userModel.setUserType(userType);
		
		return userModel;
	}

	@Override
	@Transactional
	public UserModel addUser(AddUserModel addUserModel) {
		User user = userRepo.findByUserName(addUserModel.getUserModel().getUserName().trim());
		if(user != null) {
			throw new RuntimeException("User Name already taken");
		}
		
		user = new User();
		user.setUserName(addUserModel.getUserModel().getUserName().trim());
		user.setActive(true);
		user.setPassword(addUserModel.getUserModel().getPassword());
		addUserModel.getUserModel().setPassword(ApplicationConstants.EMPTY_STRING);
		user.setUpdatedBy(addUserModel.getUpdatedBy());
		user.setUpdatedTime(new Date());
		UserType userType = userTypeRepo.getOne(addUserModel.getUserModel().getUserType().getUserTypeId());
		user.setUserType(userType);
		
		userRepo.save(user);
		
		addUserModel.getUserModel().setUserId(user.getId());

		
		return addUserModel.getUserModel();
	}

	@Override
	public boolean deleteUser(DeleteUserModel deleteUserModel) {
		User user = userRepo.getOne(deleteUserModel.getUserModel().getUserId());
		user.setActive(false);;
		
		userRepo.save(user);
		return true;
	}

	@Override
	public List<UserModel> getAllUsers() {
		List<UserModel> userList = this.userRepo.findAll().stream().filter(User::isActive).
				map(user ->{
					UserModel userModel = new UserModel();
					userModel.setUserId(user.getId());
					userModel.setUserName(user.getUserName());
					
					UserTypeModel userType = new UserTypeModel();
					userType.setUserTypeId(user.getUserType().getId());
					userType.setUserType(user.getUserType().getUserType());
					userModel.setUserType(userType);
					return userModel;
				}).collect(Collectors.toCollection(ArrayList<UserModel>::new));
		return userList;
	}

	@Override
	public List<UserTypeModel> getAllUserTypes() {
		List<UserTypeModel> userTypeList = userTypeRepo.findAll().stream()
				.map(userType ->{
					UserTypeModel userTypeModel = new UserTypeModel();
					userTypeModel.setUserTypeId(userType.getId());
					userTypeModel.setUserType(userType.getUserType());
					return userTypeModel;
				}).collect(Collectors.toCollection(ArrayList<UserTypeModel>::new));
		
		return userTypeList;
	}

}
