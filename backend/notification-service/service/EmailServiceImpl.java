package com.blocksign.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender mailSender;

  @Override
  public void sendSigningNotification(String toEmail, String toName, String documentName) {
    // TODO: Implement real email sending
    log.info("Sending signing notification to {} for document: {}", toEmail, documentName);
  }

  @Override
  public void sendDocumentRegisteredNotification(String toEmail, String toName, String documentName) {
    // TODO: Implement real email sending
    log.info("Sending document registered notification to {} for document: {}", toEmail, documentName);
  }

  @Override
  public void sendDocumentExecutedNotification(String toEmail, String toName, String documentName) {
    // TODO: Implement real email sending
    log.info("Sending document executed notification to {} for document: {}", toEmail, documentName);
  }
}
