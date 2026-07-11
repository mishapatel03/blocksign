package com.blocksign.document.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRegisteredEvent {
  private String documentId;
  private String tenantId;
  private String fileName;
  private String documentHash;
  private LocalDateTime occurredAt;
}
