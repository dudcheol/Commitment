package com.web.commitment;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.*;

import com.web.commitment.vue.JwtInterceptor;

@SpringBootApplication
public class VueSpringBootLoginApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(VueSpringBootLoginApplication.class, args);
	}

	@Autowired
    private JwtInterceptor jwtInterceptor;

//    JWTInterceptor를 설치한다. 인터셉터가 작동할 주소(로그인 필요한 주소
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/user/update"); // 기본 적용 경로
//                .excludePathPatterns(Arrays.asList("/member/login", "/board/list"));// 적용 제외 경로 수정하기!!
    }

//    Interceptor를 이용해서 처리하므로 전역의 Corss Origin 처리를 해준다.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("auth-token");
    }
	
}
