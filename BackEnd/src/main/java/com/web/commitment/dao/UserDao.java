package com.web.commitment.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {

	Optional<User> findUserByEmail(String email);

	int countByEmail(String email);
	
}
