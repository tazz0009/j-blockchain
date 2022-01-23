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
import org.springframework.boot.test.context.SpringBootTest;

import com.tazz0009.blockbasic.utils.Util;

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
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void test() throws NoSuchAlgorithmException, IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(new byte[] {});
		baos.write("Genesis".getBytes());
		baos.write(BigInteger.valueOf(19304).toByteArray());
		baos.write(new BigInteger("2").pow(256-12).toByteArray());
		baos.close();
		String hashStr1 = Util.convByteArrToString(Util.getHash(baos.toByteArray()));
		String hashStr2 = Util.byteToHex(Util.getHash(baos.toByteArray()));
		if (hashStr1.equals(hashStr2)) {
			System.out.println("same!!");
		}
	}

}
