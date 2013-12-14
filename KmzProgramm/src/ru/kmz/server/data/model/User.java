package ru.kmz.server.data.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import ru.kmz.server.utils.SHA1Utils;
import ru.kmz.web.administration.shared.UserProxy;

@PersistenceCapable
public class User {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private String username;
	@Persistent
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = SHA1Utils.getSHA1Hash(password);
	}

	public UserProxy asProxy() {
		UserProxy proxy = new UserProxy();
		proxy.setId(id);
		proxy.setUsername(username);
		return proxy;
	}

}
