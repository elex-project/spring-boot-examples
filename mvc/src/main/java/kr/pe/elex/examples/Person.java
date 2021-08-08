package kr.pe.elex.examples;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Person {
	@JsonProperty
	private String name;
	@JsonProperty
	private int age;
}
