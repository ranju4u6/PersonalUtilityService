package com.personal.utility.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.utility.entity.ToDo;
import com.personal.utility.entity.User;

/**
 * Spring data repository class for ToDo Entity
 * @author renjith
 *
 */

public interface ToDoRepository extends JpaRepository<ToDo, String> {
	public List<ToDo> findByUserId(User userId);
}
