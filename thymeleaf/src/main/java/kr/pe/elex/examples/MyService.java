package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class MyService {
	public Person getUser() {
		return new Person("Charlie", 14);
	}

	public List<Person> getPersonList() {
		return Arrays.asList(
				new Person("Charlie", 14),
				new Person("Steve", 34),
				new Person("Jane", 22));
	}

	public Copyright getCopyright() {
		return new Copyright();
	}
}
