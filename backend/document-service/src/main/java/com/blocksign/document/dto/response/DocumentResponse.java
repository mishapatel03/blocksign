package com.blocksign.document.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class DocumentResponse {
  private String id;
  private String fileName;
  private String status;
  private String documentHash;
  private String blockchainTxHash;
  private Long blockNumber;
  private LocalDateTime createdAt;
  private List<SignerResponse> signers;
}
