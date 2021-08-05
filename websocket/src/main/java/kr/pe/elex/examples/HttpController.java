/*
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class HttpController {

	@GetMapping("/")
	public ModelAndView home() {
		final Map<String, Object> map = new HashMap<>();
		map.put("title", "Websocket Test");
		return new ModelAndView("home", map);
	}

}
