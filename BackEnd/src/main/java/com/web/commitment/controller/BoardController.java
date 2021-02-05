package com.web.commitment.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.web.commitment.dao.BoardDao;
import com.web.commitment.dao.CommitDao;
import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.Board;
import com.web.commitment.dto.User;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class BoardController {

	@Autowired
	BoardDao boardDao;

	@Autowired
	UserDao userDao;

	@Autowired
	CommitDao commitDao;

	@PostMapping("/sns")
	@ApiOperation(value = "게시글 작성")
	public String commit(@RequestBody Board sns) {

		// 여기서는 id 필요 없음
		// email, commit_id를 받아오면 게시글 작성 (이미지 업로드까지) XXX
//		System.out.println(LocalDateTime.now().toString().split("T")[0]);
		try {
			int emailResult = userDao.countByEmail(sns.getEmail());

			if (emailResult != 0) {
				sns.setCreatedAt(LocalDateTime.now());
				boardDao.save(sns);
				return "success";
			} else {
				// email이 없음
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// 로그인한 유저의 게시글 불러오기
	@GetMapping("/mysns")
	@ApiOperation(value = "로그인 한 유저의 게시글 목록")
	public Page<Board> mySns(@RequestParam final String email, final Pageable pageable) {
		// 페이지 index는 0부터
		return boardDao.findByEmail(email, pageable);
	}

	// 다른 유저의 게시글 불러오기 open이 1인 것만
	@GetMapping("/sns")
	@ApiOperation(value = "다른 유저의 게시글 목록")
	public Page<Board> loadSns(@RequestParam String email, final Pageable pageable) {
		// 페이지 index는 0부터
		return boardDao.findAllByEmail(email, pageable);
	}

	@PutMapping("/account/update")
	@ApiOperation(value = "게시글 수정")
	public Object update(@RequestBody Board sns) {

		// 여기서는 id 받아와야 함
		// email, commit_id를 받아오면 게시글 작성 (이미지 업로드까지) XXX
		try {
			int emailResult = userDao.countByEmail(sns.getEmail());

			if (emailResult != 0) {
				boardDao.save(sns);
				return "success";
			} else {
				// email이 없음
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@DeleteMapping("/sns")
	@ApiOperation(value = "게시글 삭제")
	public int delete(@RequestParam String sns_id) {

		Optional<Board> sns = boardDao.findById(sns_id);
		System.out.println(sns);

		try {
			boardDao.delete(sns.get());
			return 0;

		} catch (Exception e) {
			return 1;
		}
	}

	@GetMapping("/sns/{id}")
	@ApiOperation(value = "게시글 상세")
	public Optional<Board> snsDetail(@PathVariable String id) {

		return boardDao.findById(id);
	}

	// 대소문자 구분 없이 검색! IgnoreCase
	//open 1...?
	@GetMapping("/search/title")
	@ApiOperation(value = "제목으로 검색")
	public Page<Board> searchByTitle(@RequestParam String keyword, final Pageable pageable) {

		keyword = "%" + keyword + "%";
		return boardDao.findByTitleContainingIgnoreCase(keyword, pageable);
	}

	@GetMapping("/search/content")
	@ApiOperation(value = "내용으로 검색")
	public Page<Board> searchByContent(@RequestParam String keyword, final Pageable pageable) {

		keyword = "%" + keyword + "%";
		return boardDao.findByContentContainingIgnoreCase(keyword, pageable);
	}

	@GetMapping("/search/tnc")
	@ApiOperation(value = "제목 & 내용으로 검색")
	public Page<Board> searchByTandC(@RequestParam String keyword, final Pageable pageable) {

		return boardDao.findByTitleandContent("%"+keyword.toLowerCase()+"%", pageable);
	}

	@GetMapping("/search/writer")
	@ApiOperation(value = "글쓴이로 검색")
	public Page<Board> searchByWriter(@RequestParam String keyword, final Pageable pageable) {
		
		return boardDao.findByEmailContainingIgnoreCase("%"+keyword.toLowerCase()+"%", pageable);
	}

	// 모든 유저의 게시글 불러오기 open이 1인 것만 & 해당 반경에 해당하는 사람들 것만
	@GetMapping("/sns/radius")
	@ApiOperation(value = "설정한 반경 내 모든 유저의 게시글(open 1인 것만)")
	public Page<Board> loadRadiusSns(@RequestParam(required = true) String email,
			@RequestParam(required = false) Integer radius, final Pageable pageable) {

		// 유저의 현재 위치 구하기
		User user = userDao.getUserByEmail(email);

		String lat = user.getLat();
		String lng = user.getLng();

		if (radius == 0) {
			// 모든 게시물(open1인 것만)
			return boardDao.findAll(pageable);
		}
		return boardDao.radiusCommitId(lat, lng, radius, pageable);
	}
}
