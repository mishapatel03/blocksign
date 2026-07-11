package com.blocksign.audit.controller;

import com.blocksign.audit.dto.ApiResponse;
import com.blocksign.audit.dto.AuditEventResponse;
import com.blocksign.audit.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
public class AuditController {

  private final AuditService auditService;

  @GetMapping("/{contractId}")
  public ResponseEntity<ApiResponse<List<AuditEventResponse>>> getAuditTrail(
      @PathVariable String contractId) {
    return ResponseEntity.ok(
        ApiResponse.ok(auditService.getAuditTrail(contractId)));
  }
}
