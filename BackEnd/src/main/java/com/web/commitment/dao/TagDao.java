package com.web.commitment.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Board;
import com.web.commitment.dto.Tag;

@Repository
public interface TagDao extends JpaRepository<Tag, String> {

	List<Tag> findBySnsId(String sns_id);

	List<Tag> findAllBySnsId(String id);

	Collection<Tag> findByContentContainingIgnoreCase(String keyword);

}