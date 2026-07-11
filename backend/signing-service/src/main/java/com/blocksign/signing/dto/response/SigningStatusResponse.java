package com.blocksign.signing.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SigningStatusResponse {
  private String contractId;
  private String status;
  private String message;
  private List<SignerResponse> signers;
}
