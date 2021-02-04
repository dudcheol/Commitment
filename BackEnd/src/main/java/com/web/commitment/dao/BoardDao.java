package com.web.commitment.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Board;
import com.web.commitment.dto.Ranking;

@Repository
public interface BoardDao extends JpaRepository<Board, String> {

	List<Board> findAllByCommitId(String id);

	List<Board> findBoardByEmail(String to);

	Page<Board> findByContentContainingIgnoreCase(String keyword, Pageable pageable);

	Page<Board> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

	Page<Board> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String keyword, String keyword2, Pageable pageable);

	Page<Board> findByEmailContainingIgnoreCase(String keyword, Pageable pageable);

	// 랭킹관련
	@Query(value = "select sns.user_email email, rank() over (order by count(*) desc) ranking, count(*) cnt from sns "
			+ "group by sns.user_email", nativeQuery = true)
	List<Ranking> boardRanking();

	Page<Board> findByEmail(String email, Pageable pageable);

	@Query(value = "select * from sns s,commit c where c.id=s.commit_id and c.open=1 and s.user_email=:email", nativeQuery = true)
	Page<Board> findAllByEmail(@Param("email") String email, Pageable pageable);

	@Query(value = "select * from sns s,commit c where c.id=s.commit_id and c.open=1", nativeQuery = true)
	Page<Board> findAllByCommitId(Pageable pageable);


	@Query(value = "SELECT s.*,c.* FROM `commit` c , `sns` s where c.id=s.commit_id and c.open=1 and ( 6371 * acos( cos( radians(:curlat) ) * cos( radians( `lat` ) ) *"
			+ " cos( radians( `lng` ) - radians(:curlng) ) + sin( radians(:curlat) ) * sin( radians( `lat` ) ) ) )<=:radius", nativeQuery = true)
	Page<Board> radiusCommitId(@Param("curlat") String curlat, @Param("curlng") String curlng,
			@Param("radius") Integer radius, Pageable pageable);

}
