package com.web.commitment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.UserBadge;

@Repository
public interface UserBadgeDao extends JpaRepository<UserBadge, String> {


	List<UserBadge> findAllByEmail(String email);

	List<UserBadge> findAllUserbadgeByEmail(String email);

	int countByEmail(String email);
	
}
