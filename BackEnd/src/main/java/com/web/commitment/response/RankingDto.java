package com.web.commitment.response;

import com.web.commitment.dto.Profile;
import com.web.commitment.dto.Ranking;

import lombok.Data;
@Data
public class RankingDto {
	private String email;
	private String nickname;
	private int ranking;
	private int cnt;
    private Profile profile;

}