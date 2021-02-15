package com.web.commitment.response;

import com.web.commitment.dto.Profile;
import com.web.commitment.dto.Ranking;

import lombok.Data;
@Data
public class RankingDto {
    private Ranking ranking;
    private Profile profile;

}