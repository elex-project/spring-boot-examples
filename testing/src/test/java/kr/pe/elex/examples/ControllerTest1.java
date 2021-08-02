/*
 * Spring-boot Examples
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * MvcMock을 사용해서 웹 요청과 응답을 테스트할 수 있다.
 */
@SpringBootTest//(classes = Application.class)
@AutoConfigureMockMvc
class ControllerTest1 {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getTest() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, World")));
	}

	@Test
	void getTest2() throws Exception {
		this.mockMvc.perform(get("/charlie"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, charlie")));
	}

	@Test
	void postTest() throws Exception {
		this.mockMvc.perform(post("/"))
				.andDo(print())
				.andExpect(status().isMethodNotAllowed());
	}
}
