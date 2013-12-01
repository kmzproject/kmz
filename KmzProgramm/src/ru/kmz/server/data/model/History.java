package ru.kmz.server.data.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import ru.kmz.web.common.shared.HistoryProxy;

@PersistenceCapable
public class History {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private Date date;

	@Persistent
	private String name;

	@Persistent
	private String comment;

	@Persistent
	private String user;

	@Persistent
	private Long objectId;

	public void setDate(Date date) {
		this.date = date;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public HistoryProxy asProxy() {
		return new HistoryProxy(id, name, comment, new Date(date.getTime()), user);
	}

}
