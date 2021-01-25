package com.web.commitment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Commit;
import com.web.commitment.dto.Ranking;

@Repository
public interface RankingDao extends JpaRepository<Commit, String> {
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
}
