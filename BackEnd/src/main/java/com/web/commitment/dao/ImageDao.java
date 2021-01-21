package com.web.commitment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Image;

@Repository
public interface ImageDao extends JpaRepository<Image, String> {

}