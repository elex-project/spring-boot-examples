/*
 * Spring-boot Examples
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping({"/my"})
public class MyController {
	@Autowired
	private MyService service;

	@GetMapping({"/"})
	public String index() {
		return "main";
	}

	@GetMapping({"/path/{id}"})
	public String withPathVar(@PathVariable String id) {
		return "main";
	}

	@GetMapping({"/request"})
	public String withReqVar(@RequestParam String id) {
		return "main";
	}

	@PostMapping({"/post"})
	public String withPostBody(@RequestBody Person person) {
		return "main";
	}

	@GetMapping({"/request/header"})
	public String withReqHeader(@RequestHeader MultiValueMap<String, String> headers) {
		return "main";
	}
}
