package com.web.commitment.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.FollowId;
import com.web.commitment.dto.User;

@Repository
public interface FollowIdDao extends JpaRepository<FollowId, String> {

}
