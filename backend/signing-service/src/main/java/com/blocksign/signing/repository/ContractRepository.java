package com.blocksign.signing.repository;

import com.blocksign.signing.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, String> {
  Optional<Contract> findByIdAndTenantId(String id, String tenantId);
}
