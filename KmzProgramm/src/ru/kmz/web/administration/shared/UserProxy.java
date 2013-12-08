package ru.kmz.web.administration.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserProxy implements Serializable {

	private long id;
	private String username;

	public UserProxy() {
	}

	public String getUsername() {
		return username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
