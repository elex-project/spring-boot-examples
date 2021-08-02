/*
 * Spring-boot Examples
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import org.springframework.stereotype.Service;

@Service
public class MyService {
	public String sayHello() {
		return "Hello, World.";
	}
}
