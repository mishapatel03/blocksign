package com.blocksign.document.service;

import com.blocksign.document.dto.request.RegisterDocumentRequest;
import com.blocksign.document.dto.response.DocumentResponse;
import com.blocksign.document.dto.response.PresignedUrlResponse;
import com.blocksign.document.entity.Document;
import com.blocksign.document.enums.DocumentStatus;
import com.blocksign.document.event.DocumentRegisteredEvent;
import com.blocksign.document.kafka.DocumentEventProducer;
import com.blocksign.document.exception.DocumentNotFoundException;
import com.blocksign.document.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentServiceImpl implements DocumentService {

  private final DocumentRepository documentRepository;
  private final DocumentEventProducer eventProducer;

  @Override
  @Transactional
  public DocumentResponse registerDocument(RegisterDocumentRequest request, String tenantId) {

    log.info("Registering document: {} for tenant: {}", request.getFileName(), tenantId);

    // TODO: Step 1 - Generate SHA-256 hash from S3 file
    String documentHash = "mock-hash-" + UUID.randomUUID();

    // TODO: Step 2 - Call blockchain service to record hash
    String blockchainTxHash = "0xmock-" + UUID.randomUUID();

    Document document = Document.builder()
        .tenantId(tenantId)
        .uploadedBy("mock-user")
        .fileName(request.getFileName())
        .fileSize(request.getFileSize())
        .s3Key(request.getS3Key())
        .documentHash(documentHash)
        .blockchainTxHash(blockchainTxHash)
        .status(DocumentStatus.PENDING)
        .build();

    Document saved = documentRepository.save(document);

    // TODO: Step 3 - Publish event to Kafka
    eventProducer.publishDocumentRegistered(saved);

    return mapToResponse(saved);
  }

  @Override
  public DocumentResponse getDocument(String id, String tenantId) {
    Document doc = documentRepository
        .findByIdAndTenantId(id, tenantId)
        .orElseThrow(() -> new DocumentNotFoundException("Document not found: " + id));
    return mapToResponse(doc);
  }

  @Override
  public Page<DocumentResponse> getAllDocuments(String tenantId, Pageable pageable) {
    return documentRepository
        .findByTenantId(tenantId, pageable)
        .map(this::mapToResponse);
  }

  @Override
  public PresignedUrlResponse generatePresignedUrl(String fileName) {
    // TODO: Generate real S3 pre-signed URL
    return PresignedUrlResponse.builder()
        .uploadUrl("https://mock-s3-url.com/" + fileName)
        .s3Key("mock-tenant/documents/" + UUID.randomUUID() + ".pdf")
        .expiresAt(java.time.LocalDateTime.now().plusMinutes(15))
        .build();
  }

  @Override
  public void deleteDocument(String id, String tenantId) {
    Document doc = documentRepository
        .findByIdAndTenantId(id, tenantId)
        .orElseThrow(() -> new DocumentNotFoundException("Document not found: " + id));
    documentRepository.delete(doc);
  }

  private DocumentResponse mapToResponse(Document doc) {
    return DocumentResponse.builder()
        .id(doc.getId())
        .fileName(doc.getFileName())
        .status(doc.getStatus().name())
        .documentHash(doc.getDocumentHash())
        .blockchainTxHash(doc.getBlockchainTxHash())
        .blockNumber(doc.getBlockNumber())
        .createdAt(doc.getCreatedAt())
        .build();
  }
}
