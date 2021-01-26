package com.web.commitment.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.CommitDao;
import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.Commit;
import com.web.commitment.dto.User;
import com.web.commitment.dto.Ranking;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class CommitController {

	@Autowired
	CommitDao commitDao;
	
	@Autowired
	UserDao userDao;
	
	// CRUD 중 C만
    @PostMapping("/commit/{open}")
    @ApiOperation(value = "커밋하기")
    public String commit(@Valid @RequestBody User user, @PathVariable int open) {
    	// user를 받아오면 해당 user, lat, lng로 커밋 정보 저장
		try {
			Commit commit = new Commit();
			commit.setEmail(user.getEmail());
			commit.setLat(user.getLat());
			commit.setLng(user.getLng());
			commit.setOpen(open);
			
//			Optional<Commit> getCommit=commitDao.commitCheck(user.getEmail(),user.getLat() , user.getLng());
			
        	System.out.println(user);
        	commitDao.save(commit);
        	return "success";

		} catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
    }
    
    // 커밋 행렬 좌표, 횟수 불러오기
	@GetMapping("/commit")
    @ApiOperation(value = "인덱스 별 커밋 횟수 불러오기")
    public List<int[]> commitCount(@RequestParam(required = true) final String email, @RequestParam(required = false) String name) {
		
    	Map<Position, Integer> map = new HashMap<>();
    	
    	if(name != null) {
	    	List<Commit> commits = commitDao.findAllByEmailAndName(email, name);
	    	for (Commit commit : commits) {
				map.put(new Position(commit.getLocalX(), commit.getLocalY()), map.getOrDefault(new Position(commit.getLocalX(), commit.getLocalY()), 0) + 1);
			}
    	} else { // name이 null이라면 전국지도
    		List<Commit> commits = commitDao.findAllByEmail(email);
    		for (Commit commit : commits) {
    			map.put(new Position(commit.getLocalX(), commit.getLocalY()), map.getOrDefault(new Position(commit.getLocalX(), commit.getLocalY()), 0) + 1);
			}
    	}
    		
    	List<int[]> positions = new ArrayList<>();
        // Iterator 사용 1 - keySet()
        Iterator<Position> keys = map.keySet().iterator();
        while (keys.hasNext()){
            Position key = keys.next();
            positions.add(new int[] {key.x, key.y, map.get(new Position(key.x, key.y))});
            System.out.println("KEY : " + key.x + " " + key.y); //
        }

    	return positions; // positions[0]: x좌표, positions[1]: y좌표, positions[2]: count
    }


	@GetMapping("/commit/commitrank")
    @ApiOperation(value = "랭킹")
    public List<Ranking> commitRank() {
    	
    	List<Ranking> list =commitDao.commitRank();
    	return list;
    }
    
    // 인덱스 정보
    static public class Position {
    	int x;
    	int y;

    	Position(int x, int y){
    		this.x = x;
    		this.y = y;
    	}
    	
    	@Override
        public boolean equals(Object obj)//비교 부분
        {
            return (y == ((Position)obj).y && x == ((Position)obj).x);
        }

        @Override
        public int hashCode()//해쉬 코드 부분
        {
            return Integer.valueOf(y).hashCode() + Integer.valueOf(x).hashCode();
        }

    }
    
    // 커밋 불러오기 -> open 1인 것만
    
}
