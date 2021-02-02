package com.web.commitment.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Board;
import com.web.commitment.dto.Commit;
import com.web.commitment.dto.Ranking;

@Repository
public interface BoardDao extends JpaRepository<Board, String> {

	List<Board> findAllByCommitId(String id);

	List<Board> findBoardByEmail(String to);

	Collection<Board> findByContentContainingIgnoreCase(String keyword);

	List<Board> findByTitleContainingIgnoreCase(String keyword);

	Collection<Board> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String keyword, String keyword2);

	Collection<Board> findByEmailContainingIgnoreCase(String keyword);

	// 랭킹관련
	@Query(value = "select sns.user_email email, rank() over (order by count(*) desc) ranking, count(*) cnt from sns "
			+ "group by sns.user_email", nativeQuery = true)
	List<Ranking> boardRanking();

	Page<Board> findByEmail(String email, Pageable pageable);

	@Query(value = "select * from sns s,commit c where c.open=0 and s.user_email=:email", nativeQuery = true)
	Page<Board> findAllByEmail(@Param("email") String email, Pageable pageable);

}