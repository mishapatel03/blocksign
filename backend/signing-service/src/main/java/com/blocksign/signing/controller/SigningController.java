package com.blocksign.signing.controller;

import com.blocksign.signing.dto.request.DeclineDocumentRequest;
import com.blocksign.signing.dto.request.SignDocumentRequest;
import com.blocksign.signing.dto.response.ApiResponse;
import com.blocksign.signing.dto.response.SigningStatusResponse;
import com.blocksign.signing.service.SigningService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sign")
@RequiredArgsConstructor
@Slf4j
public class SigningController {

  private final SigningService signingService;

  @PostMapping("/sign")
  public ResponseEntity<ApiResponse<SigningStatusResponse>> sign(
      @RequestBody @Valid SignDocumentRequest req) {
    String userId = "mock-user";
    return ResponseEntity.ok(
        ApiResponse.ok(signingService.signDocument(req, userId)));
  }

  @PostMapping("/decline")
  public ResponseEntity<ApiResponse<SigningStatusResponse>> decline(
      @RequestBody @Valid DeclineDocumentRequest req) {
    String userId = "mock-user";
    return ResponseEntity.ok(
        ApiResponse.ok(signingService.declineDocument(req, userId)));
  }

  @GetMapping("/status/{contractId}")
  public ResponseEntity<ApiResponse<SigningStatusResponse>> status(
      @PathVariable String contractId) {
    return ResponseEntity.ok(
        ApiResponse.ok(signingService.getSigningStatus(contractId)));
  }
}
