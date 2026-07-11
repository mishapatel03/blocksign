package com.blocksign.audit.consumer;

import com.blocksign.audit.dto.AuditEventMessage;
import com.blocksign.audit.service.AuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditEventConsumer {

  private final AuditService auditService;

  // TODO: Uncomment when Kafka is running
  // @KafkaListener(topics = "signing-events",
  //   groupId = "audit-service-consumer",
  //   containerFactory = "kafkaListenerContainerFactory")
  public void handleSigningEvent(AuditEventMessage message,
                                 Acknowledgment acknowledgment) {

    log.info("Received signing event: {}", message.getEventId());

    // Idempotency check
    if (auditService.eventExists(message.getEventId())) {
      log.info("Duplicate event, skipping: {}", message.getEventId());
      acknowledgment.acknowledge();
      return;
    }

    auditService.saveEvent(message);
    acknowledgment.acknowledge();
  }

  // TODO: Uncomment when Kafka is running
  // @KafkaListener(topics = "document-events",
  //   groupId = "audit-service-consumer",
  //   containerFactory = "kafkaListenerContainerFactory")
  public void handleDocumentEvent(AuditEventMessage message,
                                  Acknowledgment acknowledgment) {

    log.info("Received document event: {}", message.getEventId());

    // Idempotency check
    if (auditService.eventExists(message.getEventId())) {
      log.info("Duplicate event, skipping: {}", message.getEventId());
      acknowledgment.acknowledge();
      return;
    }

    auditService.saveEvent(message);
    acknowledgment.acknowledge();
  }
}
