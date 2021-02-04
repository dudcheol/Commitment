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
import com.web.commitment.dto.User;

@Repository
public interface LikeDao extends JpaRepository<Like, String> {

	Optional<Like> findByEmailAndSnsId(String email, int snsId);

	Page<Like> findAllByEmail(String email, Pageable pabeable);

	int countByWriter(String email);

	int countByEmail(String email);
	
	int countBySnsId(int id);
	//랭킹 관련
	@Query(value = "select save.user_email email, rank() over (order by count(*) desc) ranking, count(*) cnt from save "
			+ "group by save.user_email", nativeQuery = true)
	List<Ranking> likeRanking();

	Optional<User> findByEmailAndSnsId(String fromUser, String id);

}