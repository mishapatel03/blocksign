package com.blocksign.audit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditEventMessage {
  private String eventId;
  private String contractId;
  private String tenantId;
  private String eventType;
  private String performedBy;
  private String walletAddress;
  private Map<String, Object> metadata;
  private LocalDateTime occurredAt;
}
