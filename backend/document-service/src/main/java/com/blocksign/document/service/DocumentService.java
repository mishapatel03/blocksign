package com.blocksign.document.service;

import com.blocksign.document.dto.request.RegisterDocumentRequest;
import com.blocksign.document.dto.response.DocumentResponse;
import com.blocksign.document.dto.response.PresignedUrlResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentService {
  DocumentResponse registerDocument(RegisterDocumentRequest request, String tenantId);
  DocumentResponse getDocument(String id, String tenantId);
  Page<DocumentResponse> getAllDocuments(String tenantId, Pageable pageable);
  PresignedUrlResponse generatePresignedUrl(String fileName);
  void deleteDocument(String id, String tenantId);
}
