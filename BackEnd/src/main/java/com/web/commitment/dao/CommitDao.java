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

@Repository
public interface CommitDao extends JpaRepository<Commit, String> {

	List<Commit> findAllByEmail(String email);
	
	Optional<Commit> findByLatAndLng(String lat, String lng);
	
	List<Commit> findByEmail(String email);
	//
//    @Transactional
//    @Modifying
//    @Query(value = "select user_email from commit c group by c.user_email order by count(*) desc", nativeQuery = true)
//    void rank(@Param("email") String email);

    @Query(value = "select * from commit c where user_email=:email and c.lat=:lat and c.lng=:lng", nativeQuery = true)
    Optional<Commit> commitCheck(@Param("email") String email, @Param("lat") String lat,@Param("lng") String lng);

}