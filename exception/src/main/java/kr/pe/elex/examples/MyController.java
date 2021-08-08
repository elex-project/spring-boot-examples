/*
 * Spring-boot Examples
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;

@Slf4j
@Controller
public class MyController {
	@Autowired
	private MyService service;

	@GetMapping(path = {"/m1"})
	public String index() throws Exception {
		service.doSomething("illegal");
		return "main";
	}
	@GetMapping(path = {"/m2"})
	public String index2() throws Exception {
		service.doSomething("exception");
		return "main";
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleException(Throwable e){
		log.debug("Handled by {}.", getClass().getName());
		HashMap<String,Object> resp = new HashMap<>();
		resp.put("message",e.getMessage());
		return ResponseEntity.badRequest().body(resp);
	}
}
