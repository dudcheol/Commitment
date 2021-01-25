package com.web.commitment.dao;

import java.util.List;
import java.util.Optional;

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

	@Query(value = "select commit.user_email email, rank() over (order by count(*) desc) ranking from commit  group by commit.user_email", nativeQuery = true)
	List<Ranking> commitRank();

	@Query(value = "select * from commit c where user_email=:email and c.lat=:lat and c.lng=:lng", nativeQuery = true)
	Optional<Commit> commitCheck(@Param("email") String email, @Param("lat") String lat, @Param("lng") String lng);

	List<Commit> findAllByEmailAndName(String eamil, String name);

}
