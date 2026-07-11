package com.blocksign.notification.service;

public interface EmailService {
  void sendSigningNotification(String toEmail, String toName, String documentName);
  void sendDocumentRegisteredNotification(String toEmail, String toName, String documentName);
  void sendDocumentExecutedNotification(String toEmail, String toName, String documentName);
}
