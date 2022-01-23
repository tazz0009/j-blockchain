package com.tazz0009.blockbasic.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

	public static byte[] getHash(byte[] input) {
		byte[] bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(input);
			bytes = md.digest();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return bytes;
	}
	
	public static String convByteArrToString(byte[] input) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length; i++) {
			sb.append(
					Integer.toString((input[i] & 0xff) + 0x100, 16).substring(1)
			);
		}
		return sb.toString();
	}
	
	public static String byteToHex(byte[] input) {
		StringBuilder sb = new StringBuilder();
		for (byte b : input) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
}
