package com.blocksign.document.kafka;

import com.blocksign.document.entity.Document;
import com.blocksign.document.event.DocumentRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DocumentEventProducer {

  private final KafkaTemplate<String, Object> kafkaTemplate;
  private static final String TOPIC = "document-events";

  public void publishDocumentRegistered(Document document) {
    DocumentRegisteredEvent event = DocumentRegisteredEvent.builder()
        .documentId(document.getId())
        .tenantId(document.getTenantId())
        .fileName(document.getFileName())
        .documentHash(document.getDocumentHash())
        .occurredAt(java.time.LocalDateTime.now())
        .build();

    log.info("Publishing document registered event: {}", event.getDocumentId());

    // TODO: Uncomment when Kafka is running
    // kafkaTemplate.send(TOPIC, document.getTenantId(), event);
  }
}
