package com.blocksign.document.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignerRequest {
  @NotBlank
  private String userId;

  @NotBlank
  private String email;

  private String name;
}
