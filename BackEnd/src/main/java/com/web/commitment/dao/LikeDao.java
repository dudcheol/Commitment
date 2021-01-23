package com.web.commitment.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Like;

@Repository
public interface LikeDao extends JpaRepository<Like, String> {

	Optional<Like> findByEmailAndSnsId(String email, int snsId);

}