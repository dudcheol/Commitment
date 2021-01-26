package com.web.commitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.BoardDao;
import com.web.commitment.dao.CommitDao;
import com.web.commitment.dao.FollowDao;
import com.web.commitment.dao.LikeDao;
import com.web.commitment.dto.Ranking;

import io.swagger.annotations.ApiOperation;
@CrossOrigin
@RestController
public class RankingController {
	@Autowired
	BoardDao boardDao;
	
	@Autowired
	CommitDao commitDao;
	
	@Autowired
	LikeDao likeDao;
	
	@Autowired
	FollowDao followDao;
	
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
	@GetMapping("/rank/following")
	@ApiOperation(value = "팔로잉한 사람들 커밋 랭킹")
	public List<Ranking> followingRank(@RequestParam(required = true) String email) {
		List<Ranking> list = commitDao.followingRank(email);
		return list;
	}
	@GetMapping("/rank/board")
	@ApiOperation(value = "게시글 수 랭킹")
	public List<Ranking> boardRank() {
		List<Ranking> list = boardDao.boardRanking();
		return list;
	}
	@GetMapping("/rank/like")
	@ApiOperation(value = "좋아요 랭킹")
	public List<Ranking> likeRank() {
		List<Ranking> list = likeDao.likeRanking();
		return list;
	}
	@GetMapping("/rank/follower")
	@ApiOperation(value = "팔로워 수 랭킹")
	public List<Ranking> followerRank() {
		List<Ranking> list = followDao.followerRank();
		return list;
	}
}
