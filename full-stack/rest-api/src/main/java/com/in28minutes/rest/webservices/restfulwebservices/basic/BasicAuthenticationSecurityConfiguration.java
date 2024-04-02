package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthenticationSecurityConfiguration {
	
	// Filter chain
	// caution : 체인을 정의하기 시작하면 그 체인 전체를 정의해야 한다.
	// 요청이 들어오면 Spring Security 는 이 필터체인을 사용하게 됨
	// HttpSecurity : 실제로 우리가 특정한 HTTP 요청에 대해 웹 기반 보안을 설정할 수 있게 도와줌
	
	/* 정의하려는 Filter chain */
	// authentication all requests
	// basic authentication
	// disabling csrf
	// stateless rest api
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		return
				http
					.authorizeHttpRequests(					
						auth -> 
							auth
							.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
							.anyRequest().authenticated()	// 모든 HTTP 요청이 인증되도록 함
					)
					.httpBasic(Customizer.withDefaults()) // HTTP 기본 인증 설정
					.sessionManagement(					  // stateless api
						session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						)
					.csrf().disable()					  // disabling csrf
					.build();
	}

}
