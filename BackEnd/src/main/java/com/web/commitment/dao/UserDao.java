package com.web.commitment.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {

	Optional<User> findUserByEmail(String email);

	int countByEmail(String email);

	Optional<User> findByLatAndLng(String lat, String lng);
	
}
