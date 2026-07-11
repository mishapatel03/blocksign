package com.blocksign.document.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RegisterDocumentRequest {
  @NotBlank
  private String s3Key;

  @NotBlank
  private String fileName;

  private Long fileSize;

  @NotEmpty
  private List<SignerRequest> signers;

  private LocalDateTime signingDeadline;

  private String title;
}
