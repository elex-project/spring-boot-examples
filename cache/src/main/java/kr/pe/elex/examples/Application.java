/*
 * Spring-boot Examples
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableCaching
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Component
	public class MyRunner implements CommandLineRunner {
		@Autowired
		private PersonRepository repository;

		@Override
		public void run(String... args) throws Exception {
			repository.deleteAll();

			Person charlie = new Person();
			charlie.setName("Charlie");
			charlie.setAge(11);
			repository.save(charlie);

			Person steve = new Person();
			steve.setName("Steve");
			steve.setAge(34);
			repository.save(steve);
		}
	}
}
