package ru.kmz.server.bean.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.kmz.server.bean.security.model.Role;
import ru.kmz.server.bean.security.model.User;

@Service
//@Transactional
public class UserService implements UserDetailsService {

//	@Autowired
//	private UserDao userDao;

	@Override
	public User loadUserByUsername(final String username) throws UsernameNotFoundException {
		if (username == null) {
			return null;
		}
		if (!username.equals("max") && !username.equals("user")) {
			return null;
		}
		User user = new User();
		user.setFirstName(username);
		user.setLastName(username);
		user.setUsername(username);
		user.setPassword("12345");
		Role r = new Role();
		r.setName("ROLE_USER");
		List<Role> roles = new ArrayList<Role>();
		roles.add(r);
		user.setAuthorities(roles);
		return user;
	}
}