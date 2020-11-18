package com.example.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration //빈 등록
@EnableWebSecurity //시큐리티 필터를 추가한다는 의미// 해당클래스에서 설정할 것을 지정
@EnableGlobalMethodSecurity(prePostEnabled=true) // 특정 요청들에 대한 권한 및 인증을 미리 체크함
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/img/**");		//이미지, 스크립트 파일 허용
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.csrf().disable()	//post요청 문제 해결
			.authorizeRequests()
				.antMatchers("/auth/**", "/oauth/**")
				.permitAll()							//auth이하 요청들을 모두 허용한다.
				.anyRequest().authenticated() //위에서 허용한 요청을 제외한 모든 요청은 인증된 사용자만 허용
			.and()
				.formLogin()
				.loginPage("/auth/login");	//로그인 페이지 경로 지정
			
			/*
			.and()
				.logout()
				.logoutUrl("/logout")	//로그아웃 요청시 사용하는 url (post요청)
				.logoutSuccessUrl("/auth/login"); //로그아웃 성공 후 로그인 페이지로 이동
			*/
		
		}

}
