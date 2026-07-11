package com.blocksign.signing.repository;

import com.blocksign.signing.entity.Signer;
import com.blocksign.signing.enums.SignerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SignerRepository extends JpaRepository<Signer, String> {
  List<Signer> findByContractId(String contractId);
  Optional<Signer> findByContractIdAndUserId(String contractId, String userId);
  List<Signer> findByContractIdAndStatus(String contractId, SignerStatus status);
}
