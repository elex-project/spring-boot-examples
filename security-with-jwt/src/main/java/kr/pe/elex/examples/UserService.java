/*
 * Spring-boot Examples
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import kr.pe.elex.examples.model.User;
import kr.pe.elex.examples.model.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetailsService를 구현해서 사용자를 조회할 수 있는 서비스를 만듭니다.
 */
@Slf4j
@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final User user = repository.findByUsername(username);
		if (null == user) {
			throw new UsernameNotFoundException("Unable to find a user with " + username);
		} else {
			return user;
		}
	}

}
