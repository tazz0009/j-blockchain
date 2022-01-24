package com.tazz0009.blockbasic;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class BlockService {

	private final BlockRepository blockRepository;
	
	public void saveBlock(Block block) {
		Block savedBlock = blockRepository.save(block);
		log.info("savedBlock:%s", savedBlock.toString());
	}

	public Optional<Block> findByPrevHash(byte[] prevHash) {
		return blockRepository.findByPrevHash(prevHash);
	}

	public Optional<Block> findBlockById(byte[] hash) {
		return blockRepository.findByHash(hash);
	}

}
