package com.web.commitment.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Like;
import com.web.commitment.dto.Ranking;

@Repository
public interface LikeDao extends JpaRepository<Like, String> {

	Optional<Like> findByEmailAndSnsId(String email, String snsId);

	Page<Like> findAllByEmail(String email, Pageable pabeable);

	int countByWriter(String email);

	int countByEmail(String email);
	
	int countBySnsId(int id);
	//랭킹 관련
	@Query(value = "select save.user_email email, rank() over (order by count(*) desc) ranking, count(*) cnt, profile.file_path profile" + 
			" from save left outer join profile on save.user_email=profile.user_email" + 
			" group by save.user_email", nativeQuery = true)
	List<Ranking> likeRanking();
	
	@Query(value = "select max(id) from save", nativeQuery = true)
	String findByLastLike();

	Page<Like> findAllByEmailOrderByCreatedAtDesc(String email, Pageable pageable);

}