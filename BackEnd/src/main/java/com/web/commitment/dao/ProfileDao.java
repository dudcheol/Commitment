package com.web.commitment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.commitment.dto.Profile;
@Repository
public interface ProfileDao extends JpaRepository<Profile, String> {

	Profile getProfileByEmail(@Param("email") String email);

	Profile findProfileByEmail(String email);

}
