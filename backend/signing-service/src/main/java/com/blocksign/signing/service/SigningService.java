package com.blocksign.signing.service;

import com.blocksign.signing.dto.request.DeclineDocumentRequest;
import com.blocksign.signing.dto.request.SignDocumentRequest;
import com.blocksign.signing.dto.response.SigningStatusResponse;

public interface SigningService {
  SigningStatusResponse signDocument(SignDocumentRequest request, String userId);
  SigningStatusResponse declineDocument(DeclineDocumentRequest request, String userId);
  SigningStatusResponse getSigningStatus(String contractId);
}
