package com.greatlearning.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.employee.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User getUserByUsername(String username);

}
