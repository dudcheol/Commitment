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
	List<Follow> FindFollowListByEmail(@Param("email") String email);
	@Modifying
	@Query(value = "select * from follow f where f.follow_to=:email", nativeQuery = true)
	List<Follow> FindFollowerListByEmail(@Param("email") String email);

	// 랭킹 관련
	@Query(value = "select follow.follow_to email, rank() over (order by count(*) desc) ranking, count(*) cnt, profile.file_path profile" + 
			" from follow left outer join profile on follow.follow_to=profile.user_email" + 
			" group by follow.follow_to", nativeQuery = true)
	List<Ranking> followerRank();

	@Query(value = "select count(*) cnt from follow f where f.follow_to=:email", nativeQuery = true)
	int followCnt(String email);
	
	@Query(value = "select count(*) cnt from follow f where f.follow_from=:email", nativeQuery = true)
	int followingCnt(String email);

	
	@Query(value = "select count(*) from follow f where f.follow_to=:email and f.follow_from=:email2", nativeQuery = true)
	Optional<User> findByToUserAndFromUser(@Param("email") String email,@Param("email2") String email2);
	
	@Query(value = "select max(id) from follow", nativeQuery = true)
	String findByLastFollow();

	@Query(value = "select * from follow f where f.follow_to=:to and f.follow_from=:from", nativeQuery = true)
	Optional<Follow> findFollowByFromAndTo(@Param("from") String from,@Param("to") String to);
	
	@Query(value = "select * from follow f where f.id=:id", nativeQuery = true)
	Optional<Follow> findByFollowId(@Param("id") String id);
}
