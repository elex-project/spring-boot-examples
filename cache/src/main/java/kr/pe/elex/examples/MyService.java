package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MyService {
	@Autowired
	private PersonRepository repository;

	@Cacheable("person")
	public Optional<Person> getItem(long at) {
		log.info("Calling a repository method!");
		return repository.findById(at);
	}

	public void addItem(Person item) {
		repository.save(item);
	}
}
