package com.web.commitment.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Follow;
import com.web.commitment.dto.Ranking;
import com.web.commitment.dto.User;

@Repository
public interface FollowDao extends JpaRepository<Follow, String> {
	@Modifying
	@Query(value = "select * from follow f where f.follow_from=:email", nativeQuery = true)
	List<Follow> FindFollowByEmail(@Param("email") String email);

	// 랭킹 관련
	@Query(value = "select follow.follow_to email, rank() over (order by count(*) desc) ranking, count(*) cnt from follow "
			+ "group by follow.follow_to", nativeQuery = true)
	List<Ranking> followerRank();

	@Query(value = "select count(*) cnt from follow f where f.follow_from=:email", nativeQuery = true)
	int followCnt(String email);

//	Optional<User> findByToUserAndFromUser(String email, String email2);

}
