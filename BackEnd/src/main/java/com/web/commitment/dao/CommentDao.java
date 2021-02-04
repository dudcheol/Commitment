package com.web.commitment.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Board;
import com.web.commitment.dto.Comment;
import com.web.commitment.dto.User;

@Repository
public interface CommentDao extends JpaRepository<Comment, String> {

	List<Comment> findBySnsId(String sns_id);

	Optional<Comment> findById(String parent);

	Object findByEmailAndSnsId(String email, String id);

	@Query(value = "select max(id) from comment", nativeQuery = true)
	Comment findByLastComment();
}