package com.blocksign.document.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignerResponse {
  private String userId;
  private String email;
  private String name;
  private String status;
}
