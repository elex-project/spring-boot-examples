/*
 * Spring-boot Examples
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import kr.pe.elex.examples.model.User;
import kr.pe.elex.examples.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Application {

	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * 실제 프로젝트에서는 입력 폼을 통해서 회원 가입을 하겠지만, ...
	 *
	 */
	@Component
	public class SetupUsers implements CommandLineRunner {

		@Override
		public void run(String... args) throws Exception {
			User user = new User("Charlie", "user1", passwordEncoder.encode("pw1"));
			repository.save(user);
		}

	}
}
