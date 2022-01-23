package com.tazz0009.blockbasic;

import java.security.NoSuchAlgorithmException;

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
	void blockTest() {
		try {
			BlockChain blockChain = new BlockChain();
			
			blockChain.addBlock("First Block after Genesis");
			blockChain.addBlock("Second Block after Genesis");
			blockChain.addBlock("Third Block after Genesis");
			
			
			System.out.println(blockChain);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
