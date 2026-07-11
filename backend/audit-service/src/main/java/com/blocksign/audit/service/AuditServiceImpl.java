package com.blocksign.audit.service;

import com.blocksign.audit.dto.AuditEventMessage;
import com.blocksign.audit.dto.AuditEventResponse;
import com.blocksign.audit.entity.AuditEvent;
import com.blocksign.audit.repository.AuditEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditServiceImpl implements AuditService {

  private final AuditEventRepository auditEventRepository;

  @Override
  public void saveEvent(AuditEventMessage message) {
    AuditEvent event = AuditEvent.builder()
        .eventId(message.getEventId())
        .contractId(message.getContractId())
        .tenantId(message.getTenantId())
        .eventType(message.getEventType())
        .performedBy(message.getPerformedBy())
        .walletAddress(message.getWalletAddress())
        .details(message.getMetadata() != null ? message.getMetadata().toString() : null)
        .occurredAt(message.getOccurredAt())
        .build();

    auditEventRepository.save(event);
    log.info("Saved audit event: {}", message.getEventId());
  }

  @Override
  public boolean eventExists(String eventId) {
    return auditEventRepository.findByEventId(eventId).isPresent();
  }

  @Override
  public List<AuditEventResponse> getAuditTrail(String contractId) {
    return auditEventRepository.findByContractIdOrderByOccurredAtDesc(contractId)
        .stream()
        .map(this::mapToResponse)
        .collect(Collectors.toList());
  }

  private AuditEventResponse mapToResponse(AuditEvent event) {
    return AuditEventResponse.builder()
        .eventId(event.getEventId())
        .contractId(event.getContractId())
        .eventType(event.getEventType())
        .performedBy(event.getPerformedBy())
        .walletAddress(event.getWalletAddress())
        .details(event.getDetails())
        .occurredAt(event.getOccurredAt())
        .build();
  }
}
