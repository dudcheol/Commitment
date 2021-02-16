package com.web.commitment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.BoardDao;
import com.web.commitment.dao.CommitDao;
import com.web.commitment.dao.FollowDao;
import com.web.commitment.dao.LikeDao;
import com.web.commitment.dao.ProfileDao;
import com.web.commitment.dto.Profile;
import com.web.commitment.dto.Ranking;
import com.web.commitment.response.RankingDto;

import io.swagger.annotations.ApiOperation;
@CrossOrigin
@RestController
public class RankingController {
	@Autowired
	CommitDao commitDao;
	@Autowired
	ProfileDao profileDao;
	
    public List<RankingDto> modifyDto(List<Ranking> list){
        List<RankingDto> dtolist=new ArrayList<RankingDto>();
        for(Ranking r:list) {
            RankingDto rankingDto=new RankingDto();
            BeanUtils.copyProperties(r, rankingDto);
            Optional<Profile> optional=profileDao.findById(r.getEmail());
            if(optional.isPresent())    
                rankingDto.setProfile(optional.get());
            dtolist.add(rankingDto);
        }
        return dtolist;
    }
	@GetMapping("/rank/total")
	@ApiOperation(value = "전체커밋랭킹")
    public List<RankingDto> commitRank() {
        return modifyDto(commitDao.commitRank());
	}
	
	//지역별 커밋 랭킹
	@GetMapping("/rank/seoul")
	@ApiOperation(value = "서울 커밋 랭킹")
    public List<RankingDto> seoulRank() {
        return modifyDto(commitDao.seoulRank());
	}
	
	@GetMapping("/rank/gyeonggi")
	@ApiOperation(value = "경기 커밋 랭킹")
    public List<RankingDto> gyeonggiRank() {
        return modifyDto(commitDao.gyeonggiRank());
	}
	@GetMapping("/rank/gangwon")
	@ApiOperation(value = "강원 커밋 랭킹")
    public List<RankingDto> gangwonRank() {
        return modifyDto(commitDao.gangwonRank());
	}
	@GetMapping("/rank/gwangju")
	@ApiOperation(value = "광주 커밋 랭킹")
    public List<RankingDto> gwangjuRank() {
        return modifyDto(commitDao.gwangjuRank());
	}
	@GetMapping("/rank/ulsan")
	@ApiOperation(value = "울산 커밋 랭킹")
    public List<RankingDto> ulsanRank() {
        return modifyDto(commitDao.ulsanRank());
	}
	@GetMapping("/rank/busan")
	@ApiOperation(value = "부산 커밋 랭킹")
    public List<RankingDto> busanRank() {
        return modifyDto(commitDao.busanRank());
	}
	
}
