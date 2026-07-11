package com.blocksign.audit.repository;

import com.blocksign.audit.entity.AuditEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuditEventRepository extends JpaRepository<AuditEvent, String> {
  List<AuditEvent> findByContractIdOrderByOccurredAtDesc(String contractId);
  Optional<AuditEvent> findByEventId(String eventId);
}
