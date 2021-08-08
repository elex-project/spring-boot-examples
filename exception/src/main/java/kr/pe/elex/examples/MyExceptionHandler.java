package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Throwable e){
		log.debug("Handled by {}.", getClass().getName());
		HashMap<String,Object> resp = new HashMap<>();
		resp.put("message",e.getMessage());
		return ResponseEntity.badRequest().body(resp);
	}
}
