/*
 * Spring-boot Examples
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Override
	protected void configure(@NotNull HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/info", "/h2-console").permitAll() // 아무나 접근 가능
				.antMatchers("/h2-console/**").permitAll() // H2콘솔을 쓰기 위해 추가했음
				//.antMatchers("/admin").hasAnyRole("ADMIN")
				.anyRequest().authenticated() // 그 외에는 인증해야 접근 가능
				.and()
				.formLogin()// 커스텀 로그인 폼
				.loginPage("/login")
				.usernameParameter("user_id") // 로그인 폼 매개변수명 지정
				.passwordParameter("user_pw")
				.permitAll()
				.and()
				.logout()// 로그아웃
				.logoutSuccessUrl("/")
				.permitAll()
				//.and().httpBasic();
				.and().csrf().ignoringAntMatchers("/h2-console/**")// H2콘솔을 쓰기 위해 추가했음
				.and().headers().frameOptions().sameOrigin()// H2콘솔을 쓰기 위해 추가했음
				//.and().csrf().disable()
		;
	}


	@Override
	protected void configure(@NotNull AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService)
				.passwordEncoder(passwordEncoder())
		/*auth.inMemoryAuthentication()
				.withUser("user1")
				.password("{noop}pw1")
				.authorities("ROLE_USER")
				.and()
				.withUser("user2")
				.password("{noop}pw2")
				.authorities("ROLE_USER")*/
		;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
