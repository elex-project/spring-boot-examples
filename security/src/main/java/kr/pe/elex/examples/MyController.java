/*
 * Spring-boot Examples
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MyController {

	@GetMapping({"/"})
	public String index() {
		return "home";
	}

	@GetMapping({"/login"})
	public String login() {
		return "login";
	}

	@GetMapping({"/info"})
	public String info() {
		return "normal_info";
	}

	@GetMapping({"/secure"})
	public String secure() {
		return "secure_info";
	}
}
