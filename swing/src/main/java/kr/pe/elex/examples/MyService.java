package kr.pe.elex.examples;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MyService {
	public String getText(){
		return "Hello~";
	}
}
