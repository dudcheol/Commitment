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

//	@Query(value = "select * from sns where user_email=:email order by created_at desc", nativeQuery = true)
//	List<Board> findBoardByEmail(@Param("email") String email);
	
	@Query(value = "select * from sns s, commit c where s.commit_id=c.id and UPPER(s.content) like UPPER(:keyword) and c.open = 1", nativeQuery = true)
	Page<Board> findByContentContainingIgnoreCase(@Param("keyword") String keyword, Pageable pageable);
	//제목으로 검색
	@Query(value = "select * from sns s, commit c where  c.id=s.commit_id and UPPER(s.title) like UPPER(:keyword) and c.open = 1", nativeQuery = true)
	Page<Board> findByTitleContainingIgnoreCase(@Param("keyword") String keyword, Pageable pageable);

	// 랭킹관련
	@Query(value = "select sns.user_email email, rank() over (order by count(*) desc) ranking, count(*) cnt, profile.file_path profile" + 
			" from sns  left outer join profile on sns.user_email=profile.user_email group by sns.user_email", nativeQuery = true)
	List<Ranking> boardRanking();
	
	@Query(value = "select * from sns s,commit c where c.id=s.commit_id and s.user_email=:email order by s.created_at desc", nativeQuery = true)
	Page<Board> findByEmail(String email, Pageable pageable);

	@Query(value = "select * from sns s, commit c where c.id=s.commit_id and c.open=1 and s.user_email=:email order by s.created_at desc", nativeQuery = true)
	Page<Board> findAllByEmail(@Param("email") String email, Pageable pageable);

	@Query(value = "select * from sns s,commit c where c.id=s.commit_id and c.open=1 order by s.created_at desc", nativeQuery = true)
	Page<Board> findAllByCommitId(Pageable pageable);

	////////////////////////////
	@Query(value = "SELECT * FROM sns s, commit c where c.id=s.commit_id and c.open=1 and ( 6371 * acos( cos( radians(:curlat) ) * cos( radians( c.lat ) ) *"
			+ " cos( radians( c.lng ) - radians(:curlng) ) + sin( radians(:curlat) ) * sin( radians( c.lat ) ) ) )<=:radius order by s.created_at desc", nativeQuery = true)
	Page<Board> radiusCommitId(@Param("curlat") String curlat, @Param("curlng") String curlng,
			@Param("radius") double radius, Pageable pageable);
	
	@Query(value = "select * from sns s,commit c "
			+ "where s.user_email in (select f.follow_to from follow f where f.follow_from=:email) "
			+ "and c.id=s.commit_id and c.open=1 "
			+ "order by s.created_at desc", nativeQuery = true)	
	Page<Board> findBoardByEmailSort(@Param("email")String email,Pageable pageable);

	//글쓴이로 검색
	@Query(value = "select * from sns s, commit c "
			+ "where c.id=s.commit_id and c.open=1 "
			+ "and lower(s.user_email) like :keyword order by s.created_at desc", nativeQuery = true)
	Page<Board> findByEmailContainingIgnoreCase(@Param("keyword") String keyword, Pageable pageable);
	
	//제목과 내용으로 검색
	@Query(value = "select * from sns s, commit c where c.open=1 "
			+ "and c.id=s.commit_id  "
			+ "and (lower(s.title) like :keyword or lower(s.content) like :keyword ) order by s.created_at desc", nativeQuery = true)
	Page<Board> findByTitleandContent(@Param("keyword")String keyword, Pageable pageable);

	
	@Query(value = "select * from sns s, commit c "
			+ "where (s.user_email in (select f.follow_to from follow f where f.follow_from=:email) or s.user_email=:email)"
			+ "and c.id=s.commit_id and c.open=1 "
			+ "order by s.created_at desc", nativeQuery = true)	
	Page<Board> findtotalByEmail(@Param("email") String email, Pageable pageable);


	@Query(value = "select * from sns s, commit c "
			+ "where c.id=s.commit_id and c.open=1 "
			+ "and c.region_name=:region and c.local_x=:x and c.local_y=:y order by s.created_at desc", nativeQuery = true)
	Page<Board> locationsns(@Param("x") String x,@Param("y") String y,@Param("region") String region,Pageable pageable);

	@Query(value = "select * from sns s, commit c "
			+ "where c.id=s.commit_id and c.open=1 "
			+ "and c.local_x=:x and c.local_y=:y order by s.created_at desc", nativeQuery = true)
	Page<Board> nationalsns(@Param("x") String x,@Param("y") String y, Pageable pageable);
}
