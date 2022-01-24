package com.tazz0009.blockbasic;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tazz0009.blockbasic.utils.Util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class BlockbasicApplicationTests {

	@Autowired
	BlockRepository blockRepository;

	@Autowired
	BlockChainRepository blockChainRepository;
	
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
	
//	@Test
	void blockTest() throws IOException {
		try {
			BlockChain blockChain = new BlockChain();
			
			blockChain.addBlock("First Block after Genesis");
			blockChain.addBlock("Second Block after Genesis");
			blockChain.addBlock("Third Block after Genesis");
			
			for (Block block : blockChain.getBlocks()) {
				System.out.printf("Previous Hash: %s\n", Util.byteToHex(block.getPrevHash()));
				System.out.printf("Data in Block: %s\n", new String(block.getData()));
				System.out.printf("Hash: %s\n", Util.byteToHex(block.getHash()));
				System.out.printf("Nonce: %d\n", block.getNonce());
				
				Proof pow = new Proof(block);
				
				boolean validate = pow.validate(block);
				System.out.printf("Pow : %s\n", validate);
				System.out.println();
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
//	@Test
	void blockTest2() throws IOException, NoSuchAlgorithmException {
		byte[] byteArr = {};
		Block genesisBlock = new Block("Genesis", byteArr);
		Block save = blockRepository.save(genesisBlock);
		
	}

	@Test
	void blockTest3() throws IOException, NoSuchAlgorithmException {
		blockRepository.deleteAll();
		blockChainRepository.deleteAll();
	}
	
	
}
