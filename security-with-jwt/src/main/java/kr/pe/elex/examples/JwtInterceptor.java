package kr.pe.elex.examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
//@Component
@Deprecated
public class JwtInterceptor implements HandlerInterceptor {
	@Autowired
	private JwtService jwtService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		log.info("Interceptor Triggered!! {}", getClass().getName());
		final String authHeader = request.getHeader("Authorization");
		if (null != authHeader) {
			Authentication authentication = jwtService.getAuthentication(authHeader);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		return true;
		//return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
