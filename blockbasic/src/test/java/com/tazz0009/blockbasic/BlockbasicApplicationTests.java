package com.tazz0009.blockbasic;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class BlockbasicApplicationTests {

//	@Test
//	void contextLoads() {
//		log.info("test!!");
//		String msg = "Hello world";
//		StringBuilder sb = new StringBuilder();
//		try {
//			MessageDigest md = MessageDigest.getInstance("SHA-256");
//			md.update(msg.getBytes());
//			
//			for (byte b : md.digest()) {
//				sb.append(String.format("%02x", b));
//			}
//			
//			System.out.println(sb.toString());
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//		
//	}
	
	@Test
	void blockTest() throws IOException {
		try {
			BlockChain blockChain = new BlockChain();
			
			blockChain.addBlock("First Block after Genesis");
			blockChain.addBlock("Second Block after Genesis");
			blockChain.addBlock("Third Block after Genesis");
			
			for (Block block : blockChain.getBlocks()) {
				System.out.printf("Previous Hash: %s\n", byteToHex(block.getPrevHash()));
				System.out.printf("Data in Block: %s\n", new String(block.getData()));
				System.out.printf("Hash: %s\n", byteToHex(block.getHash()));
				System.out.printf("Nonce: %d\n", block.getNonce());
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public String byteToHex(byte[] bArr) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bArr) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
	
//	@Test
	void test() {
		BigInteger nonce = BigInteger.valueOf(10);
		BigInteger target = new BigInteger("2").pow(256-18);
		System.out.printf("nonce : %d\n", nonce);
		System.out.printf("nonce : %s\n", byteToHex(nonce.toByteArray()));
		System.out.printf("ccc : %d\n", target);
		System.out.println(byteToHex(target.toByteArray()));
		System.out.printf("bbbb : %s\n", byteToHex(target.toByteArray()));
		byte[] array = ByteBuffer.allocate(Long.BYTES).putLong(100).array();
		System.out.printf("aaaa : %s\n", byteToHex(array));
	}

}
