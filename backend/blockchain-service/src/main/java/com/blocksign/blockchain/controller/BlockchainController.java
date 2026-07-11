package com.blocksign.blockchain.controller;

import com.blocksign.blockchain.dto.ApiResponse;
import com.blocksign.blockchain.dto.BlockchainRecordResponse;
import com.blocksign.blockchain.dto.SubmitHashRequest;
import com.blocksign.blockchain.service.BlockchainService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blockchain")
@RequiredArgsConstructor
public class BlockchainController {

  private final BlockchainService blockchainService;

  @PostMapping("/submit")
  public ResponseEntity<ApiResponse<BlockchainRecordResponse>> submit(
      @RequestBody @Valid SubmitHashRequest request) {
    return ResponseEntity.ok(
        ApiResponse.ok(blockchainService.submitHash(request)));
  }

  @GetMapping("/verify/{contractId}")
  public ResponseEntity<ApiResponse<Boolean>> verify(
      @PathVariable String contractId,
      @RequestParam String currentHash) {
    return ResponseEntity.ok(
        ApiResponse.ok(blockchainService.verifyHash(contractId, currentHash)));
  }

  @GetMapping("/transaction/{contractId}")
  public ResponseEntity<ApiResponse<BlockchainRecordResponse>> getTransaction(
      @PathVariable String contractId) {
    return ResponseEntity.ok(
        ApiResponse.ok(blockchainService.getRecord(contractId)));
  }
}
