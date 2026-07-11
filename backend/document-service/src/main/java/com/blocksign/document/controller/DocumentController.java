package com.blocksign.document.controller;

import com.blocksign.document.dto.request.RegisterDocumentRequest;
import com.blocksign.document.dto.response.ApiResponse;
import com.blocksign.document.dto.response.DocumentResponse;
import com.blocksign.document.dto.response.PresignedUrlResponse;
import com.blocksign.document.service.DocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
@Slf4j
public class DocumentController {

  private final DocumentService documentService;

  @GetMapping("/presigned-url")
  public ResponseEntity<ApiResponse<PresignedUrlResponse>> getPresignedUrl(
      @RequestParam String fileName) {
    return ResponseEntity.ok(
        ApiResponse.ok(documentService.generatePresignedUrl(fileName)));
  }

  @PostMapping("/register")
  public ResponseEntity<ApiResponse<DocumentResponse>> register(
      @RequestBody @Valid RegisterDocumentRequest req,
      @RequestHeader("X-Tenant-Id") String tenantId) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.ok(documentService.registerDocument(req, tenantId)));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<Page<DocumentResponse>>> getAll(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestHeader("X-Tenant-Id") String tenantId) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
    return ResponseEntity.ok(
        ApiResponse.ok(documentService.getAllDocuments(tenantId, pageable)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<DocumentResponse>> getById(
      @PathVariable String id,
      @RequestHeader("X-Tenant-Id") String tenantId) {
    return ResponseEntity.ok(
        ApiResponse.ok(documentService.getDocument(id, tenantId)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(
      @PathVariable String id,
      @RequestHeader("X-Tenant-Id") String tenantId) {
    documentService.deleteDocument(id, tenantId);
    return ResponseEntity.ok(ApiResponse.ok(null));
  }
}
