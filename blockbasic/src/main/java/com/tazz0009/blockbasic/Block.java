package com.tazz0009.blockbasic;

import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Block {
	
	@Id
	@JsonIgnore
	private byte[] id;
	private byte[] hash;
	private String data;
	private byte[] prevHash;
	private BigInteger nonce;
	
	public Block() {
	}
	
	public Block(byte[] id, byte[] hash, String data, byte[] prevHash, BigInteger nonce) {
		super();
		this.id = id;
		this.hash = hash;
		this.data = data;
		this.prevHash = prevHash;
		this.nonce = nonce;
	}

	public Block(byte[] hash, String data, byte[] prevHash) {
		super();
		this.id = hash;
		this.hash = hash;
		this.data = data;
		this.prevHash = prevHash;
	}
	
	public Block(String data, byte[] prevHash) throws NoSuchAlgorithmException, IOException {
		super();
		this.hash = new byte[] {};
		this.data = data;
		this.prevHash = prevHash;
		this.nonce = BigInteger.valueOf(0);
		
		Proof proof = new Proof(this);
		Map<String, Object> value = proof.run(this.nonce);
		
		this.nonce = (BigInteger) value.get("nonce");
		this.hash = (byte[]) value.get("hash");
		this.id = (byte[]) value.get("hash");
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
		sb.append(this.data);
		sb.append("\n");
		sb.append("hash: ");
		sb.append(hexStringHash);
		sb.append("]\n");
		return sb.toString();
	}
}
