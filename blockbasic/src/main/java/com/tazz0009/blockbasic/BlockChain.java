package com.tazz0009.blockbasic;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BlockChain {
	
	@Id
	@JsonIgnore
	private byte[] id;
	private byte[] lastHash;
	
	@Transient
	private List<Block> blocks;
	
	public BlockChain() throws NoSuchAlgorithmException, IOException {
		blocks = new ArrayList<>();
		byte[] byteArr = {};
		Block genesisBlock = new Block("Genesis", byteArr);
		blocks.add(genesisBlock);
		this.setId("lastHash".getBytes());
		this.setLastHash(genesisBlock.getHash());
	}
	
	public BlockChain(Block block) throws NoSuchAlgorithmException, IOException {
		blocks = new ArrayList<>();
		blocks.add(block);
	}
	
	public Block addBlock(String data) throws NoSuchAlgorithmException, IOException {
		Block newBlock = new Block(data, this.lastHash);
		blocks.add(newBlock);
		this.setId("lastHash".getBytes());
		this.setLastHash(newBlock.getHash());
		return newBlock;
	}
	
}
