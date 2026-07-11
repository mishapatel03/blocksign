package com.blocksign.notification.dto;

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
public class NotificationEvent {
  private String eventId;
  private String type;
  private String contractId;
  private String tenantId;
  private String recipientEmail;
  private String recipientName;
  private Map<String, Object> metadata;
  private LocalDateTime occurredAt;
}
