package ru.kmz.web.common.client.data;

import com.google.gwt.text.client.IntegerParser;

public class KeyValueData {

	private String key;
	private long keyLong;
	private String value;

	public KeyValueData(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public KeyValueData(long key, String value) {
		this.key = "" + key;
		this.keyLong = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public long getKeyLong() {
		return keyLong;
	}

	public String getValue() {
		return value;
	}

	public KeyValueData(String value) {
		this.key = value;
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof KeyValueData) {
			if (key == null || ((KeyValueData) obj).key == null)
				return false;
			return ((KeyValueData) obj).key.equals(key);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return key != null ? key.hashCode() : 0;
	}
}
