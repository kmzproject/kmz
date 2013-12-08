package ru.kmz.server.bean.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.kmz.server.bean.security.model.AuthUser;
import ru.kmz.server.bean.security.model.Role;
import ru.kmz.server.data.model.User;
import ru.kmz.server.data.utils.UserDataUtils;

@Service
//@Transactional
public class UserService implements UserDetailsService {

	@Override
	public AuthUser loadUserByUsername(final String username) throws UsernameNotFoundException {
		if (username == null) {
			return null;
		}
		UserDataUtils.checkAdmin();
		User user = UserDataUtils.getUser(username);
		if (user == null) {
			return null;
		}
		AuthUser authUser = new AuthUser(user);
		Role r = new Role();
		r.setName("ROLE_USER");
		List<Role> roles = new ArrayList<Role>();
		roles.add(r);
		authUser.setAuthorities(roles);
		return authUser;
	}
}