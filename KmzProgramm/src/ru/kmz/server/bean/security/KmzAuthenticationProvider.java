package ru.kmz.server.bean.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import ru.kmz.server.bean.security.model.AuthUser;
import ru.kmz.server.utils.SHA1Utils;

@Component
public class KmzAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		password = SHA1Utils.getSHA1Hash(password);

		AuthUser user = userService.loadUserByUsername(username);

		if (user == null) {
			throw new BadCredentialsException("Username not found.");
		}

		if (!password.equals(user.getPassword())) {
			throw new BadCredentialsException("Wrong password.");
		}

		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

		return new UsernamePasswordAuthenticationToken(username, password, authorities);
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
}