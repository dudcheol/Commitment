package com.web.commitment.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
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
import com.web.commitment.dto.Tag;
import com.web.commitment.dto.User;
import com.web.commitment.request.BoardTagReqDto;
import com.web.commitment.response.BoardDto;

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

	@Autowired
	FollowingBoardController followingBoardController;
	
	@Autowired
	TagController tagController;

	@PostMapping("/sns")
	@ApiOperation(value = "게시글 작성, 수정")
	public String commit(@RequestBody BoardTagReqDto req) {
		try {
			int emailResult = userDao.countByEmail(req.getEmail());

			if (emailResult != 0) {
				Board b = new Board();
				b.setCommitId(req.getCommitId());
				b.setEmail(req.getEmail());
				b.setTitle(req.getTitle());
				b.setContent(req.getContent());
				b.setLocation(req.getLocation());
				boardDao.save(b);
				
				// snsId 뱉어주기
				System.out.println(b);
				tagController.tag(b.getId(), req.getTag());
				return b.getId();
			} else {
				// email이 없음
				return "no email";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// 로그인한 유저의 게시글 불러오기
	@GetMapping("/mysns")
	@ApiOperation(value = "로그인 한 유저의 게시글 목록")
	@Transactional(readOnly = true)
	public Page<BoardDto> mySns(@RequestParam final String email, final Pageable pageable) {
		// 페이지 index는 0부터
		Page<Board> boards = boardDao.findByEmail(email, pageable);
		List<BoardDto> boardDtos = followingBoardController.clean(boards);
		return new PageImpl<BoardDto>(boardDtos, pageable, boards.getTotalElements());
//		return boardDto;
	}

	// 다른 유저의 게시글 불러오기 open이 1인 것만 (닉네임, 좋아요 수, 태그 주소 줘야 함)
	@GetMapping("/sns")
	@ApiOperation(value = "다른 유저의 게시글 목록")
	public Page<BoardDto> loadSns(@RequestParam String email, final Pageable pageable) {
		// 페이지 index는 0부터
		Page<Board> boards = boardDao.findAllByEmail(email, pageable);
		List<BoardDto> boardDtos = followingBoardController.clean(boards);
		return new PageImpl<BoardDto>(boardDtos, pageable, boards.getTotalElements());
	}

	@DeleteMapping("/sns")
	@ApiOperation(value = "게시글 삭제")
	public String delete(@RequestParam String sns_id) {
		try {
			boardDao.deleteById(sns_id);
			return "success";

		} catch (Exception e) {
			return "fail";
		}
	}

	@GetMapping("/sns/{id}")
	@ApiOperation(value = "게시글 상세")
	public Optional<Board> snsDetail(@PathVariable String id) {

		return boardDao.findById(id);
	}

	// 대소문자 구분 없이 검색! IgnoreCase
	@GetMapping("/search/title")
	@ApiOperation(value = "제목으로 검색(open 1인 것만)")
	public Page<BoardDto> searchByTitle(@RequestParam String keyword, final Pageable pageable) {

		keyword = "%" + keyword + "%";
		Page<Board> boards = boardDao.findByTitleContainingIgnoreCase(keyword, pageable);
		List<BoardDto> boardDtos = followingBoardController.clean(boards);
		return new PageImpl<BoardDto>(boardDtos, pageable, boards.getTotalElements());
	}

	@GetMapping("/search/content")
	@ApiOperation(value = "내용으로 검색(open 1인 것만)")
	public Page<BoardDto> searchByContent(@RequestParam String keyword, final Pageable pageable) {

		keyword = "%" + keyword + "%";
		Page<Board> boards = boardDao.findByContentContainingIgnoreCase(keyword, pageable);
		List<BoardDto> boardDtos = followingBoardController.clean(boards);
		return new PageImpl<BoardDto>(boardDtos, pageable, boards.getTotalElements());

	}

	@GetMapping("/search/tnc")
	@ApiOperation(value = "제목 & 내용으로 검색(open 1인 것만)")
	public Page<BoardDto> searchByTandC(@RequestParam String keyword, final Pageable pageable) {

		Page<Board> boards = boardDao.findByTitleandContent("%" + keyword.toLowerCase() + "%", pageable);
		List<BoardDto> boardDtos = followingBoardController.clean(boards);
		return new PageImpl<BoardDto>(boardDtos, pageable, boards.getTotalElements());

	}

	@GetMapping("/search/writer")
	@ApiOperation(value = "글쓴이로 검색(open 1인 것만)")
	public Page<BoardDto> searchByWriter(@RequestParam String keyword, final Pageable pageable) {

		Page<Board> boards = boardDao.findByEmailContainingIgnoreCase("%" + keyword.toLowerCase() + "%", pageable);
		List<BoardDto> boardDtos = followingBoardController.clean(boards);
		return new PageImpl<BoardDto>(boardDtos, pageable, boards.getTotalElements());
	}

	// 모든 유저의 게시글 불러오기 open이 1인 것만 & 해당 반경에 해당하는 사람들 것만
	@GetMapping("/sns/radius")
	@ApiOperation(value = "설정한 반경 내 모든 유저의 게시글(open 1인 것만)")
	public Page<BoardDto> loadRadiusSns(@RequestParam String lat, @RequestParam String lng,
			@RequestParam(required = false, defaultValue = "0") double radius, final Pageable pageable) {
		
		// 모든 게시물(open1인 것만)
		Page<Board> boards = null;
		if (radius == 0) {
			boards = boardDao.findAll(pageable);
		} else {
			boards = boardDao.radiusCommitId(lat, lng, radius, pageable);
		}

		List<BoardDto> result = followingBoardController.clean(boards);
		return new PageImpl<BoardDto>(result, pageable, boards.getTotalElements());
	}

	@GetMapping("/sns/location")
	@ApiOperation(value = "네모칸 안에 회원 전체 게시글 (open 1인 것만)")
	public Page<BoardDto> locationsns(@RequestParam String x, @RequestParam String y, String region,final Pageable pageable) {
		Page<Board> boards;
		if(region.equals("national")) {
			boards=boardDao.nationalsns(x,y, pageable);
		}else {
			boards=boardDao.locationsns(x,y,region, pageable);
		}
		List<BoardDto> boardDtos = followingBoardController.clean(boards);
		return new PageImpl<BoardDto>(boardDtos, pageable, boards.getTotalElements());

	}
}
