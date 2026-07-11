package com.blocksign.signing.service;

import com.blocksign.signing.dto.request.DeclineDocumentRequest;
import com.blocksign.signing.dto.request.SignDocumentRequest;
import com.blocksign.signing.dto.response.SigningStatusResponse;
import com.blocksign.signing.entity.Contract;
import com.blocksign.signing.entity.Signer;
import com.blocksign.signing.enums.ContractStatus;
import com.blocksign.signing.enums.SignerStatus;
import com.blocksign.signing.event.SigningEventProducer;
import com.blocksign.signing.exception.ContractNotFoundException;
import com.blocksign.signing.repository.ContractRepository;
import com.blocksign.signing.repository.SignerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SigningServiceImpl implements SigningService {

  private final ContractRepository contractRepository;
  private final SignerRepository signerRepository;
  private final SigningEventProducer eventProducer;

  @Override
  @Transactional
  public SigningStatusResponse signDocument(SignDocumentRequest request, String userId) {

    log.info("Processing signature for contract: {}", request.getContractId());

    // TODO Step 1: Validate EIP-712 wallet signature
    // TODO Step 2: Check contract is in signable state
    // TODO Step 3: Update signer status to SIGNED
    // TODO Step 4: Check if all signers done
    //              → if yes move to EXECUTED
    // TODO Step 5: Publish signing event to Kafka

    return SigningStatusResponse.builder()
        .contractId(request.getContractId())
        .status("SIGNED")
        .message("Signature recorded successfully")
        .build();
  }

  @Override
  @Transactional
  public SigningStatusResponse declineDocument(DeclineDocumentRequest request, String userId) {

    log.info("Declining contract: {}", request.getContractId());

    // TODO: Update status to DECLINED
    // TODO: Notify all parties

    return SigningStatusResponse.builder()
        .contractId(request.getContractId())
        .status("DECLINED")
        .message("Contract declined")
        .build();
  }

  @Override
  public SigningStatusResponse getSigningStatus(String contractId) {
    // TODO: Return real signing status from DB
    return SigningStatusResponse.builder()
        .contractId(contractId)
        .status("PENDING")
        .message("Waiting for signatures")
        .build();
  }
}
