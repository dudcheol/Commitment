package com.web.commitment.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Image;

@Repository
public interface ImageDao extends JpaRepository<Image, String> {

	List<Image> findAllBySnsId(String sns_id);

	Optional<Image> findByFileName(String file_name);

	void deleteBySnsId(String sns_id);

}