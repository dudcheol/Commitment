package com.web.commitment.dao;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Board;
import com.web.commitment.dto.Ranking;

@Repository
public interface BoardDao extends JpaRepository<Board, String> {

	List<Board> findAllByCommitId(String id);

	List<Board> findBoardByEmail(String to);

	Collection<Board> findByContentContainingIgnoreCase(String keyword);

	List<Board> findByTitleContainingIgnoreCase(String keyword);

	Collection<Board> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String keyword, String keyword2);

	// 랭킹관련
	@Query(value = "select commit.user_email email, rank() over (order by count(*) desc) ranking, count(*) cnt from board "
			+ "group by board.user_email", nativeQuery = true)
	List<Ranking> boardRanking();
}