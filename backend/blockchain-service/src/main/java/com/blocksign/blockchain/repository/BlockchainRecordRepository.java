package com.blocksign.blockchain.repository;

import com.blocksign.blockchain.entity.BlockchainRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlockchainRecordRepository extends JpaRepository<BlockchainRecord, String> {
  Optional<BlockchainRecord> findByContractId(String contractId);
}
