package com.blocksign.signing.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignDocumentRequest {
  @NotBlank
  private String contractId;

  @NotBlank
  private String walletAddress;

  @NotBlank
  private String walletSignature;

  @NotBlank
  private String documentHash;
}
