package com.web.commitment.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Tag;

@Repository
public interface TagDao extends JpaRepository<Tag, String> {

	List<Tag> findBySnsId(String sns_id);

	List<Tag> findAllBySnsId(String id);

	@Query(value = "select distinct content from tag where content like :keyword", nativeQuery = true)
	Page<String[]> findByContentContainingIgnoreCase(@Param("keyword") String keyword, Pageable pageable);

	@Query(value = "select distinct sns.* from sns, tag where tag.content=:keyword", nativeQuery = true)
	Page<Object> findByContentContaining(String keyword, Pageable pageable);
}