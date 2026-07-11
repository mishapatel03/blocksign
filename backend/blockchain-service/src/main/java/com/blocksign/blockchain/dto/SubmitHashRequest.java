package com.blocksign.blockchain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubmitHashRequest {
  @NotBlank
  private String contractId;

  @NotBlank
  private String tenantId;

  @NotBlank
  private String documentHash;
}
