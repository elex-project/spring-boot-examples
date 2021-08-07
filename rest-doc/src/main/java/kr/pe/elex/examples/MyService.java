package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class MyService {
	private Set<Person> data;

	@PostConstruct
	private void init() {
		data = new HashSet<>();
		data.add(new Person("Charlie", 11));
	}

	public Person getPerson(String name) {
		for (Person person : data) {
			if (person.getName().equals(name)) {
				return person;
			}
		}
		return null;
	}
	public void addPerson(Person person){
		data.add(person);
	}
}
