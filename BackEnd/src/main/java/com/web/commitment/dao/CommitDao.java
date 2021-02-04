package com.web.commitment.dao;

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

//	@Query(value = "select * from commit c where user_email=:email and c.lat=:lat and c.lng=:lng", nativeQuery = true)
//	Optional<Commit> commitCheck(@Param("email") String email, @Param("lat") String lat, @Param("lng") String lng);


	List<Commit> findAllByEmailAndRegion(String eamil, String name);

	List<Commit> findAllByOpen(int i);

	List<Commit> findAllByEmailAndNationalXAndNationalY(String email, String x, String y);

	List<Commit> findAllByEmailAndLocalXAndLocalYAndRegion(String email, String x, String y, String region);

	List<Commit> findByEmail(String email);

	// 랭킹관련
	@Query(value = "select commit.user_email email, rank() over (order by count(*) desc) ranking, count(*) cnt from commit "
			+ "group by commit.user_email", nativeQuery = true)
	List<Ranking> commitRank();

	@Query(value = "select commit.user_email email, rank() over (order by count(*) desc) ranking, count(*) cnt from commit "
			+ "where created_at between DATE_ADD(NOW(),INTERVAL -1 WEEK ) AND NOW() "
			+ "group by commit.user_email", nativeQuery = true)
	List<Ranking> commitWeekRank();

	@Query(value = "select commit.user_email email, rank() over (order by count(*) desc) ranking, count(*) cnt from commit "
			+ "where created_at between DATE_ADD(NOW(),INTERVAL -1 MONTH ) AND NOW() "
			+ "group by commit.user_email", nativeQuery = true)
	List<Ranking> commitMonthRank();

	@Query(value = "select commit.user_email email, rank() over (order by count(*) desc) ranking, count(*) cnt \r\n"
			+ "from commit \r\n" + "where commit.user_email in (\r\n"
			+ "select follow.follow_to from follow where follow_from=:email) "
			+ "group by commit.user_email;", nativeQuery = true)
	List<Ranking> followingRank(@Param("email") String email);

	@Query(value = "select commit.user_email email, rank() over (order by count(*) desc) ranking, count(*) cnt from commit "
			+ "where commit.region=seoul" + "group by commit.user_email", nativeQuery = true)
	List<Ranking> seoulRank();

	@Query(value = "select commit.user_email email, rank() over (order by count(*) desc) ranking, count(*) cnt from commit "
			+ "where commit.region=gyeonggi" + "group by commit.user_email", nativeQuery = true)
	List<Ranking> gyeonggiRank();

	@Query(value = "select commit.user_email email, rank() over (order by count(*) desc) ranking, count(*) cnt from commit "
			+ "where commit.region=gangwon" + "group by commit.user_email", nativeQuery = true)
	List<Ranking> gangwonRank();

	@Query(value = "select commit.user_email email, rank() over (order by count(*) desc) ranking, count(*) cnt from commit "
			+ "where commit.region=gwangju" + "group by commit.user_email", nativeQuery = true)
	List<Ranking> gwangjuRank();

	@Query(value = "select commit.user_email email, rank() over (order by count(*) desc) ranking, count(*) cnt from commit "
			+ "where commit.region=ulsan" + "group by commit.user_email", nativeQuery = true)
	List<Ranking> ulsanRank();

	@Query(value = "select commit.user_email email, rank() over (order by count(*) desc) ranking, count(*) cnt from commit "
			+ "where commit.region=busan" + "group by commit.user_email", nativeQuery = true)
	List<Ranking> busanRank();

	// 커밋 시간제한
	@Query(value = "select * from commit "
			+ "where commit.user_email=:email and commit.region_name=:region and commit.local_x=:x and commit.local_y=:y"
			+ " AND created_at between DATE_ADD(NOW(),INTERVAL -1 HOUR ) AND NOW()", nativeQuery = true)
	List<Commit> timeCheck(@Param("email") String email, @Param("x") int x, @Param("y") int y,
			@Param("region") String region);

	int countByEmail(String email);

	@Query(value = "select * from commit c,sns s where c.user_email=:email and c.open=1 order by", nativeQuery = true)
	Page<Commit> findAllByEmail(@Param("email") String email, Pageable pageable);


	

}
