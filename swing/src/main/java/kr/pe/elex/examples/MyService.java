package kr.pe.elex.examples;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class MyService {
	@Getter @Setter
	private String something = "Something";

	public String getText() {
		return "Hello~";
	}

}
