package com.web.commitment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Badge;

@Repository
public interface BadgeDao extends JpaRepository<Badge, String> {
	@Query(value = "select * from badge ", nativeQuery = true)
	Badge badgeCnt();


	Badge findBadgeByUserEmail(String email);
}
