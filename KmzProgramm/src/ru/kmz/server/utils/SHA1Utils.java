package ru.kmz.server.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Utils {

	public static String getSHA1Hash(String str) {
		byte[] convertme = "ASD".getBytes();
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String ans = byteArrayToHexString(md.digest(convertme));
		return ans;
	}

	private static String byteArrayToHexString(byte[] b) {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}
}
