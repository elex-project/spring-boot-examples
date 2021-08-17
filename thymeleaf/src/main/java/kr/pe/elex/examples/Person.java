package kr.pe.elex.examples;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
	@JsonProperty
	private String name;
	@JsonProperty
	private int age;
}
