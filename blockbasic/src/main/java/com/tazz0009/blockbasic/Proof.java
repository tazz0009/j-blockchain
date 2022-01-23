package com.tazz0009.blockbasic;

import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import com.tazz0009.blockbasic.utils.Util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Proof {

	final int difficulty = 12;
	
	private Block block;
	private BigInteger target;
	
	public Proof(Block b) {
		this.block = b;
		target = new BigInteger("2").pow(256-difficulty);
	}

	public Map<String, Object>  run(BigInteger nonce) throws IOException, NoSuchAlgorithmException {
		byte[] hash = null;
		BigInteger max = BigInteger.valueOf(Long.MAX_VALUE);
		while (nonce.compareTo(max) == -1) {
			byte[] data = this.initData(nonce);
			hash = Util.getHash(data);
			BigInteger intHash = new BigInteger(hash);
			if (intHash.compareTo(target) == -1 && intHash.compareTo(BigInteger.valueOf(0)) == 1) {
				break;
			} else {
				nonce = nonce.add(BigInteger.valueOf(1));
			}
		}
//		System.out.println("\nnonce:" + nonce.toString());
		Map<String, Object> value = new HashMap<>();
		value.put("nonce", nonce);
		value.put("hash", hash);
		return value;
	}

	private byte[] initData(BigInteger nonce) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(block.getPrevHash());
		baos.write(block.getData());
		baos.write(nonce.toByteArray());
		baos.write(this.target.toByteArray());
		baos.close();
		return  baos.toByteArray();
	}
	
	private String byteToHex(byte[] bArr) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bArr) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
	
}
