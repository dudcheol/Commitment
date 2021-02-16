package com.web.commitment.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Commit;
import com.web.commitment.dto.Ranking;

@Repository
public interface CommitDao extends JpaRepository<Commit, String> {

	List<Commit> findAllByEmail(String email);

	Optional<Commit> findByLatAndLng(String lat, String lng);

	List<Commit> findAllByEmailAndRegion(String eamil, String name);

	List<Commit> findAllByOpen(int i);

	List<Commit> findAllByEmailAndNationalXAndNationalY(@Param("email") String email,@Param("x") String x,@Param("y") String y);
	
	List<Commit> findAllByEmailAndLocalXAndLocalYAndRegion(String email, String x, String y, String region);

	List<Commit> findByEmail(String email);

	// 랭킹관련

	@Query(value = "select c.user_email email, u.nickname nickname, row_number() over (order by count(*) desc) ranking, count(*) cnt"
			+ " from user u, commit c" + " where u.email=c.user_email " + " group by c.user_email"
			+ " order by ranking", nativeQuery = true)
	List<Ranking> commitRank();

	@Query(value = "select c.user_email email, u.nickname nickname ,row_number() over (order by count(*) desc) ranking, count(*) cnt from user u, commit c"
			+ " where u.email=c.user_email and c.region_name='seoul' "
			+ "group by c.user_email order by ranking", nativeQuery = true)
	List<Ranking> seoulRank();

	@Query(value = "select c.user_email email, u.nickname nickname ,row_number() over (order by count(*) desc) ranking, count(*) cnt from user u, commit c"
			+ " where u.email=c.user_email and c.region_name='gyeonggi' "
			+ "group by c.user_email order by ranking", nativeQuery = true)
	List<Ranking> gyeonggiRank();

	@Query(value = "select c.user_email email, u.nickname nickname ,row_number() over (order by count(*) desc) ranking, count(*) cnt from user u, commit c"
			+ " where u.email=c.user_email and c.region_name='gangwon' "
			+ "group by c.user_email order by ranking", nativeQuery = true)
	List<Ranking> gangwonRank();

	@Query(value = "select c.user_email email, u.nickname nickname ,row_number() over (order by count(*) desc) ranking, count(*) cnt from user u, commit c"
			+ " where u.email=c.user_email and c.region_name='gwangju' "
			+ "group by c.user_email order by ranking", nativeQuery = true)
	List<Ranking> gwangjuRank();

	@Query(value = "select c.user_email email, u.nickname nickname ,row_number() over (order by count(*) desc) ranking, count(*) cnt from user u, commit c"
			+ " where u.email=c.user_email and c.region_name='ulsan' "
			+ "group by c.user_email order by ranking", nativeQuery = true)
	List<Ranking> ulsanRank();

	@Query(value = "select c.user_email email, u.nickname nickname ,row_number() over (order by count(*) desc) ranking, count(*) cnt from user u, commit c"
			+ " where u.email=c.user_email and c.region_name='busan' "
			+ "group by c.user_email order by ranking", nativeQuery = true)
	List<Ranking> busanRank();

	// 커밋 시간제한
	@Query(value = "select * from commit "
			+ "where commit.user_email=:email and commit.region_name=:region and commit.local_x=:x and commit.local_y=:y"
			+ " AND created_at between DATE_ADD(NOW(),INTERVAL -1 HOUR ) AND NOW()", nativeQuery = true)
	List<Commit> timeCheck(@Param("email") String email, @Param("x") int x, @Param("y") int y,
			@Param("region") String region);

	int countByEmail(String email);

	@Query(value = "select * from commit c where user_email=:email and c.id not in (select distinct commit_id from sns) order by created_at desc", nativeQuery = true)
	List<Commit> commitOnly(@Param("email") String email);

	Commit findCommitById(String id);

	@Query(value = "select count(*) from commit where user_email=:email", nativeQuery = true)
	int commitCnt(@Param("email") String email);

	List<Commit> findAllByRegion(String region);
}
