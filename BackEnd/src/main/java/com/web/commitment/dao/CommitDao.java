package com.web.commitment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Commit;

@Repository
public interface CommitDao extends JpaRepository<Commit, String> {
	
}
