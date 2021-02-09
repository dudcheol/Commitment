package com.web.commitment.dao;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Board;
import com.web.commitment.dto.Comment;
import com.web.commitment.dto.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {


	User getUserByEmail(String email);

	User findUserByEmail(String email);

	Optional<User> findUserByEmailAndPass(String email, String pass);

	Optional<User> findUserByNickname(String nickname);

	
	Optional<User> findUserByEmailAndAuthkey(String email, String authKey);

	@Transactional
	@Modifying
	@Query(value = "update user u set u.auth=1 where u.email=:email", nativeQuery = true)
	void AuthUpdate(@Param("email") String email);

	@Transactional
	@Modifying
	@Query(value = "update user u set u.authkey=:authkey where u.email=:email", nativeQuery = true)
	void AuthkeyUpdate(@Param("email") String email, @Param("authkey") String authkey);
	
	int countByEmail(String email);


	Page<User> findByNicknameContainingIgnoreCase(String keyword, Pageable pageable);

	Page<User> findByEmailContainingIgnoreCase(String keyword, Pageable pageable);

	Optional<User> findByEmail(String email);
	
	 @Query(value = "select distinct u.* from user u,commit c "
             + "where u.email in (select f.follow_to from follow f where f.follow_from=:email) "
             + "order by c.created_at desc", nativeQuery = true)
	 Page<User> findfollowMapByEmail(@Param("email") String email, Pageable pageable);

	@Query(value = "select distinct u.* from user u, commit c where u.email in (select f.follow_to from follow f where f.follow_from=:email) "
			+ "order by c.created_at desc", nativeQuery = true)
	List<User> findAllByFollowing(@Param("email") String email);

}
