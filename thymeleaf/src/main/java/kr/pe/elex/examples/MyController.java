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
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class MyController {
	@Autowired
	private MyService service;

	@GetMapping({"/", "/persons"})
	public String index(Model model) {
		model.addAttribute("user", service.getUser());
		model.addAttribute("copyright", service.getCopyright());
		model.addAttribute("personList",service.getPersonList());

		return "main";
	}


}
