package com.blocksign.audit.service;

import com.blocksign.audit.dto.AuditEventMessage;
import com.blocksign.audit.dto.AuditEventResponse;

import java.util.List;

public interface AuditService {
  void saveEvent(AuditEventMessage message);
  boolean eventExists(String eventId);
  List<AuditEventResponse> getAuditTrail(String contractId);
}
