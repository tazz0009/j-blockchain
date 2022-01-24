package com.tazz0009.blockbasic;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends JpaRepository<Block, byte[]> {

	Optional<Block> findByPrevHash(byte[] prevHash);

	Optional<Block> findByHash(byte[] hash);

}
