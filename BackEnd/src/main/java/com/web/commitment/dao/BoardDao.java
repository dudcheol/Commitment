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
	
	@Query(value = "select distinct * from sns s, commit c where UPPER(s.content) like UPPER(:keyword) and c.open = 1", nativeQuery = true)
	Page<Board> findByContentContainingIgnoreCase(@Param("keyword") String keyword, Pageable pageable);

	@Query(value = "select distinct * from sns s, commit c where UPPER(s.title) like UPPER(:keyword) and c.open = 1", nativeQuery = true)
	Page<Board> findByTitleContainingIgnoreCase(@Param("keyword") String keyword, Pageable pageable);


	// 랭킹관련
	@Query(value = "select sns.user_email email, rank() over (order by count(*) desc) ranking, count(*) cnt from sns "
			+ "group by sns.user_email", nativeQuery = true)
	List<Ranking> boardRanking();
	
	@Query(value = "select * from sns s,commit c where c.id=s.commit_id and s.user_email=:email  by s.created_at desc", nativeQuery = true)
	Page<Board> findByEmail(String email, Pageable pageable);

	@Query(value = "select * from sns s,commit c where c.id=s.commit_id and c.open=1 and s.user_email=:email  by s.created_at desc", nativeQuery = true)
	Page<Board> findAllByEmail(@Param("email") String email, Pageable pageable);

	@Query(value = "select * from sns s,commit c where c.id=s.commit_id and c.open=1  by s.created_at desc", nativeQuery = true)
	Page<Board> findAllByCommitId(Pageable pageable);

	@Query(value = "SELECT s.*,c.* FROM `commit` c , `sns` s where c.id=s.commit_id and c.open=1 and ( 6371 * acos( cos( radians(:curlat) ) * cos( radians( `lat` ) ) *"
			+ " cos( radians( `lng` ) - radians(:curlng) ) + sin( radians(:curlat) ) * sin( radians( `lat` ) ) ) )<=:radius order by s.created_at desc", nativeQuery = true)
	Page<Board> radiusCommitId(@Param("curlat") String curlat, @Param("curlng") String curlng,
			@Param("radius") Integer radius, Pageable pageable);

	@Query(value = "select * from sns s,commit c "
			+ "where s.user_email in (select f.follow_to from follow f where f.follow_from=:email) "
			+ "and c.id=s.commit_id and c.open=1 "
			+ "order by s.created_at desc", nativeQuery = true)	
	Page<Board> findBoardByEmailSort(@Param("email")String email,Pageable pageable);

	@Query(value = "select * from sns s,commit c where c.id=s.commit_id and c.open=1 "
			+ "and lower(s.user_email) like :keyword order by s.created_at desc", nativeQuery = true)
	Page<Board> findByEmailContainingIgnoreCase(@Param("keyword") String keyword, Pageable pageable);
	

	@Query(value = "select * from sns s,commit c where c.id=s.commit_id and c.open=1 "
			+ "and (lower(s.title) like :keyword or lower(s.content) like :keyword )order by s.created_at desc", nativeQuery = true)
	Page<Board> findByTitleandContent(@Param("keyword")String keyword, Pageable pageable);


	@Query(value = "select * from sns s,commit c "
			+ "where (s.user_email in (select f.follow_to from follow f where f.follow_from=:email) or s.user_email=:email)"
			+ "and c.id=s.commit_id and c.open=1 "
			+ "order by s.created_at desc", nativeQuery = true)	
	Page<Board> findtotalByEmail(@Param("email")String email, Pageable pageable);

}
