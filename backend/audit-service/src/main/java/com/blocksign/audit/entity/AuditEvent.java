package com.blocksign.audit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_events")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditEvent {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String eventId;
  private String contractId;
  private String tenantId;
  private String eventType;
  private String performedBy;
  private String walletAddress;
  private String details;
  private LocalDateTime occurredAt;
}
