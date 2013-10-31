package ru.kmz.web.common.client.data;

public class KeyValueData {

	private String key;
	private String value;

	public KeyValueData(String ket, String value) {
		this.key = ket;
		this.value = value;
	}

	public String getKey() {
		return key;
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
		if (obj instanceof KeyValueData) {
			return ((KeyValueData) obj).key.equals(key);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return key!=null? key.hashCode() : 0;
	}
}
