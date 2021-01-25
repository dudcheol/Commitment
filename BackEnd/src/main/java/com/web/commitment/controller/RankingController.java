package com.web.commitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.RankingDao;
import com.web.commitment.dto.Ranking;

import io.swagger.annotations.ApiOperation;
@CrossOrigin
@RestController
public class RankingController {
	@Autowired
	RankingDao rankingDao;
	
	@GetMapping("/rank/total")
	@ApiOperation(value = "랭킹")
	public List<Ranking> commitRank() {
		List<Ranking> list = rankingDao.commitRank();
		return list;
	}
	@GetMapping("/rank/aweek")
	@ApiOperation(value = "주간랭킹")
	public List<Ranking> commitWeekRank() {
		List<Ranking> list = rankingDao.commitWeekRank();
		return list;
	}
	@GetMapping("/rank/month")
	@ApiOperation(value = "월간랭킹")
	public List<Ranking> commitMonthRank() {
		List<Ranking> list = rankingDao.commitMonthRank();
		return list;
	}
	@GetMapping("/rank/following")
	@ApiOperation(value = "팔로잉한 사람들 커밋 랭킹")
	public List<Ranking> followingRank(@RequestParam(required = true) String email) {
		List<Ranking> list = rankingDao.followingRank(email);
		return list;
	}
}
