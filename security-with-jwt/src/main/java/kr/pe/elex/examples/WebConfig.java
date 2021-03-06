/*
 * Spring-boot Examples
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import kr.pe.elex.examples.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {
	//@Autowired
	//private JwtInterceptor jwtInterceptor;
	/*
	@Override
	public void addViewControllers(@NotNull ViewControllerRegistry registry) {
		registry.addViewController("/login");
	}
	*/

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//WebMvcConfigurer.super.addInterceptors(registry);
		//registry.addInterceptor(jwtInterceptor)
				//.pathMatcher(new AntPathMatcher())
				//.addPathPatterns("/**")
				//.excludePathPatterns("/api/signin")
		;
		//registry.addInterceptor(new SampleInterceptor())
		//		.addPathPatterns("/hello/**")
				//.pathMatcher(new AntPathMatcher())
		;
	}
}
