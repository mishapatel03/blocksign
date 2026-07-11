package com.blocksign.signing.entity;

import com.blocksign.signing.enums.ContractStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contracts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

  @Id
  private String id;

  private String tenantId;

  @Enumerated(EnumType.STRING)
  private ContractStatus status;

  @Version
  private Long version;

  private LocalDateTime createdAt;
  private LocalDateTime executedAt;
  private LocalDateTime deadlineAt;

  @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Signer> signers = new ArrayList<>();
}
