package com.web.commitment.dao;


import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Board;

@Repository
public interface BoardDao extends JpaRepository<Board, String> {

	List<Board> findAllByCommitId(String id);

	List<Board> findBoardByEmail(String to);
	
	Collection<Board> findByContentContainingIgnoreCase(String keyword);

	List<Board> findByTitleContainingIgnoreCase(String keyword);

	Collection<Board> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String keyword, String keyword2);

	Collection<Board> findByEmailContainingIgnoreCase(String keyword);

}