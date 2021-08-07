/*
 * Spring-boot Examples
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import kr.pe.elex.examples.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MyController {

	/**
	 * 로그인한 사용자 정보를 가져오려면, Authentication을 사용합니다.
	 * 또는, @AuthenticationPrincipal를 사용합니다.
	 *
	 * @param authentication
	 * @param model
	 * @return
	 */
	@GetMapping({"/"})
	public String index(Authentication authentication, Model model) {
		if (null != authentication) {
			User user = (User) (authentication.getPrincipal());
			log.info("USER: {}", user.getName());

			model.addAttribute("user", user);
		} else {
			log.info("NO USER: ");
		}
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

	/**
	 * 로그인한 사용자 정보를 가져오기 위해, @AuthenticationPrincipal를 사용합니다.
	 *
	 * @param user
	 * @param model
	 * @return
	 */
	@GetMapping({"/secure"})
	public String secure(@AuthenticationPrincipal User user, Model model) {
		if (null != user) {
			log.info("USER: {}", user.getName());
		} else {
			log.info("NO USER!!");
		}
		model.addAttribute("user", user);
		return "secure_info";
	}
}
