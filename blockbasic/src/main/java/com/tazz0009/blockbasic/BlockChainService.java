package com.tazz0009.blockbasic;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class BlockChainService {

	private final BlockRepository blockRepository;
	private final BlockChainRepository blockChainRepository;

	public void saveLastHash(BlockChain blockChain) {
		blockRepository.save(blockChain.getBlocks().get(blockChain.getBlocks().size()-1));
		blockChainRepository.save(blockChain);		
	}

	public Optional<BlockChain> find() {
		Optional<BlockChain> blockChain = blockChainRepository.findById("lastHash".getBytes());	
		return blockChain;
	}
	
}
