/*
 * Spring-boot Examples
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import kr.pe.elex.examples.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class MyController {
	@GetMapping({"/"})
	public String index() {
		return "home";
	}

	@PostMapping(value = {"/test1"})
	@ResponseBody
	public ResponseEntity<String> test1(@Valid @RequestBody Person person, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			log.info("ERROR: {}", person);
			return ResponseEntity.badRequest().body("BAD");
		} else {
			log.info("OK: {}", person);
			return ResponseEntity.ok("GOOD");
		}
	}

	@PostMapping(value = {"/test2"})
	@ResponseBody
	public ResponseEntity<String> test2(@Valid @RequestBody Person person) {

		return ResponseEntity.ok("GOOD");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseBody
	public Map<String, String> handleValidation(MethodArgumentNotValidException e) {
		Map<String, String> result = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach(item -> {
			result.put(((FieldError) item).getField(),
					item.getDefaultMessage());
		});
		return result;
	}
}
