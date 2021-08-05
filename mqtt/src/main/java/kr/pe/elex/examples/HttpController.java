/*
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class HttpController {

	@Autowired
	private MqttPublisher mqtt;

	@GetMapping("/")
	public ModelAndView home() {
		final Map<String, Object> map = new HashMap<>();
		return new ModelAndView("home", map);
	}

	@GetMapping("/publish")
	public void publish(@RequestParam String topic, @RequestParam String message) {
		log.info("I'll publish a message: {} {}", topic, message);

		mqtt.publish(topic, message);
	}
}
