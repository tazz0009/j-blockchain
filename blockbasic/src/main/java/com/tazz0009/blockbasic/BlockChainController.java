package com.tazz0009.blockbasic;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("blockchain")
public class BlockChainController {

	@Autowired
	private BlockService blockService;

	@Autowired
	private BlockChainService blockChainService;
	
	@GetMapping("/ready")
	public BlockChain ready() throws NoSuchAlgorithmException, IOException {
//		BlockChain blockChain = new BlockChain();
		log.info("ready!!!");
		BlockChain blockChain = initBlockChain();
		return blockChain;
	}
	
	@GetMapping("/addBlock/{data}")
	public List<Block> addBlock(@PathVariable("data") String data) throws NoSuchAlgorithmException, IOException {
		log.info("addBlock!!!");
		BlockChain blockChain = initBlockChain();
		
		blockChain.addBlock(data);
		blockChainService.saveLastHash(blockChain);
		List<Block> blocks = getBlocks(blockChain.getLastHash());
		return blocks;
	}
	
	private List<Block> getBlocks(byte[] lastHash) {
		List<Block> blocks = new ArrayList<>();
		Optional<Block> findBlock = blockService.findBlockById(lastHash);
		while (!findBlock.isEmpty()) {
			blocks.add(findBlock.get());
			if (findBlock.get().getPrevHash().length == 0) {
				break;
			}
			findBlock = blockService.findBlockById(findBlock.get().getPrevHash());
		}
		return blocks;
	}

	@GetMapping("/printBlocks")
	public List<Block> printBlocks() throws NoSuchAlgorithmException, IOException {
		log.info("printBlocks!!!");
		BlockChain blockChain = initBlockChain();
		List<Block> blocks  = getBlocks(blockChain.getLastHash());
		
		return blocks;
	}
	

	private BlockChain initBlockChain() throws NoSuchAlgorithmException, IOException {
		Optional<BlockChain> findBlockChain = blockChainService.find();
		BlockChain blockChain = null;
		if (findBlockChain.isEmpty()) {
			blockChain = new BlockChain();
			blockChainService.saveLastHash(blockChain);
		} else {
			blockChain = findBlockChain.get();
		}
		
		return blockChain;
	}
	
}
