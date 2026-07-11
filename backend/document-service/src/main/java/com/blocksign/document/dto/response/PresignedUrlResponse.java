package com.blocksign.document.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PresignedUrlResponse {
  private String uploadUrl;
  private String s3Key;
  private LocalDateTime expiresAt;
}
