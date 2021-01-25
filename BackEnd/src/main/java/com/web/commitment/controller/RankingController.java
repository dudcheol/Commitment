package com.web.commitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.CommitDao;
import com.web.commitment.dto.Ranking;

import io.swagger.annotations.ApiOperation;
@CrossOrigin
@RestController
public class RankingController {
	@Autowired
	CommitDao commitDao;
	
	@GetMapping("/rank/total")
	@ApiOperation(value = "랭킹")
	public List<Ranking> commitRank() {
		List<Ranking> list = commitDao.commitRank();
		return list;
	}
	@GetMapping("/rank/aweek")
	@ApiOperation(value = "주간랭킹")
	public List<Ranking> commitWeekRank() {
		List<Ranking> list = commitDao.commitWeekRank();
		return list;
	}
	@GetMapping("/rank/month")
	@ApiOperation(value = "월간랭킹")
	public List<Ranking> commitMonthRank() {
		List<Ranking> list = commitDao.commitMonthRank();
		return list;
	}
}
