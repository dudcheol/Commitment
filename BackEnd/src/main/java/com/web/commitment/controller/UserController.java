package com.web.commitment.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.commitment.dao.UserBadgeDao;
import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.Badge;
import com.web.commitment.dto.BasicResponse;
import com.web.commitment.dto.Profile;
import com.web.commitment.dto.User;
import com.web.commitment.request.LoginRequest;
import com.web.commitment.response.BoardDto;
import com.web.commitment.response.UserDto;
import com.web.commitment.service.JwtService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class UserController {
	@Autowired
	UserDao userDao;
	@Autowired
	FollowController followController;
	@Autowired
	CommitController commitController;
	@Autowired
	BadgeController badgeController;
	@Autowired
	UserBadgeDao userBadgeDao;
	@Autowired
	private JwtService jwtService;

	@PostMapping("/account/login")
	@ApiOperation(value = "로그인")
	public Object login(@RequestBody LoginRequest request) {
		Optional<User> userOpt;
		ResponseEntity response = null;
		Map<String, Object> resultMap = new HashMap<>();
		System.out.println("login");
		if (request.getToken() != null) {// 소셜 로그인 일경우
			userOpt = userDao.findByEmail(request.getEmail());
			User user = new User();

			if (!userOpt.isPresent()) {// db에 없는 경우
				user.setEmail(request.getEmail());
				user.setNickname(request.getName());
				userDao.save(user);// 회원가입시킴
			}
			Profile profile = new Profile();
			profile.setEmail(request.getEmail());
			profile.setFilePath(request.getImage());

		}

		userOpt = userDao.findUserByEmailAndPass(request.getEmail(), request.getPass());

		if (userOpt.isPresent()) {
//        	jwt.io에서 확인
//			로그인 성공했다면 토큰을 생성한다.
			User user = userOpt.get();
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(user, userDto);
			String token = jwtService.create(userDto);
//			logger.trace("로그인 토큰정보 : {}", token);

			resultMap.put("auth-token", token);
			resultMap.put("data", "success");
			resultMap.put("email", user.getEmail());
			resultMap.put("pass", user.getPass());
			resultMap.put("nickname", user.getNickname());
			resultMap.put("tel", user.getTel());
			resultMap.put("age", user.getAge());
			resultMap.put("gender", user.getGender());
			resultMap.put("mystory", user.getMystory());
			resultMap.put("auth", user.getAuth());
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		}
		resultMap.put("data", "fail");

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@GetMapping("/account/info")
	@ApiOperation(value = "로그인 회원정보 받아오기")
	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println(">>>>>> " + jwtService.get(req.getHeader("auth-token")));
		try {
			// 사용자에게 전달할 정보이다.
			resultMap.putAll(jwtService.get(req.getHeader("auth-token")));
			Map<String, String> s = (Map<String, String>) resultMap.get("user");
			resultMap.put("commitCnt", commitController.totalCommitNum(s.get("email")));
			resultMap.put("followerCnt", followController.followCnt(s.get("email")));
			resultMap.put("badgeCnt", badgeController.badgeCnt(s.get("email")));

			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
//			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/account/signup")
	@ApiOperation(value = "회원가입수정")
	@Transactional
	public User signup(@Valid @RequestBody User request) {
		User user = userDao.findUserByEmail(request.getEmail());// 수정

		if (user == null)// 가입
			user = request;
		else {
			user.setEmail(request.getEmail());
			user.setPass(request.getPass());
			user.setNickname(request.getNickname());
			user.setTel(request.getTel());
			user.setAge(request.getAge());
			user.setGender(request.getGender());
			user.setMystory(request.getMystory());
			// 지역은 회원가입후 현재 위치 설정에서 받아옴
		}

		System.out.println(user);
		userDao.save(user);

		return user;
	}

	@DeleteMapping("/account/delete")
	@ApiOperation(value = "회원탈퇴")
	@Transactional
	public Object userDelete(@RequestParam(required = true) final String email) {
		User user = userDao.getUserByEmail(email);
		userDao.delete(user);

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/account/nickCheck")
	@ApiOperation(value = "닉네임 중복체크")
	public Map<String, String> uidCheck(@RequestParam(required = true) final String nickname) throws IOException {
		Optional<User> user = userDao.findUserByNickname(nickname);
		Map<String, String> hm = new HashMap<>();

		if (user.isPresent()) {
			hm.put("data", "fail");
			return hm;
		}
		hm.put("data", "success");
		return hm;
	}

	@GetMapping("/account/emailCheck")
	@ApiOperation(value = "이메일 중복체크")
	public Map<String, String> emailCheck(@RequestParam(required = true) final String email) throws IOException {
		Optional<User> user = userDao.findByEmail(email);
		Map<String, String> hm = new HashMap<>();

		if (user.isPresent()) {
			hm.put("data", "fail");
			return hm;
		}
		hm.put("data", "success");
		return hm;
	}

	// 네이버 메일 사용
	@GetMapping("/account/smtp")
	@ApiOperation(value = "smtp")
	@Transactional
	protected String smtp(@Valid @RequestParam(required = true) final String email) {
		User user = userDao.getUserByEmail(email);
		// 먼저 아이디로 회원정보를 받아오고 가져온 데이터에서 email값을 비교하여 존재하지 않으면 인증메일 보내지 못함
		// mail server 설정
		String host = "smtp.naver.com";
		String id = ""; // 자신의 네이버 계정
		String password = "";// 자신의 네이버 패스워드

		// 메일 받을 주소
		String to_email = email;

		// SMTP 서버 정보를 설정한다.
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		// 인증 번호 생성기
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			int rIndex = rnd.nextInt(3);
			switch (rIndex) {
			case 0:
				// a-z
				temp.append((char) ((int) (rnd.nextInt(26)) + 97));
				break;
			case 1:
				// A-Z
				temp.append((char) ((int) (rnd.nextInt(26)) + 65));
				break;
			case 2:
				// 0-9
				temp.append((rnd.nextInt(10)));
				break;
			}
		}
		String AuthenticationKey = temp.toString();
		user.setAuthkey(AuthenticationKey);// 인증키 저장
		userDao.save(user);
		System.out.println(AuthenticationKey);
		userDao.AuthkeyUpdate(to_email, AuthenticationKey);// 테이블에 KEY 저장

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(id, password);
			}
		});
		session.setDebug(true);
		// email 전송
		try {
			MimeMessage msg = new MimeMessage(session);
			MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, "UTF-8");
			messageHelper.setFrom(new InternetAddress("1693013@naver.com"));
			messageHelper.setTo(email);
			// 메일 제목
			messageHelper.setSubject("안녕하세요 Commitment 인증 메일입니다.");
			// 메일 내용
			messageHelper.setText(new StringBuffer().append("<center>").append("<div height=\"1000\">").append(
					"<img src=\"https://commitmentbucket.s3.ap-northeast-2.amazonaws.com/%EB%A9%94%EC%9D%BC+%EC%9D%B8%EC%A6%9D.PNG\" width=\"550\" >")
					.append("<br>").append("<a  href='https://i4a308.p.ssafy.io:8080/user/signUpConfirm?email=")
					.append(user.getEmail()).append("&authKey=").append(AuthenticationKey)
					.append("' target='_blenk'><font size=\"5px\"  color=\"black\">[메일 인증]</a></font>").append("</div>")
					.append("</center>").toString(), true);// true를 해야 html형식으로 됨

			Transport.send(msg);
			System.out.println("이메일 전송");

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return AuthenticationKey;
	}

	@GetMapping("/user/signUpConfirm")
	@ApiOperation(value = "메일 인증 확인")
	public void signUpConfirm(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String authKey, HttpServletResponse response) throws IOException {
		// 해당 이메일 권한 허용 authStatus 업데이트
		Optional<User> userOpt = userDao.findUserByEmailAndAuthkey(email, authKey);// 인증키 일치하는지 확인
		if (userOpt.isPresent()) {
			userDao.AuthUpdate(email);// 인증 했다고 체크
			response.sendRedirect("http://i4a308.p.ssafy.io/user/mailCheck");// 회원가입이 완료되었습니다 페이지로 이동(프론트 주소)
		} else
			response.sendRedirect("http://i4a308.p.ssafy.io/404");
	}

	@GetMapping("/user/loaction")
	@ApiOperation(value = "현재위치설정")
	public Object loaction(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String lat, @RequestParam(required = true) final String lng)
			throws IOException {
		User user = userDao.getUserByEmail(email);
		user.setLat(lat);
		user.setLng(lng);
		userDao.save(user);

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/// 닉네임으로 검색
	@GetMapping("/user/nickname")
	@ApiOperation(value = "닉네임으로 유저정보 받아오기")
	public Map<String, Object> searchByNickname(@RequestParam String nickname) {
		HashMap<String, Object> hm = new HashMap<>();

		Optional<User> option = userDao.findUserByNickname(nickname);
		UserDto userdto = new UserDto();
		if (option.isPresent()) {
			BeanUtils.copyProperties(option.get(), userdto);
			hm.put("user", userdto);
			hm.put("commitCnt", commitController.totalCommitNum(userdto.getEmail()));
			hm.put("followerCnt", followController.followCnt(userdto.getEmail()));
			hm.put("badgeCnt", badgeController.badgeCnt(userdto.getEmail()));
			hm.put("data", "success");
		} else
			hm.put("data", "fail");
		return hm;
	}

	/// 이메일로 검색
	@GetMapping("/search/email")
	@ApiOperation(value = "이메일로 검색")
	public Page<User> searchByEmail(@RequestParam String keyword, final Pageable pageable) {

		return userDao.findByEmailContainingIgnoreCase(keyword, pageable);
	}
	@GetMapping("/search/nickname")
	@ApiOperation(value = "닉네임으로 검색")
	public Page<User> searchBynick(@RequestParam String keyword, final Pageable pageable) {
		
		return userDao.findByNicknameContainingIgnoreCase(keyword, pageable);
	}

	@GetMapping("/user/map")
	@ApiOperation(value = "대표지도 설정")
	public String mapSelect(@RequestParam String email, @RequestParam String region) {
		User user = userDao.getUserByEmail(email);
		try {
			user.setRegion_name(region);
			userDao.save(user);
		} catch (Exception e) {
			return "지역이름이나 이메일 오류";
		}

		return "success";
	}
}
