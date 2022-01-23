package com.tazz0009.blockbasic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.ArrayUtils;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Block {
	
	private byte[] hash;
	private byte[] data;
	private byte[] prevHash;
	
	public Block(byte[] hash, byte[] data, byte[] prevHash) {
		super();
		this.hash = hash;
		this.data = data;
		this.prevHash = prevHash;
	}
	
	public Block(byte[] data, byte[] prevHash) throws NoSuchAlgorithmException {
		super();
		this.data = data;
		this.prevHash = prevHash;
		
		byte[] addAll = ArrayUtils.addAll(data, prevHash);
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(addAll);
		this.hash = md.digest();
	}
	
	@Override
	public String toString() {
		String hexStringPrevHash = ByteArrayUtil.toHexString(prevHash);
		
		String hexStringHash = ByteArrayUtil.toHexString(hash);
		StringBuilder sb = new StringBuilder();
		sb.append("\n[");
		sb.append("prevHash: ");
		sb.append(hexStringPrevHash);
		sb.append("\n");
		sb.append("data: ");
		sb.append(new String(data));
		sb.append("\n");
		sb.append("hash: ");
		sb.append(hexStringHash);
		sb.append("]\n");
		return sb.toString();
	}
}
