package com.web.commitment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.User;


@Repository
public interface UserDao extends JpaRepository<User, String> {

	User getUserByEmail(String email);

	User findUserByEmail(String email);
	
	
}
