package kr.pe.elex.examples;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtFilter extends GenericFilterBean {

	@Autowired
	private JwtService jwtService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		final String authHeader = httpServletRequest.getHeader("Authorization");
		if (null != authHeader) {
			try {
				Authentication authentication = jwtService.getAuthentication(authHeader);
				SecurityContextHolder.getContext().setAuthentication(authentication);

			} catch (Exception e) {
				handleException(e, response);
				return;
			}
		}

		chain.doFilter(request, response);
	}

	private void handleException(Exception e, ServletResponse response) {
		final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		final ErrorResponse error = new ErrorResponse();
		if (e instanceof IncorrectClaimException) {
			error.setErrorCode(HttpServletResponse.SC_BAD_REQUEST);
			error.setMessage(e.getMessage());
		} else if (e instanceof MissingClaimException) {
			error.setErrorCode(HttpServletResponse.SC_BAD_REQUEST);
			error.setMessage(e.getMessage());
		} else if (e instanceof ExpiredJwtException) {
			error.setErrorCode(HttpServletResponse.SC_UNAUTHORIZED);
			error.setMessage(e.getMessage());
		} else if (e instanceof SignatureException) {
			error.setErrorCode(HttpServletResponse.SC_BAD_REQUEST);
			error.setMessage(e.getMessage());
		} else if (e instanceof MalformedJwtException) {
			error.setErrorCode(HttpServletResponse.SC_BAD_REQUEST);
			error.setMessage(e.getMessage());
		} else if (e instanceof UnsupportedJwtException) {
			error.setErrorCode(HttpServletResponse.SC_BAD_REQUEST);
			error.setMessage(e.getMessage());
		} else {
			error.setErrorCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			error.setMessage(e.getMessage());
		}

		try {
			final ObjectMapper mapper = new ObjectMapper();
			httpServletResponse.setStatus(error.getErrorCode());
			httpServletResponse.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
			httpServletResponse.getWriter().write(mapper.writeValueAsString(error));
		} catch (IOException ex) {
			log.error("Oops~", ex);
		}
	}

	@Getter @Setter
	private static class ErrorResponse {
		@JsonProperty
		private int errorCode;
		@JsonProperty
		private String message;
	}
}
