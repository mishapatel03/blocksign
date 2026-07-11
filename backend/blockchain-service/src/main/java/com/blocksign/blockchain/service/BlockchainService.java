package com.blocksign.blockchain.service;

import com.blocksign.blockchain.dto.BlockchainRecordResponse;
import com.blocksign.blockchain.dto.SubmitHashRequest;

public interface BlockchainService {
  BlockchainRecordResponse submitHash(SubmitHashRequest request);
  Boolean verifyHash(String contractId, String currentHash);
  BlockchainRecordResponse getRecord(String contractId);
}
