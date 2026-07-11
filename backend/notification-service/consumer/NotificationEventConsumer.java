package com.blocksign.notification.consumer;

import com.blocksign.notification.dto.NotificationEvent;
import com.blocksign.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationEventConsumer {

  private final EmailService emailService;

  // TODO: Uncomment when Kafka is running
  // @KafkaListener(topics = "document-events",
  //   groupId = "notification-service-consumer")
  public void handleDocumentEvent(NotificationEvent event) {
    log.info("Received notification event: {}", event.getType());
    // TODO: send email to signers
  }

  // TODO: Uncomment when Kafka is running
  // @KafkaListener(topics = "signing-events",
  //   groupId = "notification-service-consumer")
  public void handleSigningEvent(NotificationEvent event) {
    log.info("Received signing event: {}", event.getType());
    // TODO: send email to relevant parties
  }
}
