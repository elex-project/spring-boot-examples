package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
//@Order(3)
public class MyFilter3 implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info("I'm a filter 3.");
		chain.doFilter(request, response);
	}
}
