package com.blocksign.document.repository;

import com.blocksign.document.entity.Document;
import com.blocksign.document.enums.DocumentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {

  List<Document> findByTenantId(String tenantId);

  List<Document> findByTenantIdAndStatus(String tenantId, DocumentStatus status);

  Page<Document> findByTenantId(String tenantId, Pageable pageable);

  Optional<Document> findByIdAndTenantId(String id, String tenantId);
}
