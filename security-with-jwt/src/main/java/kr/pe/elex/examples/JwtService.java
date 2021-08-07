package kr.pe.elex.examples;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Slf4j
@Service
public class JwtService {

	private final byte[] key = new byte[32];

	@PostConstruct
	private void init() {
		new Random().nextBytes(key);
		log.info("JWT Key = {}", key);
	}

	public String generateToken(final Authentication authentication) {
		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.setIssuer("Elex")
				.setSubject(authentication.getName())
				.setExpiration(Date.from(Instant.now().plus(3, ChronoUnit.HOURS)))
				.claim("authorities", authoritiesToString(authentication.getAuthorities()))
				.signWith(Keys.hmacShaKeyFor(key))
				.compact();
	}

	public Jws<Claims> parseToken(final String token)
			throws UnsupportedJwtException, MalformedJwtException, SignatureException, ExpiredJwtException,
			MissingClaimException, IncorrectClaimException {

		return Jwts.parserBuilder()
				.setSigningKey(key)
				.requireIssuer("Elex") // 토큰의 Issuer 일치 여부 확인
				.build()
				.parseClaimsJws(parseHeader(token));
	}

	public Authentication getAuthentication(String token)
			throws UnsupportedJwtException, MalformedJwtException, SignatureException, ExpiredJwtException,
			MissingClaimException, IncorrectClaimException {
		return getAuthentication(parseToken(token).getBody());
	}

	public Authentication getAuthentication(Claims claims) {
		//User principal = (User) userService.loadUserByUsername(claims.getSubject());
		return new UsernamePasswordAuthenticationToken(claims.getSubject(), claims,
				authoritiesFromString(claims.get("authorities", String.class)));
	}

	private static String authoritiesToString(Collection<? extends GrantedAuthority> authorities) {
		StringJoiner joiner = new StringJoiner(",");
		for (GrantedAuthority authority : authorities) {
			joiner.add(authority.getAuthority());
		}
		return joiner.toString();
	}

	private static Collection<? extends GrantedAuthority> authoritiesFromString(String authorities) {
		List<GrantedAuthority> list = new ArrayList<>();
		for (String authority : authorities.split(",")) {
			list.add(new SimpleGrantedAuthority(authority));
		}
		return list;
	}

	private static String parseHeader(final String authorizationHeader)
			throws MalformedJwtException {
		final String[] authentication = authorizationHeader.split(" ");
		if (authentication.length == 2 && authentication[0].matches("[bB]earer")) {
			return authentication[1];
		} else if (authentication.length == 1) {
			return authentication[0];
		} else {
			throw new MalformedJwtException("Authentication Header param must be started with 'Bearer ': " + authorizationHeader);
		}
	}
}
