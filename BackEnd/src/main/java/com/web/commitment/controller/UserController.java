package com.web.commitment.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.web.commitment.dao.UserDao;
import com.web.commitment.dto.BasicResponse;
import com.web.commitment.dto.User;
import com.web.commitment.service.JwtService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class UserController {
	@Autowired
	UserDao userDao;
	@Autowired
	ProfileController profilecontroller;
	@Autowired
	private JwtService jwtService;

	@GetMapping("/account/login")
	@ApiOperation(value = "로그인")
	public Object login(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String pass) {
		Optional<User> userOpt = userDao.findUserByEmailAndPass(email, pass);
		ResponseEntity response = null;
		Map<String, Object> resultMap = new HashMap<>();

		if (userOpt.isPresent()) {
//        	jwt.io에서 확인
//			로그인 성공했다면 토큰을 생성한다.
			User user = userOpt.get();
			String token = jwtService.create(user);
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
			System.out.println("su");
			response = new ResponseEntity<>(resultMap, HttpStatus.OK);
		} else {
			resultMap.put("data", "fail");
			System.out.println("f");
			response = new ResponseEntity<>(resultMap, HttpStatus.OK);
		}

		return response;
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
		}

		System.out.println(user);
		userDao.save(user);

		return user;
	}

	@GetMapping("/account/info")
	@ApiOperation(value = "회원정보불러오기")
	public Object user(@RequestParam(required = true) final String email) {
		User user = userDao.getUserByEmail(email);
		System.out.println(user);
//		Map<String,String> map=new HashMap<String, String>();
//		map.put("email",email);
//		map.put("pass",user.getPass());
//		map.put("nickname",user.getNickname());
//		map.put("tel",user.getTel());
//		map.put("age",user.getAge());
//		map.put("gender",user.getGender());
//		map.put("badge",user.getBadge());
//		map.put("image",profilecontroller.image(email));
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

	// 네이버 메일 사용
	@GetMapping("/account/smtp")
	@ApiOperation(value = "smtp")
	@Transactional
	protected String smtp(@Valid @RequestParam(required = true) final String email) {
		User user = userDao.getUserByEmail(email);
		// 먼저 아이디로 회원정보를 받아오고 가져온 데이터에서 email값을 비교하여 존재하지 않으면 인증메일 보내지 못함
		// mail server 설정
		String host = "smtp.naver.com";
		String id = "1693013"; // 자신의 네이버 계정
		String password = "best81264";// 자신의 네이버 패스워드

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
			messageHelper.setSubject("안녕하세요 ss blog 인증 메일입니다.");
			// 메일 내용
			messageHelper.setText(new StringBuffer().append("<h1>[ss blog 이메일 인증]</h1>")
					.append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
					.append("<a href='http://localhost:8080/user/signUpConfirm?email=").append(user.getEmail())
					.append("&authKey=").append(AuthenticationKey).append("' target='_blenk'>이메일 인증 확인</a>").toString(),
					true);// true를 해야 html형식으로 됨

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
			response.sendRedirect("http://localhost:8082/#/user/mailCheck");// 회원가입이 완료되었습니다 페이지로 이동
		} else
			response.sendRedirect("http://localhost:8082/#/404");
	}

	@GetMapping("/user/loaction")
	@ApiOperation(value = "현재위치설정")
	public Object loaction(@RequestParam(required = true) final String email, @RequestParam(required = true) final String lat,
			@RequestParam(required = true) final String lng) throws IOException {
		User user = userDao.getUserByEmail(email);
		user.setLat(lat);
		user.setLng(lng);
		userDao.save(user);

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

    /// 해시태그로 검색
    @GetMapping("/search/nickname")
    @ApiOperation(value = "사용자 검색: 닉네임으로 검색")
    public Collection<User> searchByNickname(@RequestParam String keyword) {
    	
    	return userDao.findByNicknameContainingIgnoreCase(keyword);
    }
    
    /// 이메일로 검색
    @GetMapping("/search/email")
    @ApiOperation(value = "사용자 검색: 이메일로 검색")
    public Collection<User> searchByEmail(@RequestParam String keyword) {
    	
    	return userDao.findByEmailContainingIgnoreCase(keyword);
    }
}
