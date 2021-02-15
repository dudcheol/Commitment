package com.web.commitment.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Comment;

@Repository
public interface CommentDao extends JpaRepository<Comment, String> {

	List<Comment> findBySnsId(String sns_id);

	Optional<Comment> findById(String parent);

	Object findByEmailAndSnsId(String email, String id);

//	@Query(value = "select max(id) from comment", nativeQuery = true)
//	String findByLastComment();

	Page<Comment> findBySnsIdOrderByCreatedAt(String sns_id, Pageable pageable);

	@Query(value = "select max(c.id) from comment c where c.user_email=:email", nativeQuery = true)
	String findLastComment(@Param("email") String email);
	
}