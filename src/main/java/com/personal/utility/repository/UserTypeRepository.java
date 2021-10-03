package com.personal.utility.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.utility.entity.UserType;

/**
 * Spring data repository class for UserType Entity
 * @author renjith
 *
 */
public interface UserTypeRepository extends JpaRepository<UserType, String> {

}
