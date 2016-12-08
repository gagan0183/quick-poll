package com.spring.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.spring.model.User;
import com.spring.rep.UserRepository;

@Component
public class QuickPollUserDetailsService implements UserDetailsService {

	@Inject
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User %username wont exist", username));
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		if (user.isAdmin()) {
			authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
		}
		return new org.springframework.security.core.userdetails.User(username, user.getPasssword(), authorities);
	}
}
