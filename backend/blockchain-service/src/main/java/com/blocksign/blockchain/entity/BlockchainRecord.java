package com.blocksign.blockchain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "blockchain_records")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlockchainRecord {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String contractId;
  private String tenantId;
  private String documentHash;
  private String transactionHash;
  private Long blockNumber;
  private String network;
  private LocalDateTime submittedAt;
}
