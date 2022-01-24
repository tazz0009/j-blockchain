package com.tazz0009.blockbasic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockChainRepository extends JpaRepository<BlockChain, byte[]> {

}
