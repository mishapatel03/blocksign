package com.blocksign.document.entity;

import com.blocksign.document.enums.DocumentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  private String tenantId;

  @Column(nullable = false)
  private String uploadedBy;

  @Column(nullable = false)
  private String fileName;

  private Long fileSize;

  @Column(nullable = false)
  private String s3Key;

  @Column(length = 64)
  private String documentHash;

  private String blockchainTxHash;

  private Long blockNumber;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private DocumentStatus status;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @PrePersist
  void onCreate() {
    createdAt = LocalDateTime.now();
  }

  @PreUpdate
  void onUpdate() {
    updatedAt = LocalDateTime.now();
  }
}
