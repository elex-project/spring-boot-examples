package kr.pe.elex.examples;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
//@RestControllerAdvice
@Deprecated
public class JwtExceptionHandler {

	@ExceptionHandler({IncorrectClaimException.class, MissingClaimException.class, ExpiredJwtException.class,
			SignatureException.class, MalformedJwtException.class, UnsupportedJwtException.class})
	public ResponseEntity<?> onEx(Throwable e) {
		log.error("Oops~!!!", e);

		Map<String, Object> data = new HashMap<>();
		data.put("message", e.getMessage());
		data.put("errorCode", HttpServletResponse.SC_BAD_REQUEST);
		return ResponseEntity.badRequest().body(data);
	}

}
