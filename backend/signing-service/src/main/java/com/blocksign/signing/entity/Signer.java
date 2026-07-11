package com.blocksign.signing.entity;

import com.blocksign.signing.enums.SignerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "signers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Signer {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "contract_id")
  private Contract contract;

  private String userId;
  private String email;
  private String name;
  private String walletAddress;
  private String walletSignature;

  @Enumerated(EnumType.STRING)
  private SignerStatus status;

  private LocalDateTime signedAt;
}
