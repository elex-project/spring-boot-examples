package kr.pe.elex.examples;

import kr.pe.elex.examples.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping({"/api"})
public class MyRestController {
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/signin")
	public ResponseEntity<Void> signIn(@RequestParam String userId, @RequestParam String userPw) {
		/*
		사용자 인증과정을 수동으로 진행
		 */
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(userId, userPw);
		Authentication authentication = authenticationManager
				.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		/*
		인증 완료 후 토큰 발행
		 */
		String token = jwtService.generateToken(authentication);
		return ResponseEntity.ok()
				.header("Authentication", "Bearer " + token)
				.body(null);
	}

	@GetMapping("/user/{userName}")
	public ResponseEntity<User> getUser(@PathVariable String userName) {
		return ResponseEntity.ok((User) userService.loadUserByUsername(userName));
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> onException(Throwable e) {
		log.error("ERROR!!!", e);
		return ResponseEntity.internalServerError().body(e.getMessage());
	}
}
