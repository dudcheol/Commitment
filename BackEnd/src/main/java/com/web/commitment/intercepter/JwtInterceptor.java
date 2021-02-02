package com.web.commitment.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.web.commitment.service.JwtService;



@Component
public class JwtInterceptor implements HandlerInterceptor {
	
	@Autowired
    private JwtService jwtService;
	
	public static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);
	//컨트롤러보다 먼저 수행되는 메서드
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info(request.getMethod() + " : " + request.getServletPath());
         
         // option 요청은 바로 통과시켜주자.
         if (request.getMethod().equals("OPTIONS")) {
             return true;
         } else {
             // request의 parameter에서 auth_token으로 넘어온 녀석을 찾아본다.
             // String token = request.getParameter("auth_token");
             String token = request.getHeader("token");
             if (token != null && token.length() > 0) {
                 // 유효한 토큰이면 진행, 그렇지 않으면 예외를 발생시킨다.
                 jwtService.checkValid(token);
                 logger.info("토큰 사용 가능 : {}", token);
                 return true;
             } 
//             else {
//                 throw new RuntimeException("인증 토큰이 없습니다.");
//             }
         }
       return true;
     }
}