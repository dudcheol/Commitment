package com.web.commitment.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Commit;

@Repository
public interface CommitDao extends JpaRepository<Commit, String> {

	List<Commit> findAllByEmail(String email);

	Optional<Commit> findByLatAndLng(String lat, String lng);
	
}
