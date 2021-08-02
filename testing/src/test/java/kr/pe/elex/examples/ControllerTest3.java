/*
 * Spring-boot Examples
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 테스트 설정 클래스를 만든 다음, 테스트용 빈을 등록한다.
 */
@WebMvcTest(MyController.class)
class ControllerTest3 {
	@TestConfiguration
	static class TestConfig {
		@Bean
		MyService myService() {
			return new MyService() {
				@Override
				public String sayHello() {
					return "Hello, Mock";
				}
			};
		}
	}

	@Autowired
	private MockMvc mockMvc;

	@Test
	void greetingsTest() throws Exception {
		this.mockMvc.perform(get("/greetings"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, Mock")));
	}
}
