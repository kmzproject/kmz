package ru.kmz.web.common.client.data;

public class KeyValueData<T> {

	private T key;
	private String value;

	public KeyValueData(T key, String value) {
		this.key = key;
		this.value = value;
	}

	public KeyValueData(T key) {
		this.key = key;
		this.value = key.toString();
	}

	public T getKey() {
		return key;
	}

	public String getKeyStr() {
		return key.toString();
	}

	public String getValue() {
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof KeyValueData) {
			if (key == null || ((KeyValueData<T>) obj).key == null)
				return false;
			return ((KeyValueData<T>) obj).key.equals(key);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return key != null ? key.hashCode() : 0;
	}
}
