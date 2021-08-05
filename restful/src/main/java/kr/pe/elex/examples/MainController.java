package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Slf4j
@RestController
public class MainController {

	@GetMapping(path = "/{name}", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
	public ResponseEntity<Person> home(@PathVariable String name) {

		return ResponseEntity
				.ok(new Person(name, new Random().nextInt()));
	}
}
