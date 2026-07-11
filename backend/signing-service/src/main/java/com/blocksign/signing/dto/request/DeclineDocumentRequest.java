package com.blocksign.signing.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeclineDocumentRequest {
  @NotBlank
  private String contractId;

  @NotBlank
  private String reason;
}
