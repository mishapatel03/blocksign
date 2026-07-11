package com.blocksign.signing.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SigningEventProducer {

  private final KafkaTemplate<String, Object> kafkaTemplate;
  private static final String TOPIC = "signing-events";

  // TODO: Implement signing event publishing
  // public void publishSigningEvent(SigningEvent event) {
  //   kafkaTemplate.send(TOPIC, event.getContractId(), event);
  // }
}
