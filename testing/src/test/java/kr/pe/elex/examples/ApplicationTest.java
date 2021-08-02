/*
 * Spring-boot Examples
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * 통합 테스트. 모든 빈을 로드한 다음, 테스트가 수행된다.
 */
@SpringBootTest(classes = Application.class)
class ApplicationTest {
	@Autowired
	private MyController controller;

	@Test
	void test() {
		assertThat(controller).isNotNull();
	}
}
