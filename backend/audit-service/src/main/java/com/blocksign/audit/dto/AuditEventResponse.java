package com.blocksign.audit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditEventResponse {
  private String eventId;
  private String contractId;
  private String eventType;
  private String performedBy;
  private String walletAddress;
  private String details;
  private LocalDateTime occurredAt;
}
