package com.blocksign.blockchain.service;

import com.blocksign.blockchain.dto.BlockchainRecordResponse;
import com.blocksign.blockchain.dto.SubmitHashRequest;
import com.blocksign.blockchain.entity.BlockchainRecord;
import com.blocksign.blockchain.repository.BlockchainRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlockchainServiceImpl implements BlockchainService {

  private final BlockchainRecordRepository repository;

  @Override
  public BlockchainRecordResponse submitHash(SubmitHashRequest request) {
    log.info("Submitting hash to blockchain: {}", request.getDocumentHash());

    // TODO: Connect Web3j to Infura and submit real tx
    // Web3j web3 = Web3j.build(new HttpService(infuraUrl));

    BlockchainRecord record = BlockchainRecord.builder()
        .contractId(request.getContractId())
        .tenantId(request.getTenantId())
        .documentHash(request.getDocumentHash())
        .transactionHash("0xmock-" + UUID.randomUUID())
        .blockNumber(19823456L)
        .network("sepolia")
        .submittedAt(LocalDateTime.now())
        .build();

    repository.save(record);

    return mapToResponse(record);
  }

  @Override
  public Boolean verifyHash(String contractId, String currentHash) {
    // TODO: Fetch from blockchain and compare
    return true;
  }

  @Override
  public BlockchainRecordResponse getRecord(String contractId) {
    return repository.findByContractId(contractId)
        .map(this::mapToResponse)
        .orElseThrow(() -> new RuntimeException("No blockchain record found"));
  }

  private BlockchainRecordResponse mapToResponse(BlockchainRecord r) {
    return BlockchainRecordResponse.builder()
        .contractId(r.getContractId())
        .transactionHash(r.getTransactionHash())
        .blockNumber(r.getBlockNumber())
        .documentHash(r.getDocumentHash())
        .network(r.getNetwork())
        .submittedAt(r.getSubmittedAt())
        .build();
  }
}
