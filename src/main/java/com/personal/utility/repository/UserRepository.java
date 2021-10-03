package com.personal.utility.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.utility.entity.User;


/**
 * Spring data repository class for User Entity
 * @author renjith
 *
 */
public interface UserRepository extends JpaRepository<User, String> {
	public User findByUserName(String userName);
}
