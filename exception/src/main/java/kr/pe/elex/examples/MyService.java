package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyService {
	public String doSomething(String param) throws IllegalArgumentException, Exception {
		if ("illegal" == param) throw new IllegalArgumentException("Oops~");
		if ("exception" == param) throw new Exception("Oops~");
		return "Hello";
	}
}
