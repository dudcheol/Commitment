package com.web.commitment.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Commit;
import com.web.commitment.dto.Rank;

@Repository
public interface CommitDao extends JpaRepository<Commit, String> {

	List<Commit> findAllByEmail(String email);

	Optional<Commit> findByLatAndLng(String lat, String lng);

	List<Commit> findByEmail(String email);
	
	//에러남... 나중에 수정하기..	
	@Query(value="select new com.web.commitment.dto.Rank(c.user_email, rank() over (order by count(*) desc)) from commit c group by c.user_email", nativeQuery = true)
	List<Rank> commitRank();
	
	@Query(value = "select * from commit c where user_email=:email and c.lat=:lat and c.lng=:lng", nativeQuery = true)
	Optional<Commit> commitCheck(@Param("email") String email, @Param("lat") String lat,@Param("lng") String lng);
	
}
