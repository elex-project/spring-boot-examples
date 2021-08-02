/*
 * Spring-boot Examples
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

	@Autowired
	private MyService service;

	@GetMapping({"/"})
	public String index(Model model) {
		model.addAttribute("name", "World");
		return "main";
	}

	@GetMapping({"/{name}"})
	public String index(@PathVariable @ModelAttribute String name) {
		if (null == name) name = "World";
		return "main";
	}

	@GetMapping("/greetings")
	@ResponseBody
	public String greetings() {
		return service.sayHello();
	}
}
