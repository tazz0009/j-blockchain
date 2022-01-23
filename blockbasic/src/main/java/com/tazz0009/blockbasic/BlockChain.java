package com.tazz0009.blockbasic;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BlockChain {
	
	private List<Block> blocks;
	
	public BlockChain() throws NoSuchAlgorithmException {
		blocks = new ArrayList<>();
		byte[] byteArr = {};
		Block genesisBlock = new Block("Genesis".getBytes(), byteArr);
		blocks.add(genesisBlock);
	}
	
	public void addBlock(String data) throws NoSuchAlgorithmException {
		Block lastBlock = blocks.get(blocks.size()-1);
		Block newBlock = new Block(data.getBytes(), lastBlock.getHash());
		blocks.add(newBlock);
	}
	
}
