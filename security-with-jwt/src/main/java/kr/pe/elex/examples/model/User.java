/*
 * Spring-boot Examples
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

/**
 * UserDetails를 구현해서 데이터 모델을 만듭니다.
 */
@Table(name = "User")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class User implements UserDetails {
	@Column(nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String username;
	private String password;
	private String name;
	private String role;
	private boolean enabled;

	public User(String name, String username, String password){
		this();
		this.username = username;
		this.password = password;
		this.name = name;
		this.role = "ROLE_USER";
		this.enabled = true;

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(role));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
