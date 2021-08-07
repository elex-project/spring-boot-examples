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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	@Autowired
	private JwtFilter jwtFilter;
	//@Autowired
	//private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Override
	protected void configure(@NotNull HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/info", "/h2-console").permitAll() // 아무나 접근 가능
				.antMatchers("/h2-console/**").permitAll() // H2콘솔을 쓰기 위해 추가했음
				.antMatchers("/api/signin").permitAll()
				.antMatchers("/api/**").authenticated()

				//.antMatchers("/admin").hasAnyRole("ADMIN")
				.anyRequest().authenticated() // 그 외에는 인증해야 접근 가능
				/*.and()
				.formLogin()// 커스텀 로그인 폼
				.loginPage("/login")
				.usernameParameter("user_id") // 로그인 폼 매개변수명 지정
				.passwordParameter("user_pw")
				.permitAll()
				.and()
				.logout()// 로그아웃
				.logoutSuccessUrl("/")
				.permitAll()*/
				//.and().httpBasic();
				.and().csrf().ignoringAntMatchers("/h2-console/**")// H2콘솔을 쓰기 위해 추가했음
				.and().headers().frameOptions().sameOrigin()// H2콘솔을 쓰기 위해 추가했음
				.and().csrf().ignoringAntMatchers("/api/**")
				.and()
				//.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
				//.and()
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
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

	/**
	 * 인증과정을 수동으로 진행하기 위해서 AuthenticationManager를 빈에 등록 시켜야 한다.
	 * @return
	 * @throws Exception
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
