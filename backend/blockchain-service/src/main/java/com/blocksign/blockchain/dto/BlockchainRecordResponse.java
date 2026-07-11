package com.blocksign.blockchain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlockchainRecordResponse {
  private String contractId;
  private String transactionHash;
  private Long blockNumber;
  private String documentHash;
  private String network;
  private LocalDateTime submittedAt;
}
